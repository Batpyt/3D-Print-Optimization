
public class block {
	public String[][] block(String[] startline,String[] endline,String[] moves,int count,int moveslength){
		String[][] blocks=new String[count][99999];
		String[][] blocks2=new String[count][];
		//String[][] blocks=null;
		int i=0;
		int j=0;
		int st=0;
		int en=0;
		int b=0;
		int n=0;
		int m=0;
		int[] notnull= new int[count];
		int start[] = new int[9999];
		int end[] = new int[9999];
		
		while(i<moveslength){{
			//while(st<count){
			
			
				if(moves[i]==startline[j]){
					//System.out.println("while "+st);
					start[st]=i;
					st++;
					i++;
					//st++;
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
		
	}
		//System.out.println(start[0]);
		//System.out.println(moves[start[0]-1]);
		
		
		while(b<count){
			n=0;
			if(moves[start[b]-1].contains("Z")){
				blocks[b][n]=moves[start[b]-1];
				//System.out.println("Z");
				n++;
				for(m=start[b];m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					//System.out.println(b+" "+n+" "+m);
				}
				b++;
			}
			else if(moves[start[b]-2].contains("Z")){
				blocks[b][n]=moves[start[b]-2];
				//System.out.println(b+" isisisiissisisi "+blocks[b][n]);
				n++;
				for(m=start[b]-1;m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					//System.out.println(b+" "+n+" "+m);
				}
				b++;
			}
			else if(moves[start[b]-3].contains("Z")){
				blocks[b][n]=moves[start[b]-3];
				//System.out.println(b+" isisisiissisisi "+blocks[b][n]);
				//System.out.println("Z");
				n++;
				for(m=start[b]-2;m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					n++;
					//System.out.println(b+" "+n+" "+m);
				}
				b++;
			}
			else if(moves[start[b]-4].contains("Z")){
				blocks[b][n]=moves[start[b]-4];
				//System.out.println(b+" isisisiissisisi "+blocks[b][n]);
				//System.out.println("Z");
				n++;
				for(m=start[b]-3;m<=end[b]+2;m++){
					
					blocks[b][n]=moves[m];
					//System.out.println(blocks[b][n]);
					n++;
					//System.out.println(b+" "+n+" "+m);
				}
				b++;
			}
			else{
				for(m=start[b];m<=end[b]+2;m++){
				
					blocks[b][n]=moves[m];
					n++;
					//System.out.println(b+" "+n+" "+m);
				}
				b++;
			}
		}
		
		
		b=0;
		n=0;
		//System.out.println(j+" "+count);
		
		
		while(b<count){
			n=0;
			while(blocks[b][n]!=null){
				notnull[b]++;
				n++;
				
			}
			//System.out.println(notnull[b]+" "+b);
			b++;
		}
		
		b=0;
		n=0;
		
		
		for ( i = 0 ; i < count ; i++ ) {
            blocks2 [ i ] = new String [ notnull[i] ];    //动态创建第二维
            for( j=0 ; j < notnull[i] ; j++) {
                  blocks2 [ i ][ j ] = blocks[i][j]; 
                  //System.out.println(blocks2[i][j]);
            }
        }
		
		/*
		for(int g=0;g<blocks2[1360].length;g++){
			System.out.println(blocks2[1360][g]);
		}
		*/
		
		return blocks2;
	}
}
