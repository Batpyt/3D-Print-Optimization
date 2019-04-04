import java.util.Random;

public class exchange {
	public String[][] exchange(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		//String[] newmoves=new String[moves.length];
		String[][] blocks2=new String[count][];
		int loop=0;
		int booleanz=0;
		
		while(loop<1000){
			Random r=new Random();
			int r1=r.nextInt(count-2)+1;
			
			
			
			
			int r2=r1+1;
			
			//System.out.println(r1+" random "+r2+" "+count);
			/*
			System.out.println("before: "+"xs1: "+xs[r1]+" ys1: "+ys[r1]+" xe1: "+xe[r1]+" ye1: "+ye[r1]
	 				+" xs2: "+xs[r2]+" ys2: "+ys[r2]+" xe2: "+xe[r2]+" ye2: "+ye[r2]);
			 */
			
			if(blocks[r1][0].contains("Z")||blocks[r2][0].contains("Z")){
				booleanz++;
			}
			
			if(booleanz==0){
				exchange dis=new exchange();
				double distance1=dis.distance(r1, r2, xs, ys, xe, ye);
				//System.out.println("distance1: "+distance1);
				
				int temp1=r2;
				int temp2=r1;
				double distance2=dis.distance(temp1, temp2, xs, ys, xe, ye);
				//System.out.println("distance2: "+distance2);
				//System.out.println(blocks[1][0]+"blocks");
				
				if(distance2<distance1){
					//System.out.println("1");
					//System.out.println(xs[r1]+" "+xe[r2]);
					String[] tempr1=new String[blocks[r1].length];
					for(int i=0;i<blocks[r1].length;i++){
						tempr1[i]=blocks[r1][i];
					}
					String[] tempr2=new String[blocks[r2].length];
					for(int i=0;i<blocks[r2].length;i++){
						tempr2[i]=blocks[r2][i];
					}
					
					for ( int i = 0 ; i < r1 ; i++ ) {
						blocks2 [ i ] = new String [ blocks[i].length ];    //动态创建第二维
						for(int j=0 ; j <blocks[i].length; j++) {
							blocks2 [ i ][ j ] = blocks[i][j]; 
						}
					}
					
					blocks2[r1]=new String[blocks[r2].length];
					for(int j=0;j<blocks[r2].length;j++){
						blocks2[r1][j]=tempr2[j];
					}
					blocks2[r2]=new String[blocks[r1].length];
					for(int j=0;j<blocks[r1].length;j++){
						blocks2[r2][j]=tempr1[j];
					}
					
					for ( int i = r2+1 ; i < count ; i++ ) {
						blocks2 [ i ] = new String [ blocks[i].length ];    //动态创建第二维
						for(int j=0 ; j <blocks[i].length; j++) {
							blocks2 [ i ][ j ] = blocks[i][j]; 
						}
					}
				}
				loop++;
			}
			else{
				loop++;
			}
			}
			
		
		return blocks2;
	}
	
	
	public double distance(int r1, int r2,double[] xs,double[] ys,double[] xe,double[] ye){
		double xst1=xs[r1-1];
		double yst1=ys[r1-1];
		double xet1=xe[r1-1];
		double yet1=ye[r1-1];
		
		double xst2=xs[r1];
		double yst2=ys[r1];
		double xet2=xe[r1];
		double yet2=ye[r1];
		
		double xst3=xs[r2];
		double yst3=ys[r2];
		double xet3=xe[r2];
		double yet3=ye[r2];
		
		double xst4=xs[r2+1];
		double yst4=ys[r2+1];
		double xet4=xe[r2+1];
		double yet4=ye[r2+1];
		
		double distance1=Math.sqrt(Math.abs((xet1 - xst2)* (xet1 - xst2)+(yet1 - yst2)* (yet1 - yst2)));
		double distance2=Math.sqrt(Math.abs((xet3 - xst4)* (xet3 - xst4)+(yet3 - yst4)* (yet3 - yst4)));
		double distance=distance1+distance2;
		
		return distance;
	}

}
