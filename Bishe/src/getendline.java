
/*
 * this class is change the size of array that storing start line into correct size
 */

public class getendline {
	public String[] getend(String endline[],int count){
		int q=0;
        
        String endline2[]=new String[count];
        
      //count is the integer sent from maincontroller(), it is the number of moves
        while(q<count){
        	endline2[q]=endline[q];
        	q++;
        }
        
        return endline2;
	}
	
}
