import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[][] sudo = new int[9][9];
    static boolean[][] isVisited = new boolean[9][9];
    static int count;

    static boolean checkRow(int row, int num) {
        for (int j=0; j<9; j++) {
            if (sudo[row][j] == num) return false;
        }
        return true;
    }

    static boolean checkCol(int col, int num) {
        for (int i=0; i<9; i++) {
            if (sudo[i][col] == num) return false;
        }
        return true;
    }

    static boolean checkSquare(int row, int col, int num) {
        int sr = row / 3 * 3;
        int sc = col / 3 * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudo[i][j] == num) return false;
            }
        }
        return true;
    }

    static void backtracking(int idx, int depth) {
        if (depth == count) {
            printSudo();
            System.exit(0);
        }


        for (int i=idx; i<81; i++) {
            int x = i/9;
            int y = i%9;

            if (sudo[x][y] == 0) {
                for (int n=1; n<=9; n++) { // 1부터 9까지 검사
                    if (checkRow(x, n) && checkCol(y, n) && checkSquare(x, y, n)) {
                        sudo[x][y] = n;
                        backtracking(i+1, depth + 1);
                        sudo[x][y] = 0;
                    }
                }
                return;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i<9 ;i++) {
            String temp = br.readLine();
            for (int j=0; j<9; j++) {
                sudo[i][j] = (int) temp.charAt(j) - 48;
                if (sudo[i][j] == 0) count++;
            }

        }
        backtracking(0, 0);
    }

    static void printSudo() {
        for (int i=0; i<9 ;i++) {
            for (int j=0; j<9; j++) {
                System.out.print(sudo[i][j]);
            }
            System.out.println();
        }
    }

}