import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class Route implements Comparable<Route>{
        int row, col, dist, height;
        public Route(int row, int col, int dist, int height) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.height = height;
        }

        // 거리기준 오름차순 정렬, 거리가 같을경우 높이기준 내림차순 정렬
        @Override
        public int compareTo(Route o) {
            if (this.dist == o.dist) {
                return Integer.compare(o.height, this.height);
            }
            return Integer.compare(this.dist, o.dist);
        }
    }

    static class Top {
        int row, col;

        public Top(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, K, ans;
    static int[][] map;
    static List<Top> tops;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs() {
        // 모든 봉우리로부터 bfs
        for (int c = 0; c < tops.size(); c++) {
            Top cur = tops.get(c);

            PriorityQueue<Route> pq = new PriorityQueue<>();
            pq.offer(new Route(cur.row, cur.col, 1, map[cur.row][cur.col]));

            int len = 1;
            while (!pq.isEmpty()) {
                Route temp = pq.poll();
                int x = temp.row;
                int y = temp.col;
                int dist = temp.dist;
                int height = temp.height;

                for (int i = 0; i < 4; i++) {
                    int dx = x + dxdy[i][0];
                    int dy = y + dxdy[i][1];

                    if (!isIn(dx, dy)) continue;

                    // 현재 높이보다 작다면 이동
                    if (map[dx][dy] < height) {
                        pq.offer(new Route(dx, dy, dist + 1, map[dx][dy]));
                        len = dist + 1;
                    }
                }
            }

            ans = Math.max(ans, len);
        }
    }

    static void game() {

        // 1~K 만큼 등산로 깎기
        for (int c=1; c<=K; c++) {
            // 모든 구역에 대해 깎고, 검사
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    map[i][j] -= c;
                    bfs();
                    map[i][j] += c;
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            tops = new ArrayList<>();
            int maxNum = 0;
            // input map
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxNum = Math.max(maxNum, map[i][j]);
                }
            }

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (map[i][j] == maxNum) tops.add(new Top(i, j));
                }
            }
            ans = 0;
            game();

            System.out.println("#" + tc + " " + ans);


        }
    }

    // debug
    static void printMap(int[][] map) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}