import java.util.Random;

public class exchangetwo {
	public String [][] exchangetwo(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][];
		Random r=new Random();
		int size=5;
		int loop=0;
		
		for(int i=0;i<count;i++){
			blocks2[i]=new String [blocks[i].length];
			for(int j=0;j<blocks[i].length;j++){
				blocks2[i][j]=blocks[i][j];
			}
		}
		
		while(loop<1000){
			int booleanz=0;
			
			int r1=r.nextInt(count-2*size);
			int r2=r1+size;
			int tr1=r1;
			int tr2=r2;
			System.out.println(r1+" "+r2);
			
			for(int i=0;i<2*size;i++){
				if(blocks[tr1][0].contains("Z")){
					booleanz++;
					tr1++;
				}
				
				else{
					tr1++;
				}
			}
			
			for(int i=0;i<size;i++){
				if(blocks[tr2][0].contains("Z")){
					booleanz++;
					tr2++;
				}
				
				else{
					tr2++;
				}
			}
			
			tr1=r1;
			tr2=r2;
			
			if(booleanz!=0){
				System.out.println("containss Z#####################################");
				loop++;
			}
			
			if(booleanz==0){
				//System.out.println(tr1+" "+tr2);
				exchange dis=new exchange();
				int save=dis.distance(r1, r2, xs, ys, xe, ye);
				
				
				if(save==1){
					String[][] tempr1=new String[10][];
					String[][] tempr2=new String[10][];
					
					for ( int i = 0 ; i < size ; i++ ) {
						System.out.println("time: ###########################"+i);
						tempr1 [ i ] = new String [ blocks[tr1].length ];    //��̬�����ڶ�ά
						//System.out.println("tr1:"+tr1 );
						//System.out.println("blocklength: ##############"+blocks[tr1].length);
						//System.out.println("templength: ##############"+tempr1[i].length);
						for(int j=0 ; j <blocks[tr1].length; j++) {
							
							tempr1 [ i ][ j ] = blocks[tr1][j]; 
							System.out.println(tempr1[i][j]);
							//System.out.println(blocks[tr1][j]);
						}
						tr1++;
					}
					for ( int i = 0 ; i < size ; i++ ) {
						//System.out.println("time: !!!!!!!!!!!!!!!!!!!!!!!!!!"+i);
						tempr2 [ i ] = new String [ blocks[tr2].length ];    //��̬�����ڶ�ά
						//System.out.println("blocklength: ##############"+blocks[tr2].length);
						//System.out.println("templength: ##############"+tempr2[i].length);
						for(int j=0 ; j <blocks[tr2].length; j++) {
							tempr2 [ i ][ j ] = blocks[tr2][j]; 
							System.out.println(tempr2[i][j]);
						}
						tr2++;
					}
					
					
					tr1=r1;
					tr2=r2;
					int t2=0;
					int t1=0;
					for ( int i = r1 ; i < tr2 ; i++ ) {
						blocks2 [ i ] = new String [ tempr2[t2].length ];    //��̬�����ڶ�ά
						for(int j=0 ; j <tempr2[t2].length; j++) {
							blocks2 [ i ][ j ] = tempr2[t2][j]; 
						}
						t2++;
					}
					tr1=r1;
					tr2=r2;
					t2=0;
					t1=0;
					for ( int i = tr2 ; i < tr2+size ; i++ ) {
						blocks2 [ i ] = new String [ tempr1[t1].length ];    //��̬�����ڶ�ά
						for(int j=0 ; j <tempr1[t1].length; j++) {
							blocks2 [ i ][ j ] = tempr1[t1][j]; 
						}
						t1++;
					}
					
					
					
					loop++;
				}
				
				else{
					System.out.println("not save nonoonooonononononononon");
					loop++;
				}
			
			
			
			}
		
		
		}
		
		
		/*
		int w=0;
		while(w<count){
			if(blocks[w][0].contains("Z")){
				System.out.println(blocks[w][0]+" "+w);
				w++;
			}
			else{
				w++;
			}
		}
		*/

		return blocks2;
		
	}

}