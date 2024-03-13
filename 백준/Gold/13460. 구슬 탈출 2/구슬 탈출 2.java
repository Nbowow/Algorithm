import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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
    static List<Integer> ans = new ArrayList<>();
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
        if (depth == 10) return;

        int rrow = balls[0].row;
        int rcol = balls[0].col;
        int brow = balls[1].row;
        int bcol = balls[1].col;

        for (int i=0; i<4; i++) {
            // 상
            if (i == 0 && i != dir) {
                balls[0].isAlive = true;
                balls[1].isAlive = true;

                char[][] temp1 = copyMap(arr);
                if (rrow < brow) {
                    go(rrow, rcol, 'R', temp1, i);
                    go(brow, bcol, 'B', temp1, i);
                }
                else {
                    go(brow, bcol, 'B', temp1, i);
                    go(rrow, rcol, 'R', temp1, i);
                }

//                System.out.println(depth + "=1=");
//                printMap(temp1);


                // 빨간공이 구멍에 들어갔을 경우
                if (!balls[0].isAlive) {
                    // 파란공은 살아있을 경우
                    if (balls[1].isAlive) {
                        ans.add(depth + 1);
                    }
                }
                else { // 빨간공 안들어감
                    // 파란공이 안들어갔을 경우
                    if (balls[1].isAlive) dfs(depth+1, temp1, i);
                }
            }

            // 하
            else if (i == 1 && i != dir) {
                balls[0].isAlive = true;
                balls[1].isAlive = true;

                char[][] temp2 = copyMap(arr);
                if (rrow > brow) {
                    go(rrow, rcol, 'R', temp2, i);
                    go(brow, bcol, 'B', temp2, i);
                }
                else {
                    go(brow, bcol, 'B', temp2, i);
                    go(rrow, rcol, 'R', temp2, i);
                }

//                System.out.println(depth + "=2=");
//                printMap(temp2);


                // 빨간공이 구멍에 들어갔을 경우
                if (!balls[0].isAlive) {
                    // 파란공은 살아있을 경우
                    if (balls[1].isAlive) {
                        ans.add(depth+1);
                    }
                    balls[0].isAlive = true;
                    balls[1].isAlive = true;
                }
                else { // 빨간공 안들어감
                    // 파란공이 안들어갔을 경우
                    if (balls[1].isAlive) dfs(depth+1, temp2, i);
                }

            }

            // 좌
            else if (i == 2 && i != dir) {
                balls[0].isAlive = true;
                balls[1].isAlive = true;

                char[][] temp3 = copyMap(arr);
                if (rcol < bcol) {
                    go(rrow, rcol, 'R', temp3, i);
                    go(brow, bcol, 'B', temp3, i);
                }
                else {
                    go(brow, bcol, 'B', temp3, i);
                    go(rrow, rcol, 'R', temp3, i);
                }

//                System.out.println(depth + "=3=");
//                printMap(temp3);


                // 빨간공이 구멍에 들어갔을 경우
                if (!balls[0].isAlive) {
                    // 파란공은 살아있을 경우
                    if (balls[1].isAlive) {
                        ans.add(depth+1);
                    }
                    balls[0].isAlive = true;
                    balls[1].isAlive = true;
                }
                else { // 빨간공 안들어감
                    // 파란공이 안들어갔을 경우
                    if (balls[1].isAlive) dfs(depth+1, temp3, i);
                }

            }

            // 우
            else if (i == 3 && i != dir){
                balls[0].isAlive = true;
                balls[1].isAlive = true;

                char[][] temp4 = copyMap(arr);
                if (rcol > bcol) {
                    go(rrow, rcol, 'R', temp4, i);
                    go(brow, bcol, 'B', temp4, i);
                }
                else {
                    go(brow, bcol, 'B', temp4, i);
                    go(rrow, rcol, 'R', temp4, i);
                }

//                System.out.println(depth + "=4=");
//                printMap(temp4);
//                System.out.println("r : " + balls[0].isAlive);
//                System.out.println("b : " + balls[1].isAlive);

                // 빨간공이 구멍에 들어갔을 경우
                if (!balls[0].isAlive) {
                    // 파란공은 살아있을 경우
                    if (balls[1].isAlive) {
                        ans.add(depth+1);
                    }
                    balls[0].isAlive = true;
                    balls[1].isAlive = true;
                }
                else { // 빨간공 안들어감
                    // 파란공이 안들어갔을 경우
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


        if (ans.size() == 0) System.out.println(-1);
        else {
            Collections.sort(ans);
            System.out.println(ans.get(0));
        }
    }

    static void printMap(char[][] arr) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
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