import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static boolean[] isVisited;
    static int ans = Integer.MAX_VALUE;

    static void dfs(int start, int cur, int cnt, int val) {
        if (cnt == N-1) {
            if (map[cur][start] != 0) {
                val += map[cur][start];
                ans = Math.min(ans, val);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isVisited[i] && map[cur][i] != 0) {
                isVisited[i] = true;
                dfs(start, i,cnt+1, val+map[cur][i]);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            isVisited[i] = true;
            dfs(i, i, 0, 0);
        }

        System.out.println(ans);

    }
}