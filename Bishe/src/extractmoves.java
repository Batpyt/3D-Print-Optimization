import java.io.IOException;

/**
 * Yuping Tian
 * this class is to extract lines of moves from whole lines of gcode.
 * meanwhile the first step optimization is done: remove many empty moves that gathering together.
 **/


public class extractmoves {
	public String[] extractmove(int linesno,String[] s){
		int t=0;
		int m=0;
		
		/*
		 * this is the first step of optimization 
		 * pseudo code
		 * if current line contains F without E, and its next line contains F without E and Z
		 * 		remove the next line
		 * end if
		 */
		for(int i=0;i<linesno;i++){
			if(!s[i].contains("E")&&s[i].contains("F")&&!s[i+1].contains("E")&&s[i+1].contains("F")&&!s[i].contains("Z")){
				for(int j=i+2;j<linesno;j++){
					s[i+1]=s[j];
					
				}
				
				i--;
			}
		}
		
		
		/*
		 * count the number of useful lines
		 */
		while(t<linesno){      
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5 F5000")){
        		m++;
        	}
        	t++;
        }
		
        t=0;
        String[] move=new String[m+1]; //create new array with the number just count
        m=0;
        
        while(t<linesno){
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5 F5000")){  //distinguish lines from moves 
        		//store lines into a new string
        		move[m]=s[t];  
        		
        		m++; 
        		
        	}
        	
        	t++;
        }
        move[m]="end";
        
        
        return move;
			
	}
		
    
		
}


