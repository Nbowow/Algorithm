import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        // 0 : 왼 -> 오, 1 : 오 -> 왼
        dp[0][0][0] = map[0][0];
        dp[0][0][1] = map[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j][0] = dp[0][j-1][0] + map[0][j];
            dp[0][j][1] = dp[0][j-1][1] + map[0][j];
        }

        for (int i = 1; i < N; i++) {
            // 왼쪽 -> 오른쪽
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    dp[i][j][0] = dp[i-1][j][0] + map[i][j];
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0]) + map[i][j];
            }

            // 오른쪽 -> 왼쪽
            for (int j = M - 1; j >= 0; j--) {
                if (j == M - 1) {
                    dp[i][j][1] = dp[i-1][j][1] + map[i][j];
                    continue;
                }

                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j+1][1]) + map[i][j];
            }

            // 최종
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j][1]);
                dp[i][j][1] = Math.max(dp[i][j][0], dp[i][j][1]);
            }

        }

        System.out.println(dp[N-1][M-1][0]);
    }

    static void printMap(int[][][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j][0] + " ");
            }
            System.out.println();
        }
    }
}