import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] A;
    static long[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        // dp[i] = i번째까지 포장하는데 필요한 최소 비용
        dp = new long[N + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            int b_max = Integer.MIN_VALUE;
            int b_min = Integer.MAX_VALUE;
            for (int j = 1; j <= M; j++) { // 한박스에 넣는 오렌지 갯수 = j
                if (i<j) break;
                b_max = Math.max(b_max, A[i - j + 1]);
                b_min = Math.min(b_min, A[i - j + 1]);
                dp[i] = Math.min(dp[i], dp[i - j] + K + (long) j * (b_max - b_min));
            }
        }

        System.out.println(dp[N]);

    }
}