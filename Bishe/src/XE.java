
public class XE {
	public double[] xe(String[][] blocks,int count){
		double[] xe=new double[count];
		for(int i=0;i<count;i++){
			//System.out.println(blocks[i][blocks[i].length-3]);
			xe[i]=Double.parseDouble((String) blocks[i][blocks[i].length-3].subSequence(blocks[i][blocks[i].length-3].indexOf("X")+1,blocks[i][blocks[i].length-3].indexOf("Y")));
			//System.out.println(xe[i]);
		}
		
		
		
		
		return xe;
		
	}

}
