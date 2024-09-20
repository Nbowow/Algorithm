import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[][] sticker = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // edge case
            if (n == 1) {
                int ans = Math.max(sticker[0][0], sticker[1][0]);
                sb.append(ans + "\n");
                continue;
            }

            int[][] dp = new int[3][n];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for (int i = 1; i < n; i++) {
                dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[1][i];
                dp[2][i] = Math.max(Math.max(dp[0][i-1], dp[1][i-1]), dp[2][i-1]);
            }

            int ans = Math.max(Math.max(dp[0][n-1], dp[1][n-1]), dp[2][n-1]);

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }
}