
public class idledistance {
	public double dis(double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		double totaldis=0;
		int layer=0;
		int[] nm=new int[999];
		int n=0;
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
		
		idledistance id=new idledistance();
		
		for(int i=0;i<layer;i++){
			double layerdis=id.layerdis(startnum[i], endnum[i], xs, ys, xe, ye);
			totaldis=totaldis+layerdis;
		}
		
		
		
		
		
		return totaldis;
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public double layerdis(int startnum,int endnum,double[] xs,double[] ys,double[] xe,double[] ye){
		double layerdis=0;
		int stn=0;
		
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
			double distance1=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			layerdis=layerdis+distance1;
		}
		
		
		
		
		
		return layerdis;
		
	}

}
