import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp;
    static int dfs3(int s1, int s2, int s3) {
        s1 = Math.max(s1, 0);
        s2 = Math.max(s2, 0);
        s3 = Math.max(s3, 0);

        if (s1 == 0 && s2 == 0 && s3 == 0) {
            return 0;
        }

        if (dp[s1][s2][s3] != -1) {
            return dp[s1][s2][s3];
        }

        int res = Integer.MAX_VALUE;

        res = Math.min(res, dfs3(s1 - 9, s2 - 3, s3 - 1) + 1);
        res = Math.min(res, dfs3(s1 - 9, s2 - 1, s3 - 3) + 1);
        res = Math.min(res, dfs3(s1 - 3, s2 - 9, s3 - 1) + 1);
        res = Math.min(res, dfs3(s1 - 3, s2 - 1, s3 - 9) + 1);
        res = Math.min(res, dfs3(s1 - 1, s2 - 9, s3 - 3) + 1);
        res = Math.min(res, dfs3(s1 - 1, s2 - 3, s3 - 9) + 1);

        return dp[s1][s2][s3] = res;

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] scv = new int[3];

        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[65][65][65];
        for (int i = 0; i < 65; i++) {
            for (int j = 0; j < 65; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs3(scv[0], scv[1], scv[2]));
    }
}