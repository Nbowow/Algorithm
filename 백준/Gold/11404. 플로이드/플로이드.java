import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] dp;
	static final int MAX_COST = 100000;
	static final int MAX_BUS = 100;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		dp = new int[n+1][n+1];
		for (int i=1; i<n+1; i++) Arrays.fill(dp[i], MAX_BUS * MAX_COST);
		
		for (int i=1; i<n+1; i++) dp[i][i] = 0;
		
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dp[a][b] = Math.min(dp[a][b], c);
		}
		
		for (int k=1; k<n+1; k++) { // 경로
			for (int i=1; i<n+1; i++) { // 출발지
				for (int j=1; j<n+1; j++) { // 도착지
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=1; i<n+1; i++) {
			for (int j=1; j<n+1; j++) {
				if (dp[i][j] == MAX_BUS * MAX_COST) dp[i][j] = 0;
				sb.append(dp[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

}