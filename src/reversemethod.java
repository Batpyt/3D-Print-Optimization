
public class reversemethod {
	public String [][] reversemethod(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][];
		reversemethod dis=new reversemethod();
		rev rr=new rev();
		int booleansave=0;
		
		for(int loop=0;loop<count;loop++){
			//System.out.println("loop: "+loop);
			String[] revblock=rr.reverseBlock(loop);
			if(revblock[0]=="0"){
				System.out.println(loop+" don't reverse");
			}
			else{
				if(loop==0){
					booleansave=dis.revdistancefirst(xs[0], xs[1], ys[0], ys[1], xe[0], xe[1], ye[0], ye[1]);
					if(booleansave==1){
						blocks2[0]=new String[revblock.length];
						for(int i=0;i<revblock.length;i++){
							blocks2[0][i]=revblock[i];
						}
						
						for ( int i = 1 ; i < count ; i++ ) {
							blocks2 [ i ] = new String [ blocks[i].length ];    //动态创建第二维
							for(int j=0 ; j <blocks[i].length; j++) {
								blocks2 [ i ][ j ] = blocks[i][j]; 
							}
						}
					}
					else if(booleansave==0){
						System.out.println("no save");
					}
				}
				else{
					booleansave=dis.revdistance(xs[loop-1], xs[loop], xs[loop+1], ys[loop-1], ys[loop], ys[loop+1], xe[loop-1], 
							xe[loop], xe[loop+1], ye[loop-1], ye[loop], ye[loop+1]);
					if(booleansave==1){
						System.out.println(loop+" save");
						blocks2[loop]=new String[revblock.length];
						
						for ( int i = 0 ; i < loop ; i++ ) {
							blocks2 [ i ] = new String [ blocks[i].length ];    //动态创建第二维
							for(int j=0 ; j <blocks[i].length; j++) {
								blocks2 [ i ][ j ] = blocks[i][j]; 
							}
						}
						
						for(int i=0;i<revblock.length;i++){
							blocks2[loop][i]=revblock[i];
							//System.out.println(blocks[loop][i]);
						}
						
						for ( int i = loop+1 ; i < count ; i++ ) {
							blocks2 [ i ] = new String [ blocks[i].length ];    //动态创建第二维
							for(int j=0 ; j <blocks[i].length; j++) {
								blocks2 [ i ][ j ] = blocks[i][j]; 
							}
						}
						
						
					}
					else{
						System.out.println(loop+" not save");
					}
				}
				
			}
		
		
		}
	
		
		
		for(int j=0;j<blocks2[162].length;j++){
			//System.out.println(blocks2[162][j]);
		}
	
		
		
		for(int i=0;i<count;i++){
			for(int j=0;j<blocks2[i].length;j++){
				System.out.println(blocks2[i][j]);
			}
		}
		
		return blocks2;
		
	}
	
	public int revdistance(double xs0,double xs1,double xs2,double ys0,double ys1,double ys2,double xe0,double xe1,double xe2,double ye0,double ye1,double ye2){
				
		double dis1=0;
		double dis2=0;
		int booleansave=0;
		double distance11=Math.sqrt(Math.abs((xe0 - xs1)* (xe0 - xs1)+(ye0 - ys1)* (ye0 - ys1)));
		double distance12=Math.sqrt(Math.abs((xe1 - xs2)* (xe1 - xs2)+(ye1 - ys2)* (ye1 - ys2)));
		
		double distance21=Math.sqrt(Math.abs((xe0 - xe1)* (xe0 - xe1)+(ye0 - ye1)* (ye0 - ye1)));
		double distance22=Math.sqrt(Math.abs((xs1 - xs2)* (xs1 - xs2)+(ys1 - ys2)* (ys1 - ys2)));
		
		dis1=distance11+distance12;
		dis2=distance21+distance22;
		
		if(dis1>dis2){
			booleansave=1;
		}
		else{
			booleansave=0;
		}
		
		return booleansave;
		
	}
	public int revdistancefirst(double xs0,double xs1,double ys0,double ys1,double xe0,double xe1,double ye0,double ye1){
				
		double distance1=Math.sqrt(Math.abs((xe0 - xs1)* (xe0 - xs1)+(ye0 - ys1)* (ye0 - ys1)));	
		double distance2=Math.sqrt(Math.abs((xs0 - xs1)* (xs0 - xs1)+(ys0 - ys1)* (ys0 - ys1)));
		int booleansave=0;
		if(distance1>distance2){
			booleansave=1;
		}
		else{
			booleansave=0;
		}
		
		
		
		
		
		
		
		return booleansave;
		
	}

}
