import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int[] lotto;
    public static List<Integer> ans;

    static void backTracking(int N) {
        if (N == 6) {
            System.out.printf("%d %d %d %d %d %d", ans.get(0), ans.get(1), ans.get(2), ans.get(3), ans.get(4), ans.get(5));
            System.out.println();
            return;
        }

        for (int i = 0; i < lotto.length; i++) {
            if (ans.size() == 0) {
                ans.add(lotto[i]);
                backTracking(N+1);
                ans.remove(ans.size()-1);
            } else if (ans.size() != 0 && ans.get(ans.size()-1) < lotto[i]) {
                ans.add(lotto[i]);
                backTracking(N+1);
                ans.remove(ans.size()-1);
            }


//            if (visited[i] == 0) {
//                if (ans.size() == 0) {
//                    visited[i] = 1;
//                    ans.add(lotto[i]);
//                    backTracking(N+1);
//                    visited[i] = 0;
//                    ans.remove(ans.size()-1);
//                }
//                else if(ans.size() != 0 && ans.get(ans.size()-1) < lotto[i]) {
//                    visited[i] = 1;
//                    ans.add(lotto[i]);
//                    backTracking(N+1);
//                    visited[i] = 0;
//                    ans.remove(ans.size()-1);
//                }
//            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            lotto = new int[k];
            ans = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                lotto[i] = Integer.parseInt(st.nextToken());
            }
            backTracking(0);
            System.out.println();

        }
    }
}