import java.util.Random;

public class qwe {
	public static void main(String[] args){
		for(int i=0;i<100;i++){
			Random r=new Random();
			int num = r.nextInt(2);
			System.out.println(num);
		}
	}
}
