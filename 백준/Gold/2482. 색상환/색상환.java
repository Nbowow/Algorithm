import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int DIVIDE_NUM = 1_000_000_003;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][K+1];

        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 1;
        }
        dp[1][1] = 1;


        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < K + 1; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % DIVIDE_NUM;

                // N번째 색 고르는 경우 => 2~N-2에서 K-1개 고르기 => 1~N-3에서 k-1개 고르기
                // N번째 색 고르지X 경우 => 1~N-1에서 K개 고르기
                if (i == N) {
                    dp[i][j] = (dp[i - 3][j - 1] + dp[i - 1][j]) % DIVIDE_NUM;
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}