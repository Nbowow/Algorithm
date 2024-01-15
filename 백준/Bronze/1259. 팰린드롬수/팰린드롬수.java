import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
xx:			while(true) {
			String num = sc.next();
			if (Integer.parseInt(num) == 0) break;
			
			for (int i=0; i<num.length()/2; i++) {
				if (num.charAt(i) != num.charAt(num.length()-i-1)) {
					System.out.println("no");
					continue xx;
				}
			}
			System.out.println("yes");
		}
	}

}