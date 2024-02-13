import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[5005];
		for (int i=0; i<dp.length; i++) {
			dp[i] = 5005;
		}
		
		dp[3] = 1;
		dp[5] = 1;
		
		for (int i = 6; i<dp.length; i++) {
			dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
		}
		
		if (dp[N] > 5000) System.out.println(-1);
		else System.out.println(dp[N]);
	}

}