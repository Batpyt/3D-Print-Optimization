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

/**
 * Yuping Tian
 * UCD
 * Project: faster 3D printing
 *
 * this class is the main class, we call all other classes from this class.
 * the first step is read in the gcode file from PC and extract lines of moves.
 * the second step is store the start and end coordinate.
 * the third step is store the line into many two-dimension string arrays.
 * the final step is using the optimization algorithms to changed the order or direction of moves.
 */


public class maincontroller {
	public static void main(String[] args) throws IOException{
		//readFileByLines();
		maincontroller();
	}
	
	public static void maincontroller() throws IOException {		
		
		long starttime=System.currentTimeMillis();//record the start time of the program
		
        String pathname = "C:/Users/Tian/Desktop/Android.gcode";
        int linesno=0;
        int no=0;
        
        int i=0;
        int j=0;
             
        int startno=0;
        int endno=0;
        
        int count=0;                          //to store the number of moves
        
        String[] startline=new String[9999];  //strings to store the boundaries of start
        String[] endline=new String[9999];    //strings to store the boundaries of end
        
        double [] xs=new double[9999];      //start x coordinate
        double [] ys=new double[9999];      //start y coordinate
        
        double [] xe=new double[9999];      //end x coordinate 
        double [] ye=new double[9999];      //end x coordinate 
        
        String[] s=new String[999999];        //a big enough string array to store the lines of gcode
        
        /*
		 * This part is made to read the Gcode from computer and store each line into String 
		 * then give the string to other classes to do other operations
		 */
        
        try (FileReader reader = new FileReader(pathname);  
             BufferedReader br = new BufferedReader(reader)){
            String line;
            while ((line = br.readLine()) != null) {
            	
            	//store each line of gcode in this String
            	s[no]=line;             
            	linesno++;
            	no++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        /*
         * extract lines of moves from every lines
         */
        
        extractmoves getmoves=new extractmoves();           //call this class to extract the lines from every moves
        String[] moves = getmoves.extractmove(linesno, s);  //store these lines into a new String
        i=moves.length;
                 
        /*
         * Then get the start and end coordinates from each move
         * we define that the line that its next line contains "G1 E1.00000" is the line contains start coordinate
         * and also record this line for divide into blocks
         *
         * Pseudo Code
         * if current line contains G1, E and (X or Y)
         * 		if one line back contains "G1 E1.00000"
         *  		get start coordinate from two lines above
         *   		and store this line
         *  	end if
         * end if
			
         */
        
        while(j<i){
        	if(moves[j].contains("G1")&&(moves[j].contains("X")||moves[j].contains("Y"))&&moves[j].contains("E")){  
        		
        		if(moves[j-1].contains("G1 E1.00000")){
        			xs[startno]=Double.parseDouble((String) moves[j-2].subSequence(moves[j-2].indexOf("X")+1, moves[j-2].indexOf("Y")));
        			ys[startno]=Double.parseDouble((String) moves[j-2].subSequence(moves[j-2].indexOf("Y")+1, moves[j-2].indexOf("F")));
        			
        			startline[startno]=moves[j-2];
        			
        			startno++;
            		j++;
            		
            		//count is recording the number of moves
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
         * As the end coordinate, it's get from the line which its next line has only "G1", "E" and "F" without "X" and "Y"
         * however there are two situations, which are "G1 X Y E" and "G1 X Y F", so we have to apart these two situations
         *
         * Pseudo code
         * if current line contains G1, E and F without X and Y
         * 		if one line next contains "G92 E0"
         *   		get end coordinate from one line back
         *   		and store this line
         *  	end if
         * end if
         */
        
        j=5;  //set 5 as the loop start, to avoid when j=0, there is not -1 line
        
        while(j<i){
        	
        	if(moves[j].contains("E")&&moves[j].contains("F")&&moves[j].contains("G1")&&!moves[j].contains("X")&&!moves[j].contains("Y")){
        		
        		if(moves[j+1].contains("G92 E0")){
        			
        			//there are two form of end line, one is end with F and the other is end with E, 
        			//distinguish which form is and get the coordinate
        			if(moves[j-1].contains("E")){
        			xe[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("X")+1, moves[j-1].indexOf("Y")));
        			ye[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("Y")+1, moves[j-1].indexOf("E")));
        		}
        			else if(!moves[j-1].contains("E")){
        				xe[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("X")+1, moves[j-1].indexOf("Y")));
            			ye[endno]=Double.parseDouble((String) moves[j-1].subSequence(moves[j-1].indexOf("Y")+1, moves[j-1].indexOf("F")));
        			}
        			
        			endline[endno]=moves[j-1];
        			
            		endno++;
        			j++;
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
         * because the size of arrays that stored start lines and end lines are 9999,
         * it's needed to change it to correct size
         */
        
        getstartline sl= new getstartline();
        String[] startline2=sl.getstart(startline,count); //storing the start lines
        
        getendline el=new getendline();
        String[] endline2=el.getend(endline,count);       //storing the end lines
        
        
        
        /*
         * then store lines into many two-dimension arrays
         * to do this, several data are needed, start and end lines, extracted moves, number of moves and the number of lines
         */

        block bl=new block();
        String[][] blocks=bl.block(startline2, endline2, moves, count, moves.length);
        
        
        /*
         * then use optimization algorithms to change the arrays
         */
        
        fileoutput out=new fileoutput();

        annealing ann=new annealing();
        
        //annealingtwo ann2=new annealingtwo();
        
        hillclimbing hc=new hillclimbing();
        
        //hillclimbingtwo hc2=new hillclimbingtwo();
        
        XS xss=new XS();
        YS yss=new YS();
        XE xee=new XE();
        YE yee=new YE();
        
        idledistance id=new idledistance();
        double idledis=id.dis(xs, ys, xe, ye, blocks, count);
        
        //when testing the performance, output the results of two algorithm at same time is more convenient
        //therefore add a judgement in fileoutput class, set two input string for fileoutput()
        String whichann="annealing";     //first one for simulated annealing
        String whichhc="hillclimbing";   //second one for hill climbing
        
        //store the result of simulated annealing
        String[][] annealblocks=ann.annealing( xs, ys, xe, ye, blocks, count);
        
      //store the result of hill climbing
        String[][] hillblocks=hc.hillclimbing( xs, ys, xe, ye, blocks, count);
       
        //output the result of simulated annealing, send the string of it
        String newmoves=out.output(annealblocks,  count, whichann);
        System.out.println(newmoves);
        
        //output the result of hill climbing, send the string of it
        String newmoves1=out.output( hillblocks,  count, whichhc);
        System.out.println(newmoves1);
        
        long endtime=System.currentTimeMillis();//record the end time of program
        
        long runtime=endtime-starttime;         //calculate the running time of program
        
        System.out.println("Run time: "+runtime+" milli seconds");
        
        //System.out.println(count);
        
        
      
        

	
	
	}   
        
        
        
        
    
	
}
