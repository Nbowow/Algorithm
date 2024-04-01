import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static long ans;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[] P = new int[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                P[i] = Math.abs(x) + Math.abs(y);
            }

            // 각 정점마다 x좌표와 y좌표의 합이 모두 홀수 or 짝수 여야함
            int maxNum = P[0];
            for (int i = 1; i < N; i++) {
                if (P[i] % 2 != P[i-1] % 2) {
                    maxNum = -1;
                    break;
//                    System.out.println("#" + tc + " " + -1);
//                    return;
                }
                maxNum = Math.max(maxNum, P[i]);
            }

            // 1~i번째 까지 합(sum) >= maxNum, (sum - maxNum) % 2 == 0
            long sum = 0;
            long idx = 0;
            if (maxNum != -1) {
                while (true) {
                    sum += idx;
                    if (sum >= maxNum && (sum - maxNum) % 2 == 0) {
                        ans = idx;
                        break;
                    }
                    idx++;
                }
            }
            else ans = -1;
            

            System.out.println("#" + tc + " " + ans);
        }
    }
}