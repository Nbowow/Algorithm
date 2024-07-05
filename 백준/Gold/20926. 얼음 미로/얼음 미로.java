import java.util.*;
import java.io.*;

public class Main {

    static class Tera implements Comparable<Tera>{
        int r, c, t, dir;

        public Tera(int r, int c, int t, int dir) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.dir = dir;
        }

        // 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Tera o) {
            return Integer.compare(this.t, o.t);
        }
    }

    static int W, H;
    static char[][] map;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static PriorityQueue<Tera> pq;

    static int bfs() {

        int[][] isVisited = new int[H][W];

        while (!pq.isEmpty()) {
            Tera cur = pq.poll();

//            System.out.println(cur.r + " " + cur.c + " " + cur.t + " " + cur.dir);

            for (int i = 0; i < 4; i++) {

                if ((cur.dir & (1 << i)) == 0) continue;

                int dx = cur.r + dxdy[i][0];
                int dy = cur.c + dxdy[i][1];

                if (!isIn(dx, dy) || (isVisited[dx][dy] & (1<<i)) != 0) continue;

                isVisited[dx][dy] += 1<<i; // 방문 처리
                // 출구 만났을 때 => 정답
                if (map[dx][dy] == 'E') {
                    return cur.t;
                }

                // 바위 만났을 때 => 다시 4방 탐색 가능
                else if (map[dx][dy] == 'R') {
                    pq.offer(new Tera(dx - dxdy[i][0], dy - dxdy[i][1], cur.t, 15));
                    break;
                }

                // 구멍 만났을 때
                else if (map[dx][dy] == 'H') continue;

                // 일반 지형이면 이동시간 증가
                pq.offer(new Tera(dx, dy, cur.t + map[dx][dy] - 48, 1<<i));

            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        pq = new PriorityQueue<>();
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'T') {
                    pq.offer(new Tera(i, j, 0, 15));
                }
            }
        }
        System.out.println(bfs());

    }

    // debug
    static void printMap(char[][] map) {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean isIn(int x, int y) {
        return x>=0 && x<H && y>=0 && y<W;
    }
}