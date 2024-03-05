import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[][] bulbs;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, 0}, {0, -1}, {0, 1}};

    static void check() {

        int numComb = (int) Math.pow(2, N);
        for (int i = 0; i < numComb; i++) {
            int[][] temp = copyOriginal();
            int count = 0;

            // 0행에서 스위치 눌러야 하는 조합 뽑기
            for (int n = 0; n < N; n++) {
                if ((i & 1<<n) == 1<<n) {

                    count++; // 스위치 누름
                    for (int[] dir : dxdy) {
                        int dx = dir[0];
                        int dy = n + dir[1];
                        // 전구 상태 switch
                        if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                            temp[dx][dy] = temp[dx][dy] == 0 ? 1 : 0;
                        }
                    }
                }
            }

            for (int x = 1; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    // 내 위의 전구가 켜져있으면
                    if (temp[x - 1][y] == 1) {
                        count++; // 스위치 누름
                        for (int[] dir : dxdy) {
                            int dx = x + dir[0];
                            int dy = y + dir[1];
                            // 전구 상태 switch
                            if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                                temp[dx][dy] = temp[dx][dy] == 0 ? 1 : 0;
                            }
                        }
                    }
                }
            }

            if (isFinish(temp)) ans = Math.min(ans, count);
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        bulbs = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                bulbs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check();

        System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
    }

    static int[][] copyOriginal() {
        int[][] arr = new int[N][N];
        for (int i=0; i<N; i++) arr[i] = bulbs[i].clone();
        return arr;
    }

    static boolean isFinish(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) return false;
            }
        }
        return true;
    }
}