import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int row, col, rupee;

        public Node(int row, int col, int rupee) {
            this.row = row;
            this.col = col;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.rupee, o.rupee);
        }
    }

    static int N, ans;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));
        isVisited[0][0] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.row;
            int y = cur.col;
            int rupee = cur.rupee;

            for (int i = 0; i < 4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                // 도착지점 도달
                if (dx == N - 1 && dy == N - 1) {
                    ans = rupee + map[dx][dy];
                    return;
                }

                if (isIn(dx, dy) && !isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;

                    pq.offer(new Node(dx, dy, rupee + map[dx][dy]));
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;

        int T = 0;
        while (true) {
            T++;
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            isVisited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            System.out.println("Problem " + T + ": " + ans);
        }

    }

    static boolean isIn(int x, int y) {
        if(x>=0 && x<N && y>=0 && y<N) return true;
        return false;
    }
}