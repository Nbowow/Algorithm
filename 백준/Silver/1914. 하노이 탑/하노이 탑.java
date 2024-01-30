import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	static void hanoi(int count, int from, int temp, int to) {
		if (count == 0) return;
		
		hanoi(count - 1, from, to, temp);
		sb.append(from + " " + to + "\n");
		hanoi(count - 1, temp, from, to);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		System.out.println(BigInteger.TWO.pow(K).subtract(BigInteger.ONE));
		
		if (K <= 20) {
			hanoi(K, 1, 2, 3);
			System.out.println(sb);
		}
	}
}