import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken()); // R
            house[i][1] = Integer.parseInt(st.nextToken()); // G
            house[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = house[0][i];
        }

        for (int i = 1; i < N; i++) {
            // i번째 집을 R로 색칠할 경우
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + house[i][0];

            // i번째 집을 G로 색칠할 경우
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + house[i][1];

            // i번째 집을 B로 색칠할 경우
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + house[i][2];
        }

        System.out.println(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));

    }
}