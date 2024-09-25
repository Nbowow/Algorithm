import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class MaxP {
        int idx, val;

        public MaxP(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];
        MaxP maxP = new MaxP(N-1, heights[N-1]);
        long totalCount = 0;
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j <= maxP.idx; j++) {
                if (heights[i] <= heights[j]) {
                    dp[i] = j - i - 1;
                    break;
                }
                else if (j == maxP.idx) {
                    dp[i] = dp[maxP.idx] + j - i;
                    maxP.idx = i;
                    maxP.val = heights[i];
                }
            }
            totalCount += dp[i];
        }

        System.out.println(totalCount);
    }
}