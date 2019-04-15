
public class XS {
	public double[] xs(String[][] blocks,int count){
		double[] xs=new double[count];
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				//System.out.println(blocks[i][0]);
				xs[i]=Double.parseDouble((String) blocks[i][1].subSequence(blocks[i][1].indexOf("X")+1, blocks[i][1].indexOf("Y")));
			}
			else{
				xs[i]=Double.parseDouble((String) blocks[i][0].subSequence(blocks[i][0].indexOf("X")+1, blocks[i][0].indexOf("Y")));
			}
			//System.out.println(xs[i]);
		}
		
		
		return xs;
		
	}

}
