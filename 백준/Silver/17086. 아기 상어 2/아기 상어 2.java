import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int row, col, dist;

        public Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] dxdy = {{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}};

    static int bfs(int row, int col) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(row, col, 0));
        boolean[][] isVisited = new boolean[N][M];
        isVisited[row][col] = true;

        int maxD = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.row;
            int y = cur.col;

            for (int i = 0; i < 8; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (!isIn(dx, dy) || isVisited[dx][dy]) continue;

                isVisited[dx][dy] = true;
                if (map[dx][dy] == 1) {
                    maxD = Math.max(maxD, cur.dist + 1);
                    return maxD;
                }

                q.offer(new Node(dx, dy, cur.dist + 1));
            }
        }

        return -1;
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
            }
        }

        // 모든 d빈 공간에 대해 안전거리 구하기
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        System.out.println(ans);
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}