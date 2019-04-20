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

public class maincontroller {
	public static void main(String[] args) throws IOException{
		//readFileByLines();
		readFile();
	}
	/*
	 This class is made to read the Gcode from computer and store each line into String 
	 then give the string to other classes to do other operations
	 
	 */
	public static void readFile() throws IOException {
		long starttime=System.currentTimeMillis();
		
        String pathname = "C:/Users/Tian/Desktop/Moneybox.gcode"; 
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
        			/*
        			if(startline[startno].contains("X139.149 Y107.040 F7800.000")){
        				System.out.println("12342113412412424214213");
        			}
        			*/
        			//int j2=j-2;
        			//System.out.println(startline[startno]+" ssssss "+j2);
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
        fileoutput out=new fileoutput();
        
        block bl=new block();
        String[][] blocks=bl.block(startline2, endline2, moves, count, moves.length);
        
        idledistance id=new idledistance();
        double idledis=id.dis(xs, ys, xe, ye, blocks, count);
        
        annealing ann=new annealing();
        String[][] annealblocks=ann.annealing(moves, xs, ys, xe, ye, blocks, count);
        
        //exchangefar exf=new exchangefar();
        //String[][] exfblocks=exf.exchangefar(moves, xs, ys, xe, ye, blocks, count);
        
        XS xss=new XS();
        double[] newxs=xss.xs(annealblocks, count);
        YS yss=new YS();
        double[] newys=yss.ys(annealblocks, count);
        XE xee=new XE();
        double[] newxe=xee.xe(annealblocks, count);
        YE yee=new YE();
        double[] newye=yee.ye(annealblocks, count);
        
        double idledis2=id.dis(newxs, newys, newxe, newye, annealblocks, count);
        
        String newmoves=out.output(before, after, annealblocks, endend,count);
        
        
        /*
        exchangefar exf=new exchangefar();
        String[][] exfblocks=exf.exchangefar(moves, newxs, newys, newxe, newye, annealblocks, count);
        
        
        double[] newxs1=xss.xs(exfblocks, count);
        double[] newys1=yss.ys(exfblocks, count);
        double[] newxe1=xee.xe(exfblocks, count);
        double[] newye1=yee.ye(exfblocks, count);
        
        exchange ex=new exchange();
        String[][] exchangedblocks=ex.exchange(moves,newxs1, newys1, newxe1, newye1, exfblocks, count);
        
        double[] newxs2=xss.xs(exchangedblocks, count);
        double[] newys2=yss.ys(exchangedblocks, count);
        double[] newxe2=xee.xe(exchangedblocks, count);
        double[] newye2=yee.ye(exchangedblocks, count);
        
        
        String oldmoves=out.output(before, after, exchangedblocks, endend,count);
        
        
        reversemethod r=new reversemethod();
        String[][] revblocks=r.reversemethod(moves, newxs2, newys2, newxe2, newye2,exchangedblocks, count);
        */
        
        
        
        
        long endtime=System.currentTimeMillis();
        long runtime=endtime-starttime;
        System.out.println("Run time: "+runtime+" milli seconds");
        System.out.println("original idle distance: "+idledis);
        System.out.println("optimized idle distance: "+idledis2);
        double savedis=idledis-idledis2;
        System.out.println("save "+savedis);
        
        /*
        wang performance=new wang();
        double[] oldp=performance.Performance(pathname);
        System.out.println("old performance");
        System.out.println("Global distance: "+oldp[0]);
        System.out.println("Global time: "+oldp[1]);
        System.out.println("Idle distance: "+oldp[2]);
        System.out.println("Idle time: "+oldp[3]);
        System.out.println("Printing distance: "+oldp[4]);
        System.out.println("Printing time: "+oldp[5]);
        System.out.println("----------------------------------------------------------");
        
        /*
        double[] newp=performance.Performance("C:/Users/Tian/Desktop/suanfa2.gcode");
        System.out.println("new performance");
        System.out.println("Global distance: "+newp[0]);
        System.out.println("Global time: "+newp[1]);
        System.out.println("Idle distance: "+newp[2]);
        System.out.println("Idle time: "+newp[3]);
        System.out.println("Printing distance: "+newp[4]);
        System.out.println("Printing time: "+newp[5]);
        */
        //System.out.print(count);

	
	
	}   //readfile
        
        
        
        
    
	
}//class
