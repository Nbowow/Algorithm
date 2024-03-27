import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Snake {
        int row, col;
        public Snake(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Move {
        int time;
        char dir;

        public Move(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static int N, K, L;
    static int sr, sc, time;
    static int[][] map;
    static Queue<Move> q = new ArrayDeque<>();
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Snake> sq = new ArrayDeque<>();

    static void start() {

        int dir = 3; // 오른쪽 방향이 시작방향
        while (!q.isEmpty()) {
            Move cur = q.poll();
            int curT = cur.time;
            char curDir = cur.dir;

            // curT초 이하일 때
            while (curT > time) {
                time++;

                int dx = sr + dxdy[dir][0];
                int dy = sc + dxdy[dir][1];

                // 벽 또는 자신과 부딪힐 경우 게임 종료
                if (!isIn(dx, dy) || map[dx][dy] == 1) {
                    System.out.println(time);
                    System.exit(0);
                }

                if (map[dx][dy] == 2) { // 해당 위치에 사과가 있다면
                    // 뱀이 먹고 성장
                    sq.offer(new Snake(dx, dy));
                    map[dx][dy] = 1;
                    sr = dx;
                    sc = dy;
                }
                else { // 일반 길이라면
                    // 뱀 이동
                    sq.offer(new Snake(dx, dy));
                    map[dx][dy] = 1;
                    sr = dx;
                    sc = dy;
                    Snake s = sq.poll();
                    map[s.row][s.col] = 0;
                }

            }

            dir = changeDir(dir, curDir);

        }

        // 모든 방향 전환이 끝난 후 처리
        int dx = sr;
        int dy = sc;
        while (isIn(dx, dy)) {
            time++;
            dx += dxdy[dir][0];
            dy += dxdy[dir][1];

            // 벽 또는 자신과 부딪힐 경우 게임 종료
            if (!isIn(dx, dy) || map[dx][dy] == 1) {
                System.out.println(time);
                System.exit(0);
            }

            if (map[dx][dy] == 2) { // 해당 위치에 사과가 있다면
                // 뱀이 먹고 성장
                sq.offer(new Snake(dx, dy));
                map[dx][dy] = 1;
                sr = dx;
                sc = dy;
            }
            else { // 일반 길이라면
                // 뱀 이동
                sq.offer(new Snake(dx, dy));
                map[dx][dy] = 1;
                sr = dx;
                sc = dy;
                Snake s = sq.poll();
                map[s.row][s.col] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r-1][c-1] = 2; // 사과
        }

        L = Integer.parseInt(br.readLine());
        for (int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            q.offer(new Move(t, dir));
        }

        map[0][0] = 1; // 뱀 표시
        sq.offer(new Snake(0, 0));
        start();

        System.out.println(time);
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isIn(int x, int y) {
        if (x>=0 && x<N && y>=0 && y<N) return true;
        return false;
    }

    static int changeDir(int cur, char next) {
        switch (cur) {
            case 0 : switch (next) { // 상
                case 'L' : return 2;
                case 'D' : return 3;
            }
            case 1 : switch (next) { // 하
                case 'L' : return 3;
                case 'D' : return 2;
            }
            case 2 : switch (next) { // 좌
                case 'L' : return 1;
                case 'D' : return 0;
            }
            case 3 : switch (next) { // 우
                case 'L' : return 0;
                case 'D' : return 1;
            }
        }

        return -1;
    }
}