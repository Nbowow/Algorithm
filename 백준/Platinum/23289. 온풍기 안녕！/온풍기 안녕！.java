import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Check {
        int row, col;
        public Check(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Refresher {
        int row, col, dir;
        public Refresher(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    static int R, C, K, W, ans;
    static int[][] map;
    static int[][] tempMap;
    static List<Refresher> refreshers = new ArrayList<>();
    static List<Check> checks = new ArrayList<>();
    static int[][] wallMap;
    static int[][] dxdy = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 오, 왼, 위, 아래

    static void spread(int row, int col, int dir, int[][] map) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[R][C];

        if (dir == 0) { // 오
            q.offer(new int[]{row, col + 1, 5});
            isVisited[row][col+1] = true;
            map[row][col+1] += 5;
        } else if (dir == 1) { // 왼
            q.offer(new int[]{row, col - 1, 5});
            isVisited[row][col-1] = true;
            map[row][col-1] += 5;
        } else if (dir == 2) { // 위
            q.offer(new int[] {row-1, col, 5});
            isVisited[row-1][col] = true;
            map[row-1][col] += 5;
        } else { // 아래
            q.offer(new int[] {row+1, col, 5});
            isVisited[row+1][col] = true;
            map[row+1][col] += 5;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int power = cur[2];
            if (power == 1) continue;

            // 벽이 없어야만 확산 가능
            // dir에 따라 바람 진행방향 달라짐
            if (dir == 0) { // 오
                // -1, 1
                if (isIn(x-1, y) && isIn (x-1, y+1) && !isVisited[x-1][y+1] && (wallMap[x - 1][y] & 1 << 2) == 0 && (wallMap[x - 1][y + 1] & 1) == 0) {
                    isVisited[x-1][y+1] = true;
                    q.offer(new int[]{x - 1, y + 1, power - 1});
                    map[x - 1][y + 1] += power - 1;
                }
                // 0, 1
                if (isIn(x, y+1) && !isVisited[x][y+1] && (wallMap[x][y+1] & 1) == 0) {
                    isVisited[x][y+1] = true;
                    q.offer(new int[]{x, y + 1, power - 1});
                    map[x][y + 1] += power - 1;
                }
                // 1, 1
                if (isIn(x+1, y) && isIn(x+1, y+1) && !isVisited[x+1][y+1] && (wallMap[x + 1][y] & 1 << 3) == 0 && (wallMap[x + 1][y + 1] & 1) == 0) {
                    isVisited[x+1][y+1] = true;
                    q.offer(new int[]{x + 1, y + 1, power - 1});
                    map[x + 1][y + 1] += power - 1;
                }
            } else if (dir == 1) { // 왼
                // -1, -1
                if (isIn(x-1, y) && isIn(x-1, y-1) && !isVisited[x-1][y-1] && (wallMap[x - 1][y] & 1 << 2) == 0 && (wallMap[x - 1][y - 1] & 1 << 1) == 0) {
                    isVisited[x-1][y-1] = true;
                    q.offer(new int[]{x - 1, y - 1, power - 1});
                    map[x - 1][y - 1] += power - 1;
                }
                // 0, -1
                if (isIn(x, y-1) && !isVisited[x][y-1] && (wallMap[x][y - 1] & 1 << 1) == 0) {
                    isVisited[x][y-1] = true;
                    q.offer(new int[]{x, y - 1, power - 1});
                    map[x][y - 1] += power - 1;
                }
                // 1, -1
                if (isIn(x+1, y) && isIn(x+1, y-1) && !isVisited[x+1][y-1] && (wallMap[x + 1][y] & 1 << 3) == 0 && (wallMap[x + 1][y - 1] & 1 << 1) == 0) {
                    isVisited[x + 1][y - 1] = true;
                    q.offer(new int[]{x + 1, y - 1, power - 1});
                    map[x + 1][y - 1] += power - 1;
                }
            } else if (dir == 2) { // 위
                // -1, -1
                if (isIn(x, y-1) && isIn(x-1, y-1) && !isVisited[x-1][y-1] && (wallMap[x][y-1] & 1 << 1) == 0 && (wallMap[x - 1][y - 1] & 1 << 2) == 0) {
                    isVisited[x-1][y-1] = true;
                    q.offer(new int[]{x - 1, y - 1, power - 1});
                    map[x - 1][y - 1] += power - 1;
                }
                // -1, 0
                if (isIn(x-1, y) && !isVisited[x-1][y] && (wallMap[x - 1][y] & 1 << 2) == 0) {
                    isVisited[x-1][y] = true;
                    q.offer(new int[]{x - 1, y, power - 1});
                    map[x - 1][y] += power - 1;
                }
                // -1, 1
                if (isIn(x, y+1) && isIn(x-1, y+1) && !isVisited[x-1][y+1] && (wallMap[x][y + 1] & 1) == 0 && (wallMap[x - 1][y + 1] & 1 << 2) == 0) {
                    isVisited[x-1][y+1] = true;
                    q.offer(new int[]{x - 1, y + 1, power - 1});
                    map[x - 1][y + 1] += power - 1;
                }

            } else { // 아래
                // 1, -1
                if (isIn(x, y-1) && isIn(x+1, y-1) && !isVisited[x+1][y-1] && (wallMap[x][y - 1] & 1 << 1) == 0 && (wallMap[x + 1][y - 1] & 1 << 3) == 0) {
                    isVisited[x+1][y-1] = true;
                    q.offer(new int[]{x + 1, y - 1, power - 1});
                    map[x + 1][y - 1] += power - 1;
                }
                // 1, 0
                if (isIn(x+1, y) && !isVisited[x+1][y] && (wallMap[x + 1][y] & 1 << 3) == 0) {
                    isVisited[x+1][y] = true;
                    q.offer(new int[]{x + 1, y, power - 1});
                    map[x + 1][y] += power - 1;
                }
                // 1, 1
                if (isIn(x, y+1) && isIn(x+1, y+1) && !isVisited[x+1][y+1] && (wallMap[x][y + 1] & 1) == 0 && (wallMap[x + 1][y + 1] & 1 << 3) == 0) {
                    isVisited[x+1][y+1] = true;
                    q.offer(new int[]{x + 1, y + 1, power - 1});
                    map[x + 1][y + 1] += power - 1;
                }

            }

        }

    }

    static void temperature() {
        int[][] temperatureMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tempMap[i][j] > 0) {
                    int curTemp = tempMap[i][j];
                    for (int k = 0; k < 4; k++) {
                        int dx = i + dxdy[k][0];
                        int dy = j + dxdy[k][1];

                        if (!isIn(dx, dy) || tempMap[dx][dy] >= curTemp || (wallMap[dx][dy] & 1<<k) != 0) continue;

                        // (두 칸의 온도 차이)/4 만큼 온도 조절
                        int gap = curTemp - tempMap[dx][dy];
                        temperatureMap[i][j] -= gap/4;
                        temperatureMap[dx][dy] += gap/4;
                    }
                }
            }
        }

        // 모든 칸에 대해 동시에 온도 조절
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tempMap[i][j] += temperatureMap[i][j];
            }
        }

    }

    static void Game() {

        tempMap = new int[R][C];

        while (true) {
            // 1. 모든 온풍이에서 바람 확산
//            System.out.println("====온풍기 바람 확산====");
            for (int i=0; i<refreshers.size(); i++) {
                Refresher refresher = refreshers.get(i);
                spread(refresher.row, refresher.col, refresher.dir, tempMap);
            }

            // 2. 온도 조절
//            System.out.println("====온도 조절====");
            temperature();

            // 3. 온도가 1이상인 가장 바깥쪽 칸 온도 1감소
//            System.out.println("====바깥 온도 1 감소====");
            for (int i = 0; i < R; i++) {
                if(tempMap[i][0] > 0) tempMap[i][0] -= 1;
                if(tempMap[i][C-1] > 0) tempMap[i][C-1] -= 1;
            }

            for (int j = 1; j < C-1; j++) {
                if(tempMap[0][j] > 0) tempMap[0][j] -= 1;
                if(tempMap[R-1][j] > 0) tempMap[R-1][j] -= 1;
            }
            // 4. 초콜릿 + 1
            ans++;
            if (ans == 101) return;

            // 5. 조사하는 모든 칸 검사
            int count = 0;
            for (int i = 0; i < checks.size(); i++) {
                Check cur = checks.get(i);
                if (tempMap[cur.row][cur.col] >= K) count++;
            }

            if (count == checks.size()) {
                return;
            }

        }


    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 온풍기
                if (map[i][j] > 0 && map[i][j] != 5) {
                    refreshers.add(new Refresher(i, j, map[i][j] - 1));
                }
                // 조사해야하는 구역
                if (map[i][j] == 5) {
                    checks.add(new Check(i, j));
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        wallMap = new int[R][C];
        for (int i=0; i<W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            // t == 0 : 위쪽에 벽
            // t == 1 : 오른쪽에 벽
            if (t == 0) {
                wallMap[x][y] += 1<<3;
                if (isIn(x-1, y)) wallMap[x-1][y] += 1<<2;
            } else if (t == 1) {
                wallMap[x][y] += 1<<1;
                if (isIn(x, y+1)) wallMap[x][y+1] += 1;
            }

        }

        Game();
        System.out.println(ans);

    }

    static boolean isIn(int x, int y) {
        return x>=0 && x<R && y>=0 && y<C;
    }

}