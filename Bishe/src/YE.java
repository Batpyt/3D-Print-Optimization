
public class YE {
	public double[] ye(String[][] blocks,int count){
		double[] ye=new double[count];
		
		for(int i=0;i<count;i++){
			if(blocks[i][blocks[i].length-3].contains("E")){
				ye[i]=Double.parseDouble((String) blocks[i][blocks[i].length-3].subSequence(blocks[i][blocks[i].length-3].indexOf("Y")+1,blocks[i][blocks[i].length-3].indexOf("E")));
			}
			else if(!blocks[i][blocks[i].length-3].contains("E")){
				ye[i]=Double.parseDouble((String) blocks[i][blocks[i].length-3].subSequence(blocks[i][blocks[i].length-3].indexOf("Y")+1,blocks[i][blocks[i].length-3].indexOf("F")));
			}
			
			//System.out.println(ye[i]);
		}
		
		
		
		
		return ye;
	}

}
