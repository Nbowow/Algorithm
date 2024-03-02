import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Shark {
        int row, col, speed, dir, size;
        boolean isAlive;

        public Shark(int row, int col, int speed, int dir, int size, boolean isAlive) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.isAlive = isAlive;
        }
    }

    static int R, C, M, score;
    static int[][] map;
    static Shark[] sharks;

    // 상 하 우 좌
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static void eat(int col) {
        for (int i = 0; i < R; i++) {
            // 상어를 만났을 경우
            if (map[i][col] > 0) {
                sharks[map[i][col]].isAlive = false; // 해당 상어 사망
                score += sharks[map[i][col]].size;

                map[i][col] = 0;
                return;
            }
        }
    }

    static void moveShark() {
        for (int i = 1; i < sharks.length; i++) {
            Shark s = sharks[i];

            if (!s.isAlive) continue; // 죽은 상어는 말이 없다.

            int x = s.row;
            int y = s.col;
            map[x][y] = 0; // 자신이 원래 있던자리 빈자리로 만들어줌

            // 상어 속력만큼 이동
            int dx = x;
            int dy = y;

            // 직접 이동시키면 안될듯
            for (int j = 0; j < s.speed; j++) {
                dx += dxdy[s.dir-1][0];
                dy += dxdy[s.dir-1][1];
                // 맵 나갈 경우
                if (dx < 0 || dx >= R || dy < 0 || dy >= C) {
                    changeDir(i, s.dir);
                    // 방향 바꾸고 해당 방향으로 두번 이동 (맵 나갔으므로)
                    dx += dxdy[s.dir-1][0];
                    dx += dxdy[s.dir-1][0];

                    dy += dxdy[s.dir-1][1];
                    dy += dxdy[s.dir-1][1];
                }
            }
            // 상어 다음 위치 설정
            s.row = dx;
            s.col = dy;
        }
    }

    static void update() {
        for (int i = 1; i < sharks.length; i++) {
            if (!sharks[i].isAlive) continue;

            Shark s = sharks[i];
            int dx = s.row;
            int dy = s.col;

            // 맵에 상어 위치
            if (map[dx][dy] > 0) { // 이동하려는 위치에 이미 상어가 존재할 경우
                if (s.size > sharks[map[dx][dy]].size) { // 이미 위치한 상어보다 자신의 크기가 더 클 경우
                    sharks[map[dx][dy]].isAlive = false;
                    map[dx][dy] = i; // 잡아먹음
                }
                // **** 이 코드 빼먹어서 틀림!!*****
                else s.isAlive = false; // 아닐 경우 자기가 죽음
            }

            else if (map[dx][dy] == 0) { // 이동하려는 위치에 아무 상어도 존재 x
                map[dx][dy] = i;
            }
        }
    }

    static void playGame() {
        int t = 0;
        // C번 플레이하면 게임 종료
        while (t < C) {
            // 낚시왕이 자신의 열에서 가장 가까운 상어 잡아먹음
            eat(t);

            // 모든 상어 이동
            moveShark();

            // 상태 업데이트
            update();

            // 낚시왕 다음 열로 이동
            t++;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[M+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 이동 방향
            int z = Integer.parseInt(st.nextToken()); // 크기

            map[r-1][c-1] = i+1; // 상어 표시
            // (상 하) 일 경우 행 갯수에 따라 이동 최적화
            if (d == 1 || d == 2) {
                s = s % (2 * (R-1)); // 속력
            } else { // (좌 우) 일 경우 이동 최적화
                s = s % (2 * (C-1));
            }
            sharks[i+1] = new Shark(r-1, c-1, s, d, z, true); // (i+1)번째 상어 정보 담고 있음
        }

        playGame();

        System.out.println(score);
    }

    // 상어의 이동방향 바꿔줌
    static void changeDir(int sharkNum, int dir) {
        // 상 하 우 좌
        if (dir == 1) sharks[sharkNum].dir = 2;
        else if (dir == 2) sharks[sharkNum].dir = 1;
        else if (dir == 3) sharks[sharkNum].dir = 4;
        else if (dir == 4) sharks[sharkNum].dir = 3;
    }
}