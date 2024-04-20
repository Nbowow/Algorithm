import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] isVisited;
    static int[][] map;
    static int[][] check;
    static int ans;

    static void bfs(int row, int col) {
        isVisited[row][col] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (!isIn(dx, dy) || isVisited[dx][dy]) continue;

                if (map[dx][dy] == 0) {
                    // 모서리는 중복되어 카운트 되어야 하므로 빈곳일 경우만 방문처리
                    isVisited[dx][dy] = true;
                    q.offer(new int[]{dx, dy});
                }
                else if (map[dx][dy] == 1) {
                    check[dx][dy] = 1;
                    ans++;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        map = new int[101][101];
        check = new int[101][101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = 101 - 10 - y; j < 101 - y; j++) {
                for (int k = x; k < x + 10; k++) {
                    map[j][k] = 1;
                }
            }
        }

        isVisited = new boolean[101][101];
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (map[i][j] == 0 && !isVisited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans);

    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < 101 && y >= 0 && y < 101;
    }
}