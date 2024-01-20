import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int ans[] = new int[2];

    public static void checkPaper(int num, int paper[][], int r, int c) {

        // 1사분면
        int color1 = paper[r][c];
        int check1 = 1;
        for (int i = r; i < r+num/2; i++) {
            for (int j = c; j < c+num/2; j++) {
                if (paper[i][j] != color1) {
                    checkPaper(num/2, paper, r, c);
                    check1 = 0;
                    break;
                }
            }
            if (check1 == 0) break;
        }
        if (check1 == 1) ans[paper[r][c]] += 1;

        // 2사분면
        int color2 = paper[r][c+num/2];
        int check2 = 1;
        for (int i = r; i < r+num/2; i++) {
            for (int j = c+num/2; j < c+num; j++) {
                if (paper[i][j] != color2) {
                    checkPaper(num/2, paper, r, c+num/2);
                    check2 = 0;
                    break;
                }
            }
            if (check2 == 0) break;
        }
        if (check2 == 1) ans[paper[r][c+num/2]] += 1;

        // 3사분면
        int color3 = paper[r+num/2][c];
        int check3 = 1;
        for (int i = r+num/2; i < r+num; i++) {
            for (int j = c; j < c+num/2; j++) {
                if (paper[i][j] != color3) {
                    checkPaper(num/2, paper, r+num/2, c);
                    check3 = 0;
                    break;
                }
            }
            if (check3 == 0) break;
        }
        if (check3 == 1) ans[paper[r+num/2][c]] += 1;

        // 4사분면
        int color4 = paper[r+num/2][c+num/2];
        int check4 = 1;
        for (int i = r+num/2; i < r+num; i++) {
            for (int j = c+num/2; j < c+num; j++) {
                if (paper[i][j] != color4) {
                    checkPaper(num/2, paper, r+num/2, c+num/2);
                    check4 = 0;
                    break;
                }
            }
            if (check4 == 0) break;
        }
        if (check4 == 1) ans[paper[r+num/2][c+num/2]] += 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 색종이 색이 같은지 체크
        int check = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (paper[i][j] != paper[0][0]) {
                    check = 0;
                    break;
                }
            }
        }
        // 모든 색종이 색이 같을 경우
        if (check == 1) {
            ans[paper[0][0]] += 1;
        }
        else checkPaper(N, paper, 0, 0);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    }
}