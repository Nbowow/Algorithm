import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int row, col, k, dist;

        public Node(int row, int col, int k, int dist) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.dist = dist;
        }
    }

    static int K, H, W;
    static int ans = (int)10e6;
    static int[][] map;
    static boolean[][][] isVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

    static void bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, K, 0));
        isVisited[0][0][K] = true;

        while (!q.isEmpty()) {
            Node temp = q.poll();
            int x = temp.row;
            int y = temp.col;
            int k = temp.k;
            int dist = temp.dist;

//            System.out.println("x, y, dist, k : " + x + " " + y + " " + dist + " " + k );

            if (x == H-1 && y == W-1) {
                ans = dist;
                return;
            }

            for (int i=0; i<dxdy.length; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (dx>=0 && dx<H && dy>=0 && dy<W) {
                    // 점프 x
                    if (i<4) {
                        if (!isVisited[dx][dy][k] && map[dx][dy] == 0) {
                            isVisited[dx][dy][k] = true;
                            q.offer(new Node(dx, dy, k, dist+1));
                        }
                    }
                    // 점프 o
                    else {
                        // *** 점프 한번 하므로 k를 1 감소시킨 방문배열을 확인해줘야함!!
                        if (k>0 && !isVisited[dx][dy][k-1] && map[dx][dy] == 0) {
                            isVisited[dx][dy][k-1] = true;
                            q.offer(new Node(dx, dy, k-1, dist+1));
                        }
                    }
                }
            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        isVisited = new boolean[H][W][K+1];

        for (int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        if (ans == (int)10e6) System.out.println("-1");
        else System.out.println(ans);

    }

}