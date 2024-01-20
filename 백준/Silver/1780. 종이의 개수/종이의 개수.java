import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] paper;
    public static int[] ans = new int[3];

    public static void makePaper() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    public static void checkPaper(int n, int row, int col) {
        // 1
        int c1 = 1;
        xx1:
        for (int i = row; i < row + n / 3; i++) {
            for (int j = col; j < col + n / 3; j++) {
                if (paper[i][j] != paper[row][col]) {
                    checkPaper(n/3, row, col);
                    c1 = 0;
                    break xx1;
                }
            }
        }
        if (c1 == 1) {
//            System.out.println("c1 = " + c1 + " paper : " + paper[row][col]);

            if (paper[row][col] == -1) ans[2] += 1;
            else ans[paper[row][col]] += 1;
        }

        // 2
        int c2 = 1;
        xx2:
        for (int i = row; i < row + n / 3; i++) {
            for (int j = col + n / 3; j < col + (n / 3) * 2; j++) {
                if (paper[i][j] != paper[row][col + n / 3]) {
                    checkPaper(n / 3, row, col + n / 3);
                    c2 = 0;
                    break xx2;
                }
            }
        }
        if (c2 == 1) {
//            System.out.println("c2 = " + c2 + " paper : " + paper[row][col + n / 3]);

            if (paper[row][col + n / 3] == -1) ans[2] += 1;
            else ans[paper[row][col + n / 3]] += 1;
        }

        // 3
        int c3 = 1;
        xx3:
        for (int i = row; i < row + n / 3; i++) {
            for (int j = col + (n / 3) * 2; j < col + n; j++) {
                if (paper[i][j] != paper[row][col + (n / 3) * 2]) {
                    checkPaper(n/3, row, col + (n / 3) * 2);
                    c3 = 0;
                    break xx3;
                }
            }
        }
        if (c3 == 1) {
//            System.out.println("c3 = " + c3 + " paper : " + paper[row][col + (n / 3) * 2]);

            if (paper[row][col + (n / 3) * 2] == -1) ans[2] += 1;
            else ans[paper[row][col + (n / 3) * 2]] += 1;
        }

        // 4
        int c4 = 1;
        xx4:
        for (int i = row + n / 3; i < row + (n / 3) * 2; i++) {
            for (int j = col; j < col + n / 3; j++) {
                if (paper[i][j] != paper[row + n / 3][col]) {
                    checkPaper(n / 3, row + n / 3, col);
                    c4 = 0;
                    break xx4;
                }
            }
        }
        if (c4 == 1) {
//            System.out.println("c4 = " + c4 + " paper : " + paper[row + n / 3][col]);

            if (paper[row + n / 3][col] == -1) ans[2] += 1;
            else ans[paper[row + n / 3][col]] += 1;
        }

        // 5
        int c5 = 1;
        xx5:
        for (int i = row + n / 3; i < row + (n / 3) * 2; i++) {
            for (int j = col + n / 3; j < col + (n / 3) * 2; j++) {
                if (paper[i][j] != paper[row + n / 3][col + n / 3]) {
                    checkPaper(n / 3, row + n / 3, col + n / 3);
                    c5 = 0;
                    break xx5;
                }
            }
        }
        if (c5 == 1) {
//            System.out.println("c5 = " + c5 + " paper : " + paper[row + n / 3][col + n / 3]);

            if (paper[row + n / 3][col + n / 3] == -1) ans[2] += 1;
            else ans[paper[row + n / 3][col + n / 3]] += 1;
        }

        // 6
        int c6 = 1;
        xx6:
        for (int i = row + n / 3; i < row + (n / 3) * 2; i++) {
            for (int j = col + (n / 3) * 2; j < col + n; j++) {
                if (paper[i][j] != paper[row + n / 3][col + (n / 3) * 2]) {
                    checkPaper(n / 3, row + n / 3, col + (n / 3) * 2);
                    c6 = 0;
                    break xx6;
                }
            }
        }
        if (c6 == 1) {
//            System.out.println("c6 = " + c6 + " paper : " + paper[row + n / 3][col + (n / 3) * 2]);

            if (paper[row + n / 3][col + (n / 3) * 2] == -1) ans[2] += 1;
            else ans[paper[row + n / 3][col + (n / 3) * 2]] += 1;
        }

        // 7
        int c7 = 1;
        xx7:
        for (int i = row + (n / 3) * 2; i < row + n; i++) {
            for (int j = col; j < col + n / 3; j++) {
                if (paper[i][j] != paper[row + (n / 3) * 2][col]) {
                    checkPaper(n / 3, row + (n / 3) * 2, col);
                    c7 = 0;
                    break xx7;
                }
            }
        }
        if (c7 == 1) {
//            System.out.println("c7 = " + c7 + " paper : " + paper[row + (n / 3) * 2][col]);

            if (paper[row + (n / 3) * 2][col] == -1) ans[2] += 1;
            else ans[paper[row + (n / 3) * 2][col]] += 1;
        }

        // 8
        int c8 = 1;
        xx8:
        for (int i = row + (n / 3) * 2; i < row + n; i++) {
            for (int j = col + n / 3; j < col + (n / 3) * 2; j++) {
                if (paper[i][j] != paper[row + (n / 3) * 2][col + n / 3]) {
                    checkPaper(n / 3, row + (n / 3) * 2, col + n / 3);
                    c8 = 0;
                    break xx8;
                }
            }
        }
        if (c8 == 1) {
//            System.out.println("c8 = " + c8 + " paper : " + paper[row + (n / 3) * 2][col + n / 3]);

            if (paper[row + (n / 3) * 2][col + n / 3] == -1) ans[2] += 1;
            else ans[paper[row + (n / 3) * 2][col + n / 3]] += 1;
        }

        // 9
        int c9 = 1;
        xx9:
        for (int i = row + (n / 3) * 2; i < row + n; i++) {
            for (int j = col + (n / 3) * 2; j < col + n; j++) {
                if (paper[i][j] != paper[row + (n / 3) * 2][col + (n / 3) * 2]) {
                    checkPaper(n / 3, row + (n / 3) * 2, col + (n / 3) * 2);
                    c9 = 0;
                    break xx9;
                }
            }
        }
        if (c9 == 1) {
//            System.out.println("c9 = " + c9 + " paper : " + paper[row + (n / 3) * 2][col + (n / 3) * 2]);
            if (paper[row + (n / 3) * 2][col + (n / 3) * 2] == -1) ans[2] += 1;
            else ans[paper[row + (n / 3) * 2][col + (n / 3) * 2]] += 1;
        }

    }

    public static void main(String[] args) throws IOException{
        makePaper();

        int p1 = paper[0][0];
        int check = 1;

        // 전체 다 똑같은지 확인
        total:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (paper[i][j] != p1) {
                    checkPaper(N, 0, 0);
                    check = 0;
                    break total;
                }
            }
        }
        if (check == 1) {
            if (paper[0][0] == -1) ans[2] = 1;
            else ans[paper[0][0]] = 1;
        }

        System.out.println(ans[2]);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}