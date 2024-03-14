import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Ball {
        char color;
        int row, col;
        boolean isAlive;
        public Ball(char color, int row, int col, boolean isAlive) {
            this.color = color;
            this.row = row;
            this.col = col;
            this.isAlive = isAlive;
        }
    }

    static int N, M;
    static char[][] map;
    static Ball[] balls = new Ball[2];
    static int ans = Integer.MAX_VALUE;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void go(int row, int col, char color, char[][] tempMap, int dir) {
        tempMap[row][col] = '.';

        int nextRow = row;
        int nextCol = col;

        boolean isFall = false;
        while (true) {
            nextRow += dxdy[dir][0];
            nextCol += dxdy[dir][1];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) break;

            if (tempMap[nextRow][nextCol] == 'O') {
                isFall = true;
                break;
            }
            else if (tempMap[nextRow][nextCol] == '.') continue;
            else break;
        }

        if (isFall) {
            if (color == 'R') {
                balls[0].isAlive = false;
            }
            else {
                balls[1].isAlive = false;
            }
        }

        else {
            nextRow -= dxdy[dir][0];
            nextCol -= dxdy[dir][1];
            if (color == 'R') {
                balls[0].row = nextRow;
                balls[0].col = nextCol;
                tempMap[nextRow][nextCol] = 'R';
            }
            else {
                balls[1].row = nextRow;
                balls[1].col = nextCol;
                tempMap[nextRow][nextCol] = 'B';
            }
        }
    }

    // 완탐
    // 구슬 둘중에 하나라도 들어가면 backtracking
    static void dfs(int depth, char[][] arr, int dir) {
        // pruning
        if (depth == 10 || ans <= depth) return;

        int rrow = balls[0].row;
        int rcol = balls[0].col;
        int brow = balls[1].row;
        int bcol = balls[1].col;

        for (int i=0; i<4; i++) {
        	balls[0].isAlive = true;
            balls[1].isAlive = true;
            // 상
            if (i == 0 && i != dir) {

                char[][] temp1 = copyMap(arr);
                if (rrow < brow) { // 빨간 공이 더 위에 있을 경우 파란 공을 먼저 이동시켜줘야 함
                    go(rrow, rcol, 'R', temp1, i);
                    go(brow, bcol, 'B', temp1, i);
                }
                else { // 파란 공이 더 위에 있을 경우 파란 공을 먼저 이동시켜줘야 함
                    go(brow, bcol, 'B', temp1, i);
                    go(rrow, rcol, 'R', temp1, i);
                }

                // 빨간공이 구멍에 들어갔을 경우
                if (!balls[0].isAlive) {
                    // 파란공은 살아있을 경우
                    if (balls[1].isAlive) {
                        ans = Math.min(ans, depth + 1);
                        return;
                    }
                }
                else { // 빨간공 안들어감
                    // 파란공이 안들어갔을 경우
                    if (balls[1].isAlive) dfs(depth+1, temp1, i);
                }
            }

            // 하
            else if (i == 1 && i != dir) {
            	
                char[][] temp2 = copyMap(arr);
                if (rrow > brow) {
                    go(rrow, rcol, 'R', temp2, i);
                    go(brow, bcol, 'B', temp2, i);
                }
                else {
                    go(brow, bcol, 'B', temp2, i);
                    go(rrow, rcol, 'R', temp2, i);
                }

                if (!balls[0].isAlive) {
                    if (balls[1].isAlive) {
                        ans = Math.min(ans, depth + 1);
                        return;
                    }
                }
                else {
                    if (balls[1].isAlive) dfs(depth+1, temp2, i);
                }

            }

            // 좌
            else if (i == 2 && i != dir) {

                char[][] temp3 = copyMap(arr);
                if (rcol < bcol) {
                    go(rrow, rcol, 'R', temp3, i);
                    go(brow, bcol, 'B', temp3, i);
                }
                else {
                    go(brow, bcol, 'B', temp3, i);
                    go(rrow, rcol, 'R', temp3, i);
                }

                if (!balls[0].isAlive) {
                    if (balls[1].isAlive) {
                        ans = Math.min(ans, depth + 1);
                        return;
                    }
                }
                else {
                    if (balls[1].isAlive) dfs(depth+1, temp3, i);
                }

            }

            // 우
            else if (i == 3 && i != dir){

                char[][] temp4 = copyMap(arr);
                if (rcol > bcol) {
                    go(rrow, rcol, 'R', temp4, i);
                    go(brow, bcol, 'B', temp4, i);
                }
                else {
                    go(brow, bcol, 'B', temp4, i);
                    go(rrow, rcol, 'R', temp4, i);
                }

                if (!balls[0].isAlive) {
                    if (balls[1].isAlive) {
                        ans = Math.min(ans, depth + 1);
                        return;
                    }
                }
                else {
                    if (balls[1].isAlive) dfs(depth+1, temp4, i);
                }

            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    balls[0] = new Ball('R', i, j, true);
                }
                else if (map[i][j] == 'B') {
                    balls[1] = new Ball('B', i, j, true);
                }
            }
        }

        dfs(0, map, -1);

        if (ans == Integer.MAX_VALUE) System.out.println(0);
        else {
            System.out.println(1);
        }
    }

    static char[][] copyMap(char[][] arr) {
        char[][] temp = new char[N][M];
        for (int i=0; i<N; i++) {
            temp[i] = arr[i].clone();
        }
        return temp;
    }
}