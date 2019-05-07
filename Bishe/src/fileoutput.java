import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class fileoutput {
	public String output(String[][] blocks,int count,String which) throws IOException{
		String[] newmoves= new String[999999];
		int i=0;
		int b=0;
		int n=0;
		String qqq="0";
		
		newmoves[0]="G21";
		newmoves[1]="M107";
		newmoves[2]="M104 S205";
		newmoves[3]="G28";
		newmoves[4]="G1 Z5 F5000";
		newmoves[5]="M109 S205";
		newmoves[6]="G90";
		newmoves[7]="G92 E0";
		newmoves[8]="M82";
		newmoves[9]="G1 F1800.000 E-1.00000";
		newmoves[10]="G92 E0";
		newmoves[11]="  ";
		
		
		
		
		i=12;
		while(b<count){
			//System.out.println("///////////////////////////////////////////////"+b);
			n=0;
			while(n<blocks[b].length){
				newmoves[i]=blocks[b][n];
				i++;
				n++;
			}
			b++;
		}
		n=0;
		/*
		for(int g=0;g<blocks[1360].length;g++){
			System.out.println(blocks[1360][g]);
		}
		*/
		File file = null;
		
		if(which=="annealing"){
			file=new File("C:/Users/Tian/Desktop/suanfa1.gcode");
			qqq="the result of simulated annealing is stored in suanfa1.gcode";
		}
		if(which=="hillclimbing"){
			file=new File("C:/Users/Tian/Desktop/suanfa2.gcode");
			qqq="the result of hill climbing is stored in suanfa2.gcode";
		}
		
		
		//File file=new File("C:/Users/Tian/Desktop/suanfa2.gcode");
		//File file=new File("C:/Users/Tian/Desktop/reverse.gcode");
		//File file=new File("C:/Users/Tian/Desktop/originalgcode");
		
		if (!file.exists()) {
		    file.createNewFile();
		   }
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//bw.write(newmoves[0]);
		int xie=0;
		while(xie<i){
			if(xie==598902){
				//System.out.println(" xiexiexiexie "+newmoves[xie]);
			}
			bw.write(newmoves[xie]);
			bw.newLine();
			xie++;
			
		}
		//bw.write(newmoves[i]);
		bw.close();
		
		
        
		
		System.out.println("Finish");
		
		
		
		
		
		
		
		
		
		
		
		
		return qqq;
		
	}

}
