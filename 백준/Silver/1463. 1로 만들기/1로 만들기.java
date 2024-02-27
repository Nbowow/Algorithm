import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+2];
		
		// 초기값 설정
		for (int i=1; i<N+2; i++) {
			dp[i] = i-1;
		}
		
		dp[2] = 1;
		
		if (N > 2) {
			dp[3] = 1;

			for (int i=2; i < N; i++) {
				// i * 3
				if (3*i <= N) dp[3*i] = Math.min(dp[3*i], dp[i] + 1);
				
				// i * 2
				if (2*i <= N) dp[2*i] = Math.min(dp[2*i], dp[i] + 1);
				
				// i + 1
				dp[i+1] = Math.min(dp[i+1], dp[i] + 1);
				
			}
		}
		
		
		System.out.println(dp[N]);
	}

}