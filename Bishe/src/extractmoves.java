import java.io.IOException;

public class extractmoves {
	public String[] extractmove(int linesno,String[] s){
		int t=0;
		int m=0;
		int length=linesno;
		int howmany=0;
		
		for(int i=0;i<linesno;i++){
			if(!s[i].contains("E")&&s[i].contains("F")&&!s[i+1].contains("E")&&s[i+1].contains("F")&&!s[i].contains("Z")){
				for(int j=i+2;j<linesno;j++){
					s[i+1]=s[j];
					
				}
				howmany++;
				//System.out.println(s[i]);
				i--;
			}
		}
		
		//System.out.println(howmany);
		
		while(t<linesno){      //get how many moves are in the Gcode fle
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5 F5000")){
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
        	if((s[t].contains("G92")||s[t].contains("G1"))&&!s[t].contains("Z5 F5000")){  //distinguish lines from moves 
        		move[m]=s[t];  //store them  into a new string
        		//System.out.println(move[m]+" no: "+m);
        		m++;      //以后用要记得减1
        		
        	}
        	
        	t++;
        }
        move[m]="end";
        /*
        for(int i=0;i<20;i++){
        	System.out.println(move[i]+" no "+i);
        }
        */
        //System.out.println(move[m-1]+" no: "+m);
        return move;
			
        }
		
    
		
	}


