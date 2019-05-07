
/**
 * Yuping Tian
 * there are two steps.
 * the first step is fond the order number of each boundaries in whole lines,
 * the second step is store lines into arrays according to these boundaries
 */


public class block {
	public String[][] block(String[] startline,String[] endline,String[] moves,int count,int moveslength){
		
		String[][] blocks=new String[count][99999]; //the two-dimension array with big enough second dimension
		String[][] blocks2=new String[count][];     //the two-dimension array with undifined second dimension
		
		int i=0;
		int j=0;
		int st=0;
		int en=0;
		int b=0;
		int n=0;
		int m=0;
		
		int[] notnull= new int[count]; //to record number of lines for each move
		
		int start[] = new int[9999];   //the boundaries of start lines
		int end[] = new int[9999];     //the boundaries of end line
		
		
		
		/*
		 * get the boundaries
		 */
		
		while(i<moveslength){
			
				if(moves[i]==startline[j]){
					start[st]=i;
					st++;
					i++;
				}
				
				if(moves[i]==endline[j]){
					end[en]=i;
					en++;
					i++;
					j++;
					if(st==count){
						j--;   
					}
				}
				else{
					i++;
				}
		}

		
		
		/*
		 *then store them into arrays 
		 */
		
		while(b<count){
			n=0;
			if(moves[start[b]-1].contains("Z")){
				blocks[b][n]=moves[start[b]-1];
				
				n++;
				for(m=start[b];m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					
				}
				b++;
			}
			else if(moves[start[b]-2].contains("Z")){
				blocks[b][n]=moves[start[b]-2];
				
				n++;
				for(m=start[b]-1;m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					
				}
				b++;
			}
			else if(moves[start[b]-3].contains("Z")){
				blocks[b][n]=moves[start[b]-3];
				
				n++;
				for(m=start[b]-2;m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					
				}
				b++;
			}
			
			else{
				for(m=start[b];m<=end[b]+2;m++){
				
					blocks[b][n]=moves[m];
					n++;
					
				}
				b++;
			}
		}
		
		
		b=0;
		n=0;
		
		
		/*
		 * an extra operation is needed,
		 * due to firstly the size of arrays' second dimension are set into 9999, find number of lines for each moves
		 * and correct the size of arrays 
		 */
		
		while(b<count){
			n=0;
			while(blocks[b][n]!=null){
				notnull[b]++;    //this array contains the number of lines for each move 
				n++;
				
			}
			
			b++;
		}
		
		b=0;
		n=0;
		
		
		//then store lines of each move into the arrays with well-defined size
		for ( i = 0 ; i < count ; i++ ) {
            blocks2 [ i ] = new String [ notnull[i] ];
            for( j=0 ; j < notnull[i] ; j++) {
                  blocks2 [ i ][ j ] = blocks[i][j];
                 
            }
        }
		
		
		return blocks2;
	}
}
