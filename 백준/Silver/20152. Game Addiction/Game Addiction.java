import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[31][31];
		
		for (int i=0; i<dp.length; i++) {
			dp[i][0] = 1;
		}
		
		for (int i=1; i<dp.length; i++) {
			for (int j=1; j<=i; j++) {
				if (i == j) dp[i][j] = dp[i][j-1];
				else dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		int C = Math.abs(H-N);
		System.out.println(dp[C][C]);
	}

}