import java.util.Random;
/**
 * Yuping Tian
 * the basic idea of implement hill climbing is same with simulated annealing
 * the only difference is hill climbing only accept changes when new distance is less than old distance
 * 
 *
 */
public class hillclimbing {
	public String[][] hillclimbing(double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][]; 
		int layer=0;
		int[] nm=new int[999];
		int n=0;
		int save=0;
		int lines=0;
		String[] newblocks=new String[9999999];
		int runnum=0;
		
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				layer++;
				nm[n]=i;
				n++;
				
			}
		}
		
		System.out.println("layer num: "+layer);
		int[] startnum=new int[layer];
		int[] endnum=new int [layer];
		for(int i=0;i<startnum.length;i++){
			startnum[i]=nm[i];
			
			
		} 
		
		for(int i=0;i<endnum.length;i++){
			if(i==startnum.length-1){
				endnum[i]=count-1;
			}
			else{
				endnum[i]=startnum[i+1]-1;
			}
			
		}
		
		hillclimbing hill=new hillclimbing();
		
		int blocklength=0;
		for(int i=0;i<layer;i++){
			
			n=0;
			runnum++;
			int startn=startnum[i];
			if(endnum[i]-startnum[i]>3){
				
				String[][] blocks3=hill.layer(startnum[i], endnum[i], blocks, count, xs, ys, xe, ye,layer);
				
				
				for(int k=startnum[i];k<endnum[i]+1;k++){
					blocks2[k]=new String[blocks3[n].length];
					
					for(int j=0;j<blocks3[n].length;j++){
						blocks2[k][j]=blocks3[n][j];
						
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
		System.out.println(layer);
		
		return blocks2;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public String[][] layer(int startnum,int endnum,String[][] blocks,int count,double[] xs,double[] ys,double[] xe,double[] ye,int layer){
		
		long begintime=System.currentTimeMillis();
		
		
		Random r=new Random();
		ReadFile rf = new ReadFile();
		exchange d=new exchange();
		rev rev=new rev();
		
		String[][] blocks2=new String[endnum-startnum+1][];
		double dis=0;
		int save=0;
		int stn=startnum;
		int lines=0;
		double diszong1=0;
		double diszong2=0;
		int a1=0;
		int a2=0;
		int notrev=0;
		int revnum=0;
		//System.out.println("   ");
		//System.out.println("------------------------------------------------------------------------------------------------------");
		//System.out.println("starttttttt: "+startnum+" enddddddd: "+endnum);
		
		for(int i=0;i<endnum-startnum+1;i++){
			
			
			blocks2[i]=new String[blocks[stn].length];
			for(int j=0;j<blocks[stn].length;j++){
				
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
		/*
		for(int i=0;i<endnum-startnum-1;i++){
			//double distance11=Math.sqrt(Math.abs((xee[i-1] - xss[i])* (xee[i-1] - xss[i])+(yee[i-1] - yss[i])* (yee[i-1] - yss[i])));
			double distance12=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			double distance1=distance12;
			//System.out.println("temp=========="+distance1);
			dis=dis+distance1;
		}
		*/
		
		
		for(int i=0;i<endnum-startnum-1;i++){
			
			
			double distancezong1=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			
			diszong1=diszong1+distancezong1;
			
		}
		dis=diszong1;
		
		int jishu=0;
		
		long fortime=0;
		int revvv=0;
		
		for(int loop=0;loop<10000;loop++){
		
			jishu++;
			
			int eachsave=0;
			double dis2=0;
			double dis1=0;
			double olddis=0;
			
			
			double pifrev=0.5;
			double ifrev=r.nextDouble();
			/////////////////////////////////////////swap///shift//////////////////////////////////////
			
				
			double num=r.nextDouble();
			double p=0.5;
			if(num<p){
				a1++;
				int r1=r.nextInt(endnum-startnum-2)+1;
				int r2=r1+1;
				dis2=0;
				
				for(int i=0;i<endnum-startnum-1;i++){
					double distance1=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
					dis1=dis1+distance1;
						
				}
					
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
					double distance2=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
					dis2=dis2+distance2;
					
				}
				
				double disgap=dis2-dis1;
				
				if(disgap<0){
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
						
					}
					blocks2[r2]=new String[tempr1.length];
					for(int j=0;j<tempr1.length;j++){
						blocks2[r2][j]=tempr1[j];
						
					}
						
						
				}
					
				else{
					xss[r1]=xstemp1;
					yss[r1]=ystemp1;
					xee[r1]=xetemp1;
					yee[r1]=yetemp1;
					xss[r2]=xstemp2;
					yss[r2]=ystemp2;
					xee[r2]=xetemp2;
					yee[r2]=yetemp2;
				}
				
					
				////////////////////////////////////rev//////////////////////////
				if(ifrev<pifrev){
					revvv++;
					
					double origdis=Math.sqrt(Math.abs((xee[r1-1]- xss[r1])* (xee[r1-1] - xss[r1])+(yee[r1-1] - yss[r1])* (yee[r1-1] - yss[r1])))
							+Math.sqrt(Math.abs((xee[r1]- xss[r1+1])* (xee[r1] - xss[r1+1])+(yee[r1] - yss[r1+1])* (yee[r1] - yss[r1+1])));
					double revdis=Math.sqrt(Math.abs((xee[r1-1]- xee[r1])* (xee[r1-1] - xee[r1])+(yee[r1-1] - yee[r1])* (yee[r1-1] - yee[r1])))
							+Math.sqrt(Math.abs((xss[r1]- xss[r1+1])* (xss[r1] - xss[r1+1])+(yss[r1] - yss[r1+1])* (yss[r1] - yss[r1+1])));
					disgap=revdis-origdis;
					if(disgap<0&&blocks2[r1].length>5){
						
						Node[] nodes=rf.read(blocks2[r1]);
						String[] revblock=rev.reverseBlock(nodes);
						
						if(revblock[0]=="0"){
							notrev++;
						}
						else{
							revnum++;
							for(int s=0;s<revblock.length;s++){
								
								blocks2[r1][s]=revblock[s];
								
							}
							double xetemp=xee[r1];
							double yetemp=yee[r1];
							xee[r1]=xss[r1];
							yee[r1]=yss[r1];
							xss[r1]=xetemp;
							yss[r1]=yetemp;
							
							
						}
					}
					else{
						
					}
					
				}
				
					
					
					
			}
				
				
			if(num>=p){
				a2++;
				
				int r1=r.nextInt(endnum-startnum-2)+1;
				int r2=r.nextInt(endnum-startnum-2)+1;
				
				while(r1==r2){
					r1=r.nextInt(endnum-startnum-2)+1;
					r2=r.nextInt(endnum-startnum-2)+1;
					
				}
				
				
				for(int i=0;i<endnum-startnum-1;i++){
					double distance1=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
					dis1=dis1+distance1;
					
				}
				
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
				
				double disgap=dis2-dis1;
				double tt=10/10000;
				
				
				if(disgap<0){
					
					eachsave++;
					olddis=dis;
					
					save++;
					String[] temp=new String[blocks2[r1].length];
					for(int i=0;i<blocks2[r1].length;i++){
						temp[i]=blocks2[r1][i];
					}
					
					blocks2[r1]=new String[blocks2[r2].length];
					for(int j=0;j<blocks2[r2].length;j++){
						blocks2[r1][j]=blocks2[r2][j];
						
					}
					
					blocks2[r2]=new String[temp.length];
					for(int j=0;j<temp.length;j++){
						blocks2[r2][j]=temp[j];
						
					}
					
					
					
					
					}
					
				else{
					
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
				////////////////////////////////////rev//////////////////////////
				if(ifrev<pifrev){
					revvv++;
					
					double origdis=Math.sqrt(Math.abs((xee[r1-1]- xss[r1])* (xee[r1-1] - xss[r1])+(yee[r1-1] - yss[r1])* (yee[r1-1] - yss[r1])))
							+Math.sqrt(Math.abs((xee[r1]- xss[r1+1])* (xee[r1] - xss[r1+1])+(yee[r1] - yss[r1+1])* (yee[r1] - yss[r1+1])));
					double revdis=Math.sqrt(Math.abs((xee[r1-1]- xee[r1])* (xee[r1-1] - xee[r1])+(yee[r1-1] - yee[r1])* (yee[r1-1] - yee[r1])))
							+Math.sqrt(Math.abs((xss[r1]- xss[r1+1])* (xss[r1] - xss[r1+1])+(yss[r1] - yss[r1+1])* (yss[r1] - yss[r1+1])));
					disgap=revdis-origdis;
					if(disgap<0&&blocks2[r1].length>5){
						
						Node[] nodes=rf.read(blocks2[r1]);
						String[] revblock=rev.reverseBlock(nodes);
						
						if(revblock[0]=="0"){
							notrev++;
						}
						else{
							revnum++;
							for(int s=0;s<revblock.length;s++){
								
								blocks2[r1][s]=revblock[s];
								
									}
							double xetemp=xee[r1];
							double yetemp=yee[r1];
							xee[r1]=xss[r1];
							yee[r1]=yss[r1];
							xss[r1]=xetemp;
							yss[r1]=yetemp;
							
							
						}
					}
					else{
						
					}
					
				}
					
			}
				
				
			
			
			
			
			
			
		
			
			for(int i=0;i<blocks2.length;i++){
				for(int j=0;j<blocks2[i].length;j++){
					
				}
			}
		
		for(int i=0;i<endnum-startnum-1;i++){
			double distancez2=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			diszong2=diszong2+distancez2;
			
		}
		
		
		
		}
		
		
		System.out.println("1 "+a1+" 2 "+a2+" rev "+revvv);
		
		return blocks2;

	}
}
