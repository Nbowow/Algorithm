import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Island {
        int row, col, dist;

        public Island(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }


    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void makeFindIsland(int row, int col, int islandNum, int del) {

        Queue<Island> q = new ArrayDeque<>();
        q.offer(new Island(row, col, 0));
        isVisited[row][col] = true;

        while (!q.isEmpty()) {
            Island temp = q.poll();
            int x = temp.row;
            int y = temp.col;

            // pruning
            if (temp.dist > ans) continue;

            for (int i = 0; i < 4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;
                    if (del == 1) { // 섬 만드는 bfs
                        if (map[dx][dy] == 1) {
                            map[dx][dy] = islandNum;
                            q.offer(new Island(dx, dy, 0));
                        }
                    } else if (del == 2) { // 섬 연결하는 bfs
                        // 자신과 같은 섬이면 더이상 진행 x
                        if (map[dx][dy] == islandNum) continue;

                        // 새로운 섬 발견
                        if (map[dx][dy] != islandNum && map[dx][dy] > 0) {
                            // 거리 최솟값 갱신
                            ans = Math.min(ans, temp.dist);
                            continue;
                        }
                        // 그냥 바다면
                        q.offer(new Island(dx, dy, temp.dist + 1));
                    }

                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    map[i][j] = islandNum;
                    makeFindIsland(i, j, islandNum++, 1);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 1) { // 섬이면 bfs
                    isVisited = new boolean[N][N];
                    makeFindIsland(i, j, map[i][j], 2);
                }
            }
        }

        System.out.println(ans);
    }

}