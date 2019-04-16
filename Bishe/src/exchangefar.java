import java.util.Random;

public class exchangefar {
	public String[][] exchangefar(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][]; //count=blocks.length=1043/////0-1042
		//String[][] blocks3=new String[count][];
		//System.out.println(count+" "+blocks.length);
		int layer=0;
		int[] nm=new int[999];
		int n=0;
		int save=0;
		int lines=0;
		String[] newblocks=new String[9999999];
		//System.out.println(blocks[1042][0]);
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				layer++;
				nm[n]=i;
				n++;
				//System.out.println(i+"  "+blocks[i][0]);
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
		
		exchangefar annl=new exchangefar();
		
		int blocklength=0;
		for(int i=0;i<layer;i++){
			//System.out.println("layer:yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy "+i+" "+blocks[startnum[i]][0]);
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
					//System.out.println("lineeeeeeeeeeeeeeeeeeeeee: "+lines2+" block length: "+blocks3[n].length);
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
		//System.out.println("blocks::::::::::::::: "+blocks2.length);
		//System.out.println("lines::::::::::::::::::::::"+lines);
		//System.out.println("blocklinesbbbbbbbbbbbbbb: "+blocklength);
		
		
		
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
		/*
		for(int i=0;i<endnum-startnum-1;i++){
			//double distance11=Math.sqrt(Math.abs((xee[i-1] - xss[i])* (xee[i-1] - xss[i])+(yee[i-1] - yss[i])* (yee[i-1] - yss[i])));
			double distance12=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			double distance1=distance12;
			//System.out.println("temp=========="+distance1);
			dis=dis+distance1;
		}
		*/
		
		//double olddis=dis;
		//System.out.println("dis=========="+dis+"startnum======="+startnum);
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
		//for(int loop=0;loop<12000;loop++){
		while(fortime<1	){
			
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
			
			
			//int iff=1;
			if(dis2<dis){
				eachsave++;
				olddis=dis;
				dis=dis2;
				//dis=dis2;
				save++;
				//System.out.println(blocks2[r1][0]);
				//System.out.println(blocks2[r2][0]);
				//System.out.println(xss[r1]);
				//System.out.println(xss[r2]);
				//System.out.println("old dis: "+olddis+" new dis: "+dis2);
				//System.out.println("1111111111111111 "+distance11+" "+distance12+" "+distance13+" "+distance14);
				//System.out.println("2222222222222222 "+distance21+" "+distance22+" "+distance23+" "+distance24);
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
			
			
			
			/*
			if(jishu==999){
				System.out.println("with 1000 iterations: "+save);
				eachsave=0;
			}
			if(loop==1999){
				System.out.println("with 2000 iterations: "+save);
				eachsave=0;
			}
			if(loop==2999){
				System.out.println("with 3000 iterations: "+save);
				eachsave=0;
			}
			if(loop==3999){
				System.out.println("with 4000 iterations: "+save);
				eachsave=0;
			}
			if(loop==4999){
				System.out.println("with 5000 iterations: "+save);
				eachsave=0;
			}
			if(loop==5999){
				System.out.println("with 6000 iterations: "+save);
				eachsave=0;
			}
			if(loop==6999){
				System.out.println("with 7000 iterations: "+save);
				eachsave=0;
			}
			if(loop==7999){
				System.out.println("with 8000 iterations: "+save);
				eachsave=0;
			}
			if(loop==8999){
				System.out.println("with 9000 iterations: "+save);
				eachsave=0;
			}
			if(loop==9999){
				System.out.println("with 10000 iterations: "+save);
				eachsave=0;
			}
			if(loop==10999){
				System.out.println("with 11000 iterations: "+save);
				eachsave=0;
			}
			if(loop==119999){
				System.out.println("with 12000 iterations: "+save);
				eachsave=0;
			}
			*/
			
		}
		
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
		System.out.println("zongzzzzzzz111 "+diszong1+" diszong2222222: "+dis);
		System.out.println("timememememeeem: "+time);
		//System.out.println("old dis: "+" new dis: "+dis);
		//System.out.println("blocks::::::::::::::: "+blocks2.length);
		//System.out.println("linessssssssssssssssssssssssssss: "+lines+" lllllll "+llll);
		
		
		
		return blocks2;
		
	}

}
