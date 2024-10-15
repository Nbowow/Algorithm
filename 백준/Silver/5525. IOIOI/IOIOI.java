import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();

        int[] dp = new int[M];
        if (S[0] == 'I') dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < M; i++) {
            if (S[i] == 'O') {
                if (S[i-1] == 'I') {
                    dp[i] = dp[i-1] + 1;
                    continue;
                }
                else dp[i] = 0;
            }
            else if (S[i] == 'I') {
                if (S[i-1] == 'O') {
                    dp[i] = dp[i-1] + 1;
                    continue;
                }
                else dp[i] = 1;
            }


            // 계산
            if (dp[i-1] >= 2*N + 1) {
                int temp = dp[i - 1] - (2 * N + 1) + 1; // 사이 숫자 갯수
                ans += (temp + 1) / 2;
            }
        }

        // 마지막 수 체크
        if (dp[M - 1] != 0 || dp[M - 1] != 1) {
            if (dp[M-1] >= 2*N + 1) {
                int temp = dp[M - 1] - (2 * N + 1) + 1; // 사이 숫자 갯수
                ans += (temp + 1) / 2;
            }
        }

        System.out.println(ans);
    }

}