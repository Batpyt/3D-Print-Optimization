import java.util.Random;

public class exchange {
	public String[][] exchange(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		//String[] newmoves=new String[moves.length];
		String[][] blocks2=new String[count][];
		//int loop=0;
		int booleanz=0;
		int savenum=0;
		
		for(int i=0;i<count;i++){
			blocks2[i]=new String [blocks[i].length];
			for(int j=0;j<blocks[i].length;j++){
				blocks2[i][j]=blocks[i][j];
			}
		}
		
		for(int loop=0;loop<5000;loop++){
			
			Random r=new Random();
			int r1=r.nextInt(count-3)+1;
			booleanz=0;
			
			
			
			int r2=r1+1;
			
			if(blocks[r2][0].contains("Z")){
				//System.out.println("contains Z   "+blocks[r2][0]+"    "+loop);
				booleanz++;
			}
			
			if(booleanz==0){
				exchange dis=new exchange();
				int save=dis.distance(r1, r2, xs, ys, xe, ye);
				if(save==1){
					savenum++;
					String[] tempr1=new String[blocks2[r1].length];
					for(int i=0;i<blocks2[r1].length;i++){
						tempr1[i]=blocks2[r1][i];
					}
					String[] tempr2=new String[blocks2[r2].length];
					for(int i=0;i<blocks2[r2].length;i++){
						tempr2[i]=blocks2[r2][i];
					}
					blocks2[r1]=new String[tempr2.length];
					for(int j=0;j<tempr2.length;j++){
						blocks2[r1][j]=tempr2[j];
						//System.out.println(blocks2[r1][j]);
					}
					blocks2[r2]=new String[tempr1.length];
					for(int j=0;j<tempr1.length;j++){
						blocks2[r2][j]=tempr1[j];
						//System.out.println(blocks2[r2][j]);
					}
					
					double tempxs=xs[r1];
					double tempxe=xe[r1];
					double tempys=ys[r1];
					double tempye=ye[r1];
					xs[r1]=xs[r2];
					xe[r1]=xe[r2];
					ys[r1]=ys[r2];
					ye[r1]=ye[r2];
					xs[r2]=tempxs;
					xe[r2]=tempxe;
					ys[r2]=tempys;
					ye[r2]=tempye;
					
				}
				else{
					//System.out.println("2  "+loop);
					//loop++;
					
				}
				
			}
			
		}
			
		System.out.println("saveeeee: "+savenum);
		return blocks2;
	}
	
	
	public int distance(int r1, int r2,double[] xs,double[] ys,double[] xe,double[] ye){
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
		
		double distance11=Math.sqrt(Math.abs((xet1 - xst2)* (xet1 - xst2)+(yet1 - yst2)* (yet1 - yst2)));
		double distance12=Math.sqrt(Math.abs((xet3 - xst4)* (xet3 - xst4)+(yet3 - yst4)* (yet3 - yst4)));
		double distance13=Math.sqrt(Math.abs((xet2 - xst3)* (xet2 - xst3)+(yet2 - yst3)* (yet2 - yst3)));
		double distance1=distance11+distance12+distance13;
		
		double distance21=Math.sqrt(Math.abs((xet1 - xst3)* (xet1 - xst3)+(yet1 - yst3)* (yet1 - yst3)));
		double distance22=Math.sqrt(Math.abs((xet2 - xst4)* (xet2 - xst4)+(yet2 - yst4)* (yet2 - yst4)));
		double distance23=Math.sqrt(Math.abs((xet3 - xst2)* (xet3 - xst2)+(yet3 - yst2)* (yet3 - yst2)));
		double distance2=distance21+distance22+distance23;
		
		int save=0;
		if(distance2<distance1){
			save=1;
		}
		else{
			save=0;
		}
		
		return save;
	}

}
