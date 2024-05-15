import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][2];
        int[] grapes = new int[n];
        for (int i = 0; i < n; i++) {
            grapes[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(grapes[0]);
            System.exit(0);
        }

        dp[0][1] = grapes[0];
        dp[1][0] = grapes[0];
        dp[1][1] = grapes[0] + grapes[1];
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 2][0] + grapes[i - 1], dp[i - 1][0]) + grapes[i];
        }

        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }
}