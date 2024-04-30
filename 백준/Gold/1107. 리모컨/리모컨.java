import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, mok;
    static boolean[] canUse;
    static int ans;

    static void backTracking(int num, int depth) {
        if (depth > mok + 1) return;

        ans = Math.min(ans, Math.abs(N - num) + depth);

        for (int i = 0; i < 10; i++) {
            if (!canUse[i]) continue;
            backTracking(num + (int)Math.pow(10, depth)*i, depth + 1);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int temp = N;
        while (temp / 10 > 0) {
            temp/=10;
            mok++;
        }
        mok++;

        if (M == 0) { // edge case
            ans = Math.min(Math.abs(N - 100), mok);
        } else {
            canUse = new boolean[10]; // 0 ~ 9
            for (int i=0; i<10; i++) canUse[i] = true;
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<M; i++) {
                canUse[Integer.parseInt(st.nextToken())] = false;
            }

            // 현재 위치한 채널 100
            ans = Math.abs(N - 100);
            for (int i = 0; i < 10; i++) {
                if (!canUse[i]) continue;
                backTracking(i, 1);
            }
        }

        System.out.println(ans);

    }
}