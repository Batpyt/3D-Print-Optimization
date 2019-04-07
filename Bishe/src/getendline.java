
public class getendline {
	public String[] getend(String endline[],int count){
		int q=0;
        
		/*int count=0;
        while(q<200){
        	if(endline[q]!=null ){
        	count++;
        	}
        	q++;
        }
        
        System.out.println(count);
        q=0;
        */
        String endline2[]=new String[count];
        while(q<count){
        	endline2[q]=endline[q];
        	q++;
        }
        
        return endline2;
	}
	
}
