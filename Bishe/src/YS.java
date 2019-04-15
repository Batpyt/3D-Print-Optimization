
public class YS {
	public double[] ys(String[][] blocks,int count){
		double[] ys=new double[count];
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				//System.out.println(blocks[i][0]);
				ys[i]=Double.parseDouble((String) blocks[i][1].subSequence(blocks[i][1].indexOf("Y")+1, blocks[i][1].indexOf("F")));
			}
			else{
				ys[i]=Double.parseDouble((String) blocks[i][0].subSequence(blocks[i][0].indexOf("Y")+1, blocks[i][0].indexOf("F")));
			}
			//System.out.println(ys[i]);
		}
		
		
		return ys;
		
	}

}
