import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[][] dp = new int[31][31];
		
		for (int i=0; i<31; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}

		for (int i=1; i<31; i++) {
			for (int j=i-1; j>=1; j--) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[M][N]);
		}
		
		
	}

}