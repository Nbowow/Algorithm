import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=P; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int len = 2;
			int a = 1;
			int b = 1;
			while (true) {
				int temp = (a+b) % M;
				a = b;
				b = temp;
				
				if (b == 1 && a == 1) {
					break;
				}
				len++;
			}
			System.out.println(tc + " " + (len - 1));
		}
	}

}