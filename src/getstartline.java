
public class getstartline {
	public String[] getstart(String[] startline,int count){
		int q=0;
		/*
        int count=0;
        while(q<200){
        	if(startline[q]!=null ){
        	count++;
        	}
        	q++;
        }
        
        System.out.println(count);
        q=0;
        */
        String startline2[]=new String[count];
        while(q<count){
        	startline2[q]=startline[q];
        	q++;
        }
        
        //for(String print:startline2){
        //	System.out.println(print);
       // }
        
        return startline2;

	}
	
}
