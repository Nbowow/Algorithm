import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static boolean[][][] isWork;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 공사중인 도로 개수
        K = Integer.parseInt(br.readLine());

        // 상, 하, 좌, 우 도로인지 확인
        isWork = new boolean[N + 1][M + 1][4];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int xLoad = a - c;
            int yLoad = b - d;

            // (a, b) 위 (c, d) 아래쪽 도로 공사
            if (xLoad == 1) {
                isWork[a][b][0] = isWork[c][d][1] = true;
            }
            // (a, b) 아래쪽 (c, d) 위쪽 도로 공사
            else if (xLoad == -1) {
                isWork[a][b][1] = isWork[c][d][0] = true;
            }
            // (a, b) 왼쪽 (c, d) 오른쪽 도로 공사
            if (yLoad == 1) {
                isWork[a][b][2] = isWork[c][d][3] = true;
            }
            // (a, b) 오른쪽 (c, d) 왼쪽 도로 공사
            else if (yLoad == -1) {
                isWork[a][b][3] = isWork[c][d][2] = true;
            }

        }

        long[][] dp = new long[N + 1][M + 1];
        // 초기값 설정
        dp[0][0] = 1;
        // 위
        for (int i = 1; i <= N; i++) {
            if (!isWork[i - 1][0][1]) {
                dp[i][0] = dp[i - 1][0];
            }
        }

        // 우
        for (int j = 1; j <= M; j++) {
            if (!isWork[0][j - 1][3]) {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 위 + 왼
                if (!isWork[i - 1][j][1] && !isWork[i][j - 1][3]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                // 위
                else if (!isWork[i - 1][j][1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 왼
                else if (!isWork[i][j - 1][3]) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);

    }
}