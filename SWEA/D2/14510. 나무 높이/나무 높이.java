import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, ans;
    static int[] trees;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            trees = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(trees);
//            System.out.println(Arrays.toString(trees));

            int w1 = 0; // 1자라는 갯수
            int w2 = 0; // 2자라는 갯수
            for (int i = 0; i < N; i++) {
                trees[i] = trees[N-1] - trees[i];

                w2 += trees[i] / 2;
                w1 += trees[i] % 2;
            }

            ans = 0;

            // 1, 2 자라는 날이 동일한 경우
            if (w1 == w2) ans = w1+w2;

            // 1 필요한 날이 더 큰 경우 -> 1 사용해야함
            else if (w1 > w2) {
                ans = 2 * w1 - 1;
            }
            // 2 필요한 날이 더 큰 경우 -> 1이
            else {
                while (Math.abs(w2 - w1) > 1) {
                    w2 -= 1;
                    w1 += 2;
                }
                ans = getAns(w1, w2);
            }

            sb.append("#" + tc + " " + ans + "\n");
        }
        System.out.println(sb);

    }

    static int getAns(int w1, int w2) {
        if (w1 == w2) return w1+w2; // 1,2 자라는 횟수가 동일하게 필요한 경우
        else if (w1 > w2) return 2*w2+1; // 1이 2보다 하루 더 많이 필요할 경우
        else return 2*w1 + 2; // 2가 1보다 하루 더 많이 필요할 경우
    }
}