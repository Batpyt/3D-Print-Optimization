import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class fileoutput {
	public String output(String[] before,String[] after,String[][] blocks,int endend,int count) throws IOException{
		String[] newmoves= new String[999999];
		int i=0;
		int b=0;
		int n=0;
		//System.out.println(endend);
		
		while(i<before.length){
			newmoves[i]=before[i];
			//System.out.println(newmoves[i]);
			i++;
		}
		
		
		while(b<count){
			//System.out.println("///////////////////////////////////////////////"+b);
			n=0;
			while(n<blocks[b].length){
				newmoves[i]=blocks[b][n];
				if(b==1360){
					//System.out.println(newmoves[i]+"   "+i);
				}
				//System.out.println(newmoves[i]);
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
		
		
		
		File file=new File("C:/Users/Tian/Desktop/suanfa1.gcode");
		//File file=new File("C:/Users/Tian/Desktop/suanfa2.gcode");
		//File file=new File("C:/Users/Tian/Desktop/reverse.gcode");
		//File file=new File("C:/Users/Tian/Desktop/original.gcode");
		
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
		
		
		
		
		
		
		
		
		
		
		
		String qqq="0";
		return qqq;
		
	}

}
