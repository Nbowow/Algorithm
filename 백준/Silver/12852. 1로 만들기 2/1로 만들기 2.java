import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            System.out.println(1);
            System.exit(0);
        } else if (N == 2) {
            System.out.println(1);
            System.out.println(2 + " " + 1);
            System.exit(0);
        }

        int[] dp = new int[N + 1];
        int[] parents = new int[N + 1];

        dp[2] = 1;
        dp[3] = 1;
        parents[2] = 1;
        parents[3] = 1;

        for (int i = 4; i < N + 1; i++) {
            if (i % 2 == 0 && i % 3 == 0) {
                dp[i] = Math.min(Math.min(dp[i / 2], dp[i / 3]), dp[i - 1]) + 1;

                if (dp[i] == dp[i / 2] + 1) parents[i] = i / 2;
                else if (dp[i] == dp[i / 3] + 1) parents[i] = i / 3;
                else parents[i] = i - 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i - 1], dp[i / 2]) + 1;

                if (dp[i] == dp[i / 2] + 1) parents[i] = i / 2;
                else parents[i] = i - 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i - 1], dp[i / 3]) + 1;

                if (dp[i] == dp[i / 3] + 1) parents[i] = i / 3;
                else parents[i] = i - 1;
            } else {
                dp[i] = dp[i - 1] + 1;
                parents[i] = i - 1;
            }


        }

        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();
        sb.append(N + " ");
        int t = N;
        while (true) {
            sb.append(parents[t] + " ");
            t = parents[t];
            if (t == 1) break;
        }

        System.out.println(sb);

    }
}