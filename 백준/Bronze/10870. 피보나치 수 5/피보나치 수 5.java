import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if (n == 0) System.out.println(0);
		else {
			BigInteger[] dp = new BigInteger[n+1];
			dp[0] = new BigInteger("0");
			dp[1] = new BigInteger("1");
			for (int i=2; i<n+1; i++) {
				dp[i] = dp[i-1].add(dp[i-2]);
			}
			
			System.out.println(dp[n]);
		}
		
	}

}