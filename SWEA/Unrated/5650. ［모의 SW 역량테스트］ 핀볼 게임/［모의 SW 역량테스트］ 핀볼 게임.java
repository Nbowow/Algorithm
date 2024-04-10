import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static class Wormhole {
        int idx, row, col;
        public Wormhole(int idx, int row, int col) {
            this.idx = idx;
            this.row = row;
            this.col = col;
        }
    }

    static int N;
    static int[][] map;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
    static List<Wormhole> wormholes;

    static int move(int row, int col, int direction) {
        // 출발지점으로 되돌아 오거나, 블랙홀에 빠질때까지 반복
        int dx = row;
        int dy = col;
        int dir = direction;
        int score = 0;
        while (true) {
            dx += dxdy[dir][0];
            dy += dxdy[dir][1];

            // 벽을 만났을 때
            if (isWall(dx, dy)) {
                dir = changeDir(dir);
                score++;
                continue;
            }

            // 출발지점으로 되돌아 왔을 때 -> 게임 종료
            if (dx == row && dy == col) {
                return score;
            }
            // 블랙홀 만났을 때 -> 게임 종료
            if (map[dx][dy] == -1) return score;


            // 블록에 부딪혔을 때
            if (map[dx][dy] >= 1 && map[dx][dy] <= 5) {
                score++;
                if (map[dx][dy] == 1) { // 1번 블록
                    // 진행방향 우 or 상
                    if (dir == 3 || dir == 0) dir = changeDir(dir);
                    // 하
                    else if (dir == 1) dir = 3;
                    // 좌
                    else if (dir == 2) dir = 0;
                }
                else if (map[dx][dy] == 2) { // 2번 블록
                    if (dir == 3 || dir == 1) dir = changeDir(dir);
                    else if (dir == 0) dir = 3;
                    else if (dir == 2) dir = 1;
                }
                else if (map[dx][dy] == 3) { // 3번 블록
                    if (dir == 1 || dir == 2) dir = changeDir(dir);
                    else if (dir == 0) dir = 2;
                    else if (dir == 3) dir = 1;
                }
                else if (map[dx][dy] == 4) { // 4번 블록
                    if (dir == 0 || dir == 2) dir = changeDir(dir);
                    else if (dir == 1) dir = 2;
                    else if (dir == 3) dir = 0;
                }
                else if (map[dx][dy] == 5) { // 5번 블록
                    dir = changeDir(dir);
                }
            }

            // 웜홀에 빠졌을 때
            if (map[dx][dy] >= 6 && map[dx][dy] <= 10) {
                int idx = map[dx][dy];
                for (int i = 0; i < wormholes.size(); i++) {
                    Wormhole wh = wormholes.get(i);
                    // 번호가 같은 다른 웜홀
                    if (wh.idx == idx && (wh.row != dx || wh.col != dy)) {
                        dx = wh.row;
                        dy = wh.col;
                        break;
                    }
                }
            }
        }
    }

    static int game() {
        // 모든 빈 공간에 대해서 4방으로 게임 진행 - 완전 탐색
        int maxScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        maxScore = Math.max(maxScore, move(i, j, k));
                    }
                }
            }
        }

        return maxScore;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            map = new int[N][N];
            wormholes = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {
                        wormholes.add(new Wormhole(map[i][j], i, j));
                    }
                }
            }

            int ans = game();
            System.out.println("#" + tc + " " + ans);
        }

    }

    static int changeDir(int idx) {
        if (idx == 0) return 1;
        else if (idx == 1) return 0;
        else if (idx == 2) return 3;
        else return 2;
    }

    static boolean isWall(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}