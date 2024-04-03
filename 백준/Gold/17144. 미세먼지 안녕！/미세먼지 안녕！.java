import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Dust {
        int row;
        int col;
        int weight;

        public Dust(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }
    }

    static int R, C, T;
    static boolean isFinish;
    static int[][] map;
    static int[][] copy;
    static int r1x, r1y, r2x, r2y;
    static Queue<Dust> d;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void Left(int row) {
        for (int j = 0; j < C - 1 ; j++) {
            map[row][j] = map[row][j + 1];
        }
    }

    static void Right(int row) {
        for (int j = C-1; j > 0; j--) {
            map[row][j] = map[row][j - 1];
        }
    }

    static void Down(int srow, int frow, int col) {
        for (int i = frow; i > srow; i--) {
            map[i][col] = map[i - 1][col];
        }
    }

    static void Up(int srow, int frow, int col) {
        for (int i = frow ; i < srow; i++) {
            map[i][col] = map[i + 1][col];
        }
    }
    static void cleaner() {
//        copyMap();
        // 위쪽 공기 청정기
    	Down(0, r1x, 0);
    	Left(0);
    	Up(r1x, 0, C-1);
        Right(r1x);

        // 아래쪽 공기 청정기
        Up(R - 1, r2x, 0);
        Left(R-1);
        Down(r2x, R - 1, C-1);
        Right(r2x);
        
        map[r1x][r1y+1] = 0;
        map[r2x][r2y+1] = 0;
        
        // 공기청정기 위치 원상복귀
        map[r1x][r1y] = -1;
        map[r2x][r2y] = -1;

    }

    static void dust() {
        // 초기 미세먼지 확인
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    d.offer(new Dust(i, j, map[i][j]));
                }
            }
        }

        // 더이상 미세먼지 없을 경우 끝
        if (d.isEmpty()) {
            isFinish = true;
            return;
        }

        // 확산
        while (!d.isEmpty()) {
            Dust dtemp = d.poll();

            // 4방향 확산
            int count = 0;
            for (int j = 0; j < 4; j++) {
                int dx = dtemp.row + dxdy[j][0];
                int dy = dtemp.col + dxdy[j][1];

                if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
                    // 공기 청정기가 아니라면 확산
                    if (map[dx][dy] != -1) {
                        map[dx][dy] += dtemp.weight/5;
                        count += 1;
                    }
                }
            }

            map[dtemp.row][dtemp.col] -= (dtemp.weight/5) * count;
        }

    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        copy = new int[R][C];
        
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 위쪽 공기청정기
                if (map[i][j] == -1 && r1x == 0) {
                    r1x = i; r1y = j;
                }
                // 아래쪽 공기청정기
                else if (map[i][j] == -1 && r1x != 0) {
                    r2x = i; r2y = j;
                }
            }
        }
        // T초 동안 반복
        while(T-- > 0) {
            d = new ArrayDeque<>();
            dust();

            if(isFinish) break;
            cleaner();
        }

        if (isFinish) System.out.println(0);
        else {
            int ans = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != -1) ans += map[i][j];
                }
            }
            System.out.println(ans);
        }

    }

    static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void copyMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

}