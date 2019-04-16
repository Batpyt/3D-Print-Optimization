import java.util.Random;

public class annealing {
	public String[][] annealing(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][]; //count=blocks.length=1043/////0-1042
		
		int layer=0;
		int[] nm=new int[999];
		int n=0;
		int save=0;
		int lines=0;
		double t=20;
		String[] newblocks=new String[9999999];
		
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				layer++;
				nm[n]=i;
				n++;
			}
		}//there are 23 layers
		int[] startnum=new int[layer];
		int[] endnum=new int [layer];
		for(int i=0;i<startnum.length;i++){
			startnum[i]=nm[i];
			
			//System.out.println(i+"   "+startnum[i]);
			
		} 
		
		for(int i=0;i<endnum.length;i++){
			if(i==startnum.length-1){
				endnum[i]=count-1;
			}
			else{
				endnum[i]=startnum[i+1]-1;
			}
			//System.out.println(i+"   "+endnum[i]);
		}
		
		annealing annl=new annealing();
		
		int blocklength=0;
		for(int i=0;i<layer;i++){
			n=0;
			int startn=startnum[i];
			if(endnum[i]-startnum[i]>5){
				String[][] blocks3=annl.layer(startnum[i], endnum[i], blocks, count, xs, ys, xe, ye);
				int lines2=0;
				for(int k=startnum[i];k<endnum[i]+1;k++){
					blocks2[k]=new String[blocks3[n].length];
					lines2=0;
					//System.out.println("blocklinesbbbbbbbbbbbbbb: "+k);
					for(int j=0;j<blocks3[n].length;j++){
						blocks2[k][j]=blocks3[n][j];
						lines++;
						lines2++;
					}
					n++;
					
				}
				blocklength=blocklength+blocks3.length;
				
			}
			else{
				for(int k=startnum[i];k<endnum[i]+1;k++){
					blocks2[k]=new String[blocks[startn].length];
					for(int j=0;j<blocks[startn].length;j++){
						blocks2[k][j]=blocks[startn][j];
						
					}
					startn++;
					
				}
			}
			
			
		}
		
		return blocks2;
		
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public String[][] layer(int startnum,int endnum,String[][] blocks,int count,double[] xs,double[] ys,double[] xe,double[] ye){
		
		long begintime=System.currentTimeMillis()/1000;
		
		exchange d=new exchange();
		Random r=new Random();
		System.out.println("starttttttt: "+startnum+" enddddddd: "+endnum);
		String[][] blocks2=new String[endnum-startnum+1][];
		double dis=0;
		int save=0;
		int stn=startnum;
		int lines=0;
		double diszong1=0;
		double diszong2=0;
		double finaldis=0;
		
		
		for(int i=0;i<endnum-startnum+1;i++){
			//System.out.println("11111111111111111111111111 "+stn+" "+blocks[stn].length);
			
			blocks2[i]=new String[blocks[stn].length];
			for(int j=0;j<blocks[stn].length;j++){
				//System.out.println("22222 "+j);
				blocks2[i][j]=blocks[stn][j];
				lines++;
			}
			stn++;
		}
		
		stn=startnum;
		double[] xss=new double[endnum-startnum];
		for(int i=0;i<xss.length;i++){
			xss[i]=xs[stn];
			
			stn++;
		}
		stn=startnum;
		double[] yss=new double[endnum-startnum];
		for(int i=0;i<yss.length;i++){
			yss[i]=ys[stn];
			stn++;
		}
		stn=startnum;
		double[] yee=new double[endnum-startnum];
		for(int i=0;i<yee.length;i++){
			yee[i]=ye[stn];
			stn++;
		}
		stn=startnum;
		double[] xee=new double[endnum-startnum];
		for(int i=0;i<xee.length;i++){
			xee[i]=xe[stn];
			
			stn++;
		}
		
		for(int i=0;i<endnum-startnum-1;i++){
			
			//double distance21=Math.sqrt(Math.abs((xee[i-1] - xss[i])* (xee[i-1] - xss[i])+(yee[i-1] - yss[i])* (yee[i-1] - yss[i])));
			double distancezong1=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			//double distance1=distance12;
			//System.out.println("temp=========="+distance1);
			diszong1=diszong1+distancezong1;
			//System.out.println(diszong1+" "+distancezong1+" "+i);
		}
		dis=diszong1;
		
		int jishu=0;
		
		long fortime=0;
		
		int tstart=5000;
		double tend=0.01;
		double t=tstart;
		double cooling=0.99;
		long annealtime=0;
		
		//bgm: while(t>tend){
			long timestart=System.currentTimeMillis();
			for(int loop=0;loop<15000;loop++){
			//while(fortime<3){
					
					long forbegintime=System.currentTimeMillis()/1000;
					int eachsave=0;
					double dis2=0;
					double olddis=0;
					
					
					int r1=r.nextInt(endnum-startnum-2)+1;
					int r2=r.nextInt(endnum-startnum-2)+1;
					
					while(r1==r2){
						r1=r.nextInt(endnum-startnum-2)+1;
						r2=r.nextInt(endnum-startnum-2)+1;
						
					}
					//System.out.println("r1: "+r1+" r2 "+r2);
					
					
					double xstemp1=xss[r1];
					double xetemp1=xee[r1]; 
					double ystemp1=yss[r1];
					double yetemp1=yee[r1];
					double xstemp2=xss[r2];
					double xetemp2=xee[r2];
					double ystemp2=yss[r2];
					double yetemp2=yee[r2];
					
					
					xss[r1]=xstemp2;
					yss[r1]=ystemp2;
					xee[r1]=xetemp2;
					yee[r1]=yetemp2;
					xss[r2]=xstemp1;
					yss[r2]=ystemp1;
					xee[r2]=xetemp1;
					yee[r2]=yetemp1;
					
					
					for(int i=0;i<endnum-startnum-1;i++){
						double distancez2=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
						dis2=dis2+distancez2;
						
					}
					
					double disgap=dis2-dis;
					double tt=10/10000;
					double rr=r.nextDouble();
					
					if(disgap<0||Math.exp(-disgap/t)>r.nextDouble()){
					//if(disgap<20-tt*loop){
						eachsave++;
						olddis=dis;
						dis=dis2;
						save++;
						String[] temp=new String[blocks2[r1].length];
						for(int i=0;i<blocks2[r1].length;i++){
							temp[i]=blocks2[r1][i];
						}
						
						blocks2[r1]=new String[blocks2[r2].length];
						for(int j=0;j<blocks2[r2].length;j++){
							blocks2[r1][j]=blocks2[r2][j];
							//System.out.println(blocks2[r1][j]);
						}
						
						blocks2[r2]=new String[temp.length];
						for(int j=0;j<temp.length;j++){
							blocks2[r2][j]=temp[j];
							//System.out.println(blocks2[r2][j]);
						}
						
						jishu++;
						t=t*cooling;
						//System.out.println(t);
					}
					
					else{
						
						//System.out.println("no save: "+dis+"  "+dis2);
						//System.out.println("qwe");
						jishu++;
						xss[r1]=xstemp1;
						yss[r1]=ystemp1;
						xee[r1]=xetemp1;
						yee[r1]=yetemp1;
						xss[r2]=xstemp2;
						yss[r2]=ystemp2;
						xee[r2]=xetemp2;
						yee[r2]=yetemp2;
						
						
					}
					
					
					
					long forendtime=System.currentTimeMillis()/1000;
					fortime=fortime+forendtime-forbegintime;
				}
			
			long timeend=System.currentTimeMillis();
			annealtime=annealtime+timeend-timestart;
			//System.out.println(annealtime);
			/*
			if(annealtime>2000){
				break bgm;
			}
			*/
			
		//}
			
			
		
		long endtime=System.currentTimeMillis()/1000;
		long time=endtime-begintime;
		int llll=0;
		
		
		for(int i=0;i<blocks2.length;i++){
			for(int j=0;j<blocks2[i].length;j++){
				llll++;
			}
		}
		
		for(int i=0;i<endnum-startnum-1;i++){
			double distancez2=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			diszong2=diszong2+distancez2;
			
		}
		
		
		System.out.println("saveeeeeeee: "+save);
		//System.out.println(diszong1+" "+distancezong1+" "+i);
		System.out.println("zongzzzzzzz111 "+diszong1+" diszong2222222: "+dis+" "+t);
		//System.out.println("timememememeeem: "+time);
		//System.out.println("old dis: "+" new dis: "+dis);
		//System.out.println("blocks::::::::::::::: "+blocks2.length);
		//System.out.println("linessssssssssssssssssssssssssss: "+lines+" lllllll "+llll);
		
		
		
		return blocks2;
		
	}

}
