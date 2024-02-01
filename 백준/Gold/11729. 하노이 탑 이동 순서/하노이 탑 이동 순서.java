import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	static int N, K;
	static StringBuilder sb = new StringBuilder();
	
	static void hanoi(int count, int from, int temp, int to) {
		if (count == 0) {
			return;
		}
		
		hanoi(count - 1, from, to, temp);
		sb.append(from + " " + to + "\n");
		hanoi(count - 1, temp, from, to);
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE));
		
		if (N<=20) {
			K = (int) Math.pow(2, N)-1;
			hanoi(N, 1, 2, 3);
		}
		System.out.println(sb);
	}

}