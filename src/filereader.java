import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.Utilities;

public class filereader {
	public static void main(String[] args) throws IOException{
		//readFileByLines();
		readFile();
	}
	/*
	 This class is made to read the Gcode from computer and store each line into String 
	 then give the string to other classes to do other operations
	 
	 */
	public static void readFile() throws IOException {
        String pathname = "C:/Users/Tian/Desktop/test.gcode"; 
        int linesno=0;
        int no=0;
        int length=9999;
        int i=0;
        int j=0;
        int b=0;
        int count=0;
        //boolean inmove=false;
        
        int startno=0;
        int endno=0;
        
        int xsn1=0;
        int ysn1=0;
        int xsn2=0;
        int ysn2=0;
        
        int xen1=0;
        int yen1=0;
        int xen2=0;
        int yen2=0;
        
        String[] startline=new String[9999];
        String[] endline=new String[9999];
        
        
        double [] xs=new double[length];
        double [] ys=new double[length];
        
        double [] xe=new double[length];
        double [] ye=new double[length];
        
        double [] all=new double[999999];
        String xzhou=null;
        String yzhou=null;
        String[] s=new String[999999];
        //String[] move=new String[linesno];
        //String currentline=null;
        ArrayList<String> allLines = new ArrayList(); 
        try (FileReader reader = new FileReader(pathname);  
             BufferedReader br = new BufferedReader(reader)){
            String line;
            while ((line = br.readLine()) != null) {
            	s[no]=line;   //store each line of Gcode in this String
            	linesno++;
            	no++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(linesno);
        
        extractmoves getmoves=new extractmoves();  //call this class to extract the lines from every moves
        String[] moves = getmoves.extractmove(linesno, s);  //store these lines into a new String
        i=moves.length;
        //System.out.println(i);
        //String test=(String) moves[4].substring(moves[4].indexOf("X")+1, moves[4].indexOf("Y"));
        //System.out.println(test);
        
        
        
        /*
         Then get the start and end coordinates from each move
         we define that the line that its last line contians"G1 E1.00000" is the line contains start coordinate
         */
        while(j<i){
        	if(moves[j].contains("G1")&&(moves[j].contains("X")||moves[j].contains("Y"))&&moves[j].contains("E")){  
        		//System.out.println("while");
        		//inmove=true;
        		if(moves[j-1].contains("G1 E1.00000")){
        			xs[startno]=Double.parseDouble((String) moves[j-2].subSequence(moves[j-2].indexOf("X")+1, moves[j-2].indexOf("Y")));
        			ys[startno]=Double.parseDouble((String) moves[j-2].subSequence(moves[j-2].indexOf("Y")+1, moves[j-2].indexOf("F")));
        			
        			startline[startno]=moves[j-2];
        			
        			//System.out.println("X start: "+xs[xsn1][startno]+" no: "+startno);
            		//System.out.println("Y start: "+ys[ysn1][startno]+" no: "+startno);
            		//xsn2++;
            		//ysn2++;
        			startno++;
            		j++;
            		count++;
        		}
        		
        		
        		else{
      
        			j++;
        		}
        		
        		
        	}
        	else{
        		j++;
        	}
        	
        }
        
        /*
         As the end coordinate, the line which its next line has only "G1", "E" and "F" without "X" and "Y"
         however there are two situations, which are "G1 X Y E" and "G1 X Y F", so we have to apart these two situations
         */
        
        j=5;  //set 5 as the loop start, to avoid when j=0, there is not -1 line
        
        while(j<i){
        	//System.out.println("while");
        	if(moves[j].contains("E")&&moves[j].contains("F")&&moves[j].contains("G1")&&!moves[j].contains("X")&&!moves[j].contains("Y")){
        		//System.out.println(xen2);
        		if(moves[j+1].contains("G92 E0")){
        			if(moves[j-1].contains("E")){
        			xe[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("X")+1, moves[j-1].indexOf("Y")));
        			ye[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("Y")+1, moves[j-1].indexOf("E")));
        		}
        			else if(!moves[j-1].contains("E")){
        				xe[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("X")+1, moves[j-1].indexOf("Y")));
            			ye[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("Y")+1, moves[j-1].indexOf("F")));
        			}
        			
        			endline[endno]=moves[j-1];
        			//System.out.println(moves[j-1]);
        			//System.out.println(moves[j]);
        			//System.out.println(moves[j+1]);
        			//System.out.println("X end: "+xe[xen1][endno]+" no1: "+endno);
            		//System.out.println("Y end: "+ye[yen1][endno]+" no1: "+endno);
        			//xen2++;
        			//yen2++;
            		endno++;
        			j++;
        		}
        		
        		else{
        			j++;
        		}
        		/*
        		else{
        			xe[xen1][xen2]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("X")+1, moves[j-1].indexOf("Y")));
        			//ye[yen1][yen2]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("Y")+1, moves[j-1].indexOf("E")));
        			
        			System.out.println("X end: "+xs[xen1][xen2]+" no2: "+xen2);
            		System.out.println("Y end: "+ys[yen1][yen2]+" no2: "+yen2);
        			xen2++;
        			yen2++;
        			j++;
        		}
        		*/
        	}
        	
        	else{
        		j++;
        	}
        }
        
        
        j=0;
        int beforeend=0;
        int as=0;
        //int[] afterstart={0,0};
        int afterstart=0;
        int endend=0;
        
        
        while(!s[j].contains("filament used")){
        	if(s[j].contains("E-1.00000")){
        		beforeend=j;
        		j++;
        	}
        	else if(s[j].contains("M107")&&j>1){
        		afterstart=j;
        		as++;
        		j++;
        	}
        	else{
        		j++;
        	}
        	
        }
        endend=j;
        //System.out.println("beforeend: "+beforeend+" afterstart: "+afterstart[1]+" endend: "+endend);
        //System.out.println(s[beforeend]);
        //System.out.println(s[endend]);
        
        int aftersize=endend-afterstart;
        String[] before=new String[beforeend+2];
        String[] after=new String[aftersize+1];
        
        before[beforeend]=s[beforeend];
        before[beforeend+1]=s[beforeend+1];
        j=0;
        while(j<beforeend){
        	before[j]=s[j];
        	//System.out.println(before[j]);
        	j++;
        }
        //System.out.println(before[beforeend]);
        //System.out.println(before[beforeend+1]);
        
        //System.out.println("//////////////////////////////////////////////////////////////////////////////");
        
        int af=0;
        j=afterstart;
        while(j<endend){
        	after[af]=s[j];
        	//System.out.println(after[af]);
        	af++;
        	j++;
        }
        after[aftersize]=s[endend];
        //System.out.println(after[aftersize]);
        
        getstartline sl= new getstartline();
        String[] startline2=sl.getstart(startline,count);
        
        getendline el=new getendline();
        String[] endline2=el.getend(endline,count);
        
        
        int end=0;
        int start=0;
        
        //System.out.println(moves.length);
        
        block bl=new block();
        String[][] blocks=bl.block(startline2, endline2, moves, count, moves.length);
        
       // rev r=new rev();
        //String[] reverseblock=r.reverseBlock(162);
        
        
        //fileoutput out=new fileoutput();
        //String newmoves=out.output(before, after, blocks, endend,count);
        
        
        //exchangetwo extwo=new exchangetwo();
        //String[][] exchangedtwoblocks=extwo.exchangetwo(moves, xs, ys, xe, ye, blocks, count);
        
        //exchange ex=new exchange();
        //String[][] exchangedblocks=ex.exchange(moves, xs, ys, xe, ye, exchangedtwoblocks, count);
        
        reversemethod r=new reversemethod();
        String[][] revblocks=r.reversemethod(moves, xs, ys, xe, ye, blocks, count);
        
        fileoutput out=new fileoutput();
        String newmoves=out.output(before, after, revblocks, endend,count);
        
        
        
       
        
        //System.out.println(s[endend]+" "+endend);
        //System.out.println(count);
        //System.out.println(xe.length+"length");
        
        /*
        b=0;
        while(b<count){
			int pr=0;
			System.out.println("block number: "+b);
			while(pr<blocks[b].length){
				System.out.println(blocks[b][pr]);
				pr++;
			}
			b++;
		}
		
		*/
        
        
        
        
        /*
        for(String print:startline2){
        	System.out.println(print+"  start");
        	start++;
        }
        
        
        for(String print2:endline2){
        	System.out.println(print2+" end");
        	end++;
        }
        System.out.println(start+" "+endline2.length+" "+count);
        
        /*
        int q=0;
        while(q<100){	
            System.out.println(xe[0][q]+" no: "+q);
            q++;
        }
        */
        
        /*
        int q=0;
        while(q<100){	
            System.out.println(xs[0][q]+" no: "+q);
            System.out.println(ys[0][q]+" no: "+q);
            q++;
        }
        /
        
        /*
        while(j<i){
        	
        	//if(moves[j].contains("G92 E0")&&moves[j+1]=="end"){
        	//	break;
        	//}
        	
        	//type 1 of start
        	if(moves[j].contains("G92 E0")&&(moves[j+1].contains("G1"))&&(moves[j+1].contains("X"))&&(moves[j+2].contains("E"))&&(moves[j+2].contains("F"))
        			&&(moves[j+1].contains("Y"))&&(!moves[j+1].contains("end"))){ 
        		//System.out.println("while");
        		xs[xsn1][xsn2]=Double.parseDouble((String) moves[j+1].subSequence(moves[j+1].indexOf("X")+1, moves[j+1].indexOf("Y")));
        		if(moves[j+1].contains("E")){
        			ys[ysn1][ysn2]=Double.parseDouble((String) moves[j+1].subSequence(moves[j+1].indexOf("Y")+1, moves[j+1].indexOf("E")));
        		}
        		if(moves[j+1].contains("F")){
        			ys[ysn1][ysn2]=Double.parseDouble((String) moves[j+1].subSequence(moves[j+1].indexOf("Y")+1, moves[j+1].indexOf("F")));
        		}
        		System.out.println("X start: "+xs[xsn1][xsn2]+" no: "+xsn2);
        		System.out.println("Y start: "+ys[ysn1][ysn2]+" no: "+ysn2);
        		j++;
        		//xsn1++;
        		xsn2++;
        		ysn2++;
        		
        	}
        	
        	
        	else{
        		j++;
        	}
        	
        	
        }
        
        while(b<i){
        	//get end X Y
        	if((b!=2)&&(b!=0)&&(moves[b].contains("G92"))&&(moves[b-1].contains("G1"))&&(moves[b-1].contains("E"))&&(moves[b-1].contains("F"))
        			&&(moves[b-2].contains("G1"))&&(moves[b-2].contains("X"))&&(moves[b-2].contains("Y"))&&(moves[b-2].contains("E"))){   
        		//System.out.println("while"+b);
        		xe[xen1][xen2]=Double.parseDouble((String) moves[b-2].subSequence(moves[b-2].indexOf("X")+1, moves[b-2].indexOf("Y")));
        		
        		if(moves[b-2].contains("F")){
        			//System.out.println("if2");
        			//ye[yen1][yen2]=Double.parseDouble((String) moves[b-3].subSequence(moves[b-3].indexOf("Y")+1, moves[b-3].indexOf("E")));
        			//	System.out.println("Y end: "+ye[yen1][yen2]+" no1: "+yen2);
        		}
        		
        		if(moves[b-2].contains("E")){
        			//System.out.println("if1");
        			ye[yen1][yen2]=Double.parseDouble((String) moves[b-2].subSequence(moves[b-2].indexOf("Y")+1, moves[b-2].indexOf("E")));
        			//System.out.println("Y end: "+ye[yen1][yen2]+" no2: "+yen2);
        		}
        		
        		//if(moves[j].contains("F")){
        			//ye[yen1][yen2]=Double.parseDouble((String) moves[j].subSequence(moves[j].indexOf("Y")+1, moves[j].indexOf("F")));
        		//}
        		//System.out.println("X end: "+xe[xen1][xen2]+" no: "+xen2);
        		//System.out.println("Y end: "+ye[yen1][yen2]+" no: "+yen2);
        		b++;
        		xen2++;
        		yen2++;
        		
        	}
        	else{
        		b++;
        		//System.out.println(moves[b-1]);
        	}
        }
        */
        
        
        //System.out.println(j);
        
       
        /*
        while(q<100){	
          System.out.println(moves[q]+" no: "+q);
          q++;
        }
        */
	
	
	}   //readfile
        
        
        
        
    
	
}//class
