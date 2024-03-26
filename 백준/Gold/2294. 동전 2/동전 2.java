import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int n, k;
	static Integer[] coins;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		coins = new Integer[n];
		
		k = Integer.parseInt(st.nextToken());
		dp = new int[k+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for (int i=0; i<n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coins);
		
		
		for (int i=0; i<coins.length; i++) {
			if (coins[i] < dp.length) dp[coins[i]] = 1;
		}
		
		for (int i=1; i<dp.length; i++) {
			for (int j=0; j<coins.length; j++) {
				if (i-coins[j] < 1 || dp[i-coins[j]] == Integer.MAX_VALUE) continue;
				
				dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
			}
		}
		
		System.out.println(dp[k] != Integer.MAX_VALUE ? dp[k] : -1);
	}

}