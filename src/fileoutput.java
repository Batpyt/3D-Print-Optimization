import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class fileoutput {
	public String output(String[] before,String[] after,String[][] blocks,int endend,int count) throws IOException{
		String[] newmoves= new String[endend];
		int i=0;
		int b=0;
		int n=0;
		//System.out.println(count);
		
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
				
				//System.out.println(newmoves[i]);
				i++;
				n++;
			}
			b++;
		}
		n=0;
		while(!after[n].contains("filament used")){
			newmoves[i]=after[n];
			//System.out.println(newmoves[i]);
			n++;
			i++;
		}
		//System.out.println(after[after.length]);
		newmoves[i]=after[after.length-1];
		//System.out.println(newmoves[i]);
		
		String filepath="C:/Users/Tian/Desktop/";
		//File file=new File(filepath);
		String filename="new.gcode";
		//File file=new File(filepath,filename);
		
		File file=new File("C:/Users/Tian/Desktop/new.gcode");
		if (!file.exists()) {
		    file.createNewFile();
		   }
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//bw.write(newmoves[0]);
		int xie=0;
		while(xie<i+1){
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
