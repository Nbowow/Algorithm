import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, A, B, C;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static boolean[] isVisited = new boolean[N];

    static void backTracking(int idx, int totalCost, int maxCost) {
        if (totalCost > C) return;

        if (idx == B) {
            ans = Math.min(ans, maxCost);
            return;
        }

        for (int i = 0; i < N; i++) {
            // 방문하지 않았고, 갈 수 있는 경로면
            if (!isVisited[i] && map[idx][i] != 0) {
                isVisited[i] = true;
                backTracking(i, totalCost+map[idx][i], Math.max(maxCost, map[idx][i]));
                isVisited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken())-1;
        B = Integer.parseInt(st.nextToken())-1;
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());

            map[s][e] = cost;
            map[e][s] = cost;
        }

        isVisited = new boolean[N];
        isVisited[A] = true;
        backTracking(A, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}