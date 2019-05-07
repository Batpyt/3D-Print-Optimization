
/*
 * this class is change the size of array that storing start line into correct size
 */

public class getstartline {
	public String[] getstart(String[] startline,int count){
		int q=0;
		
        String startline2[]=new String[count];
        
        //count is the integer sent from maincontroller(), it is the number of moves
        while(q<count){
        	startline2[q]=startline[q];
        	q++;
        }
        
        
        return startline2;

	}
	
}
