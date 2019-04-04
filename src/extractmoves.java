import java.io.IOException;

public class extractmoves {
	public String[] extractmove(int linesno,String[] s){
		int t=0;
		int m=0;
		
		while(t<linesno){      //get how many moves are in the Gcode fle
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5")){
        		//move[m]=s[t];
        		//System.out.println(move[m]+" no: "+m);
        		m++;
        		
        	}
        	t++;
        }
		//System.out.println(m);
        t=0;
        String[] move=new String[m+1];
        m=0;
        
        while(t<linesno){
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5")){  //distinguish lines from moves 
        		move[m]=s[t];  //store them  into a new string
        		//System.out.println(move[m]+" no: "+m);
        		m++;      //以后用要记得减1
        		
        	}
        	t++;
        }
        move[m]="end";
        //System.out.println(move[m]+" no: "+m);
        //System.out.println(move[m-1]+" no: "+m);
        return move;
			
        }
		
    
		
	}


