import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static boolean[][] trains;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trains = new boolean[N][20];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());

            if (C == 1) {
                int trainIdx = Integer.parseInt(st.nextToken()) - 1;
                int sitIdx = Integer.parseInt(st.nextToken()) - 1;
                trains[trainIdx][sitIdx] = true;
            } else if (C == 2) {
                int trainIdx = Integer.parseInt(st.nextToken()) - 1;
                int sitIdx = Integer.parseInt(st.nextToken()) - 1;
                trains[trainIdx][sitIdx] = false;
            } else if (C == 3) {
                int trainIdx = Integer.parseInt(st.nextToken()) - 1;
                for (int j = 19; j > 0; j--) {
                    trains[trainIdx][j] = trains[trainIdx][j - 1];
                }
                trains[trainIdx][0] = false;
            } else {
                int trainIdx = Integer.parseInt(st.nextToken()) - 1;
                for (int j = 0; j < 19; j++) {
                    trains[trainIdx][j] = trains[trainIdx][j + 1];
                }
                trains[trainIdx][19] = false;
            }
        }

        boolean[] bit = new boolean[1<<22];
        for (int i = 0; i < N; i++) {
            int curBit = 0;
            for (int j = 0; j < 20; j++) {
                if (trains[i][j]) {
                    curBit |= 1<<j;
                }
            }

            if (!bit[curBit]) ans++;
            bit[curBit] = true;

        }

        System.out.println(ans);
    }
}