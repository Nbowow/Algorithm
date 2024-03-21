import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Cheese {
        int row, col;

        public Cheese(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] isVisited;
    static int time;
    static int cheeseCount;

    static void bfs() {
        Queue<Cheese> q = new ArrayDeque<>(); // 공기
        Queue<Cheese> c = new ArrayDeque<>();
        q.offer(new Cheese(0, 0));

        // 공기와 맞닿은 치즈 구하기
        while (!q.isEmpty()) {
            Cheese cur = q.poll();
            int x = cur.row;
            int y = cur.col;

            for (int i = 0; i < 4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (dx >= 0 && dx < N && dy >= 0 && dy < M && !isVisited[dx][dy]) {
                    if (map[dx][dy] == 0) {
                        isVisited[dx][dy] = true;
                        q.offer(new Cheese(dx, dy));
                    }
                    else {
                        map[dx][dy]++;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                if (map[i][j] > 2) {
                    map[i][j] = 0;
                    cheeseCount--;
                }
                else map[i][j] = 1;
            }
        }

        time++;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheeseCount++;
            }
        }

        while (cheeseCount > 0) {
            isVisited = new boolean[N][M];
            bfs();
        }

        System.out.println(time);
    }

}