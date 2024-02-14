import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static boolean checkMap(int srow, int scol, int lrow, int lcol) {

        int temp = map[srow][scol];
        for (int i = srow; i < lrow; i++) {
            for (int j = scol; j < lcol; j++) {
                if (map[i][j] != temp) return false;
            }
        }

        return true;
    }

    static void divide(int N, int row, int col) {
        sb.append("(");

        // 1사분면 체크
        if (checkMap(row, col, row + N/2, col + N/2)) {

            sb.append(map[row][col]);
        } else divide(N/2, row, col);

        // 2사분면 체크
        if (checkMap(row, col + N/2, row + N/2, col +N)) {
            sb.append(map[row][col + N / 2]);
        } else divide(N / 2, row, col + N / 2);

        // 3사분면 체크
        if (checkMap(row + N / 2, col, row + N, col + N / 2)) {
            sb.append(map[row + N / 2][col]);
        } else divide(N / 2, row + N / 2, col);

        // 4사분면 체크
        if (checkMap(row + N / 2, col + N / 2, row + N, col + N)) {
            sb.append(map[row + N / 2][col + N / 2]);

        } else divide(N / 2, row + N / 2, col + N / 2);

        sb.append(")");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = charArray[j] - 48;
            }
        }
        // 전체 같은지 체크
        if (checkMap(0, 0, N, N)) {
            sb.append(map[0][0]);
        } else divide(N, 0, 0);



        System.out.println(sb);
    }
}