import org.junit.Test;


public class Test1 {
	@Test
	public void test(){
		for(int x=1;x<10;x++){
			for(int y=1;y<10;y++){
				int result = 10*x*x+x*y;
				if(result<100){
					continue;
				}
				if((result % 3)!=0){
					continue;
				}			
				System.out.println(result);
				StringBuilder sb = new StringBuilder(result+"");
				for(int i=0;i<3;i++){
					if(sb.charAt(0)==sb.charAt(1) && sb.charAt(1)==sb.charAt(2)){
						System.out.println("x=="+x+",y="+y+",result="+result);						
					}
				}
			}
		}
		
	}
}
