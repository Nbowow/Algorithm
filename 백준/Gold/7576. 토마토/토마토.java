import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Tomato {
        int row, col;

        public Tomato(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, ans;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Tomato> q = new ArrayDeque<>();

    static void bfs() {

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                Tomato tomato = q.poll();
                int x = tomato.row;
                int y = tomato.col;

                for (int j = 0; j < 4; j++) {
                    int dx = x + dxdy[j][0];
                    int dy = y + dxdy[j][1];

                    if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
                        if (isVisited[dx][dy]) continue;

                        isVisited[dx][dy] = true;

                        if (map[dx][dy] == 0) {
                            map[dx][dy] = 1;
                            q.offer(new Tomato(dx, dy));
                        }
                    }
                }
            }

            ans += 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new Tomato(i, j));
                    isVisited[i][j] = true;
                }
            }
        }

        if (isAllRipen()) {
            System.out.println(0);
        } else {
            bfs();

            if (isAllRipen()) {
                System.out.println(ans-1);
            }
            else {
                System.out.println(-1);
            }
        }
    }


    static boolean isAllRipen() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) return false;
            }
        }

        return true;
    }

}