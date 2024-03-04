import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N == 1)
			System.out.println(1);
		else {
			int[][] dp = new int[N + 5][2];

			dp[1][0] = 0;
			dp[1][1] = 1;
			dp[2][0] = 1;
			dp[2][1] = 1;

			for (int i = 3; i < N + 5; i++) {
				// 0으로 끝
				dp[i][0] = (dp[i - 2][0] + dp[i - 2][1]) % 15746;

				// 1로 끝
				dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 15746;

			}

			System.out.println((dp[N][0] + dp[N][1]) % 15746);
		}

	}

}