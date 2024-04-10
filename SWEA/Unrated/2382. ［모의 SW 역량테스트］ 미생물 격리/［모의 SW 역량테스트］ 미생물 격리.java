import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Micro {
        int row, col, count, dir;
        boolean isAlive;

        public Micro(int row, int col, int count, int dir, boolean isAlive) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
    static int N, M, K;
    static int[][] leaderMap;
    static int[][] sumMap;
    static int[][] dxdy = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Micro[] micros;

    static void move() {
        sumMap = new int[N][N]; // 미생물 수
        leaderMap = new int[N][N]; // 대표 군집 번호
        // 모든 미생물들에 대해 해당 방향으로 이동
        for (int i = 0; i < micros.length; i++) {
            if (!micros[i].isAlive) continue;

            Micro micro = micros[i];
            int dir = micro.dir;
            int dx = micro.row + dxdy[dir][0];
            int dy = micro.col + dxdy[dir][1];
            micro.row = dx;
            micro.col = dy;

            // edge에 도착했을 경우 방향 바꾸기
            if (isEdge(micro.row, micro.col)) {
                int changeDir = changeDir(dir);
                micro.dir = changeDir;
                micro.count /= 2;
            }
            
            // 미생물 수 0이 될경우 사망
            if (micro.count == 0) {
                micro.isAlive = false;
                continue;
            }

            // 군집화
            if (sumMap[dx][dy] == 0) { // 아무것도 위치하고 있지 않을 경우
                sumMap[dx][dy] += micro.count;
                leaderMap[dx][dy] = i;
            }
            else if (micros[leaderMap[dx][dy]].count < micro.count) {
                micros[leaderMap[dx][dy]].isAlive = false; // 원래 있던 군집 kill
                sumMap[dx][dy] += micro.count;
                leaderMap[dx][dy] = i;
            }
            else if (micros[leaderMap[dx][dy]].count > micro.count){
                micros[i].isAlive = false; // 내 군집이 kill
                sumMap[dx][dy] += micro.count;
            }

        }

        // 군집 업데이트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sumMap[i][j] > 0) {
                    micros[leaderMap[i][j]].count = sumMap[i][j];
                }
            }
        }

    }

    static int game() {
        // M시간 진행
        for (int t = 0; t < M; t++) {
            // 미생물 이동
            move();
        }

        // 남아 있는 미생물 수 세기
        int microSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sumMap[i][j] > 0) microSum += sumMap[i][j];
            }
        }

        return microSum;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 셀의 개수
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수

            // micro input
            micros = new Micro[K];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                micros[i] = new Micro(r, c, count, dir, true);
            }

            int ans = game();
            System.out.println("#" + tc + " " + ans);
        }
    }

    // debug
    static void printMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int changeDir(int idx) {
        if (idx == 1) return 2;
        else if (idx == 2) return 1;
        else if (idx == 3) return 4;
        else if (idx == 4) return 3;

        return -1; // can't reach
    }

    static boolean isEdge(int x, int y) {
        return x == 0 || x == N - 1 || y == 0 || y == N - 1;
    }
}