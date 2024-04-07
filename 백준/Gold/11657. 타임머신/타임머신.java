import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final long MAX_NUM = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] map = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], MAX_NUM);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            // A에서 B로 가는 버스가 하나가 아닐수 있다
            // 이렇게 하면 계속 덮어씌워져서 틀림
            // map[A][B] = C;
            map[A][B] = Math.min(map[A][B], C);
        }

        // 플로이드 워샬
        for (int k = 0; k < N; k++) { // 경로
            for (int i = 0; i < N; i++) { // 출발지
                for (int j = 0; j < N; j++) { // 도착지
                    // 갈 수 없는 경로로 가려고 한다면 continue
                    if (map[i][k] == MAX_NUM || map[k][j] == MAX_NUM) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        // 1 -> i 로의 경로가 존재하고, map[i][i] < 0 이면 출발지가 1인 음의 싸이클 존재
        boolean isMinusCycle = false;
        for (int i = 0; i < N; i++) {
            if (map[0][i] != MAX_NUM && map[i][i] < 0) {
                isMinusCycle = true;
                break;
            }
        }

        if (isMinusCycle) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < N; j++) {
                sb.append(map[0][j] == MAX_NUM ? -1 : map[0][j]);
                sb.append("\n");
            }
            System.out.println(sb);
        }

    }
}