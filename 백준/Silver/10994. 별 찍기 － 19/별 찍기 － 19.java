import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] map;

    static void recur(int depth, int idx) {

        if (depth <= 0) return;

        for (int i = idx; i < idx+4 * depth - 3; i++) {
            map[idx][i] = '*';
            map[i][idx] = '*';
            map[i][idx+4 * depth - 4] = '*';
            map[idx+4 * depth - 4][i] = '*';
        }

        recur(depth-1, idx + 2);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[4 * N - 3][4 * N - 3];

        // 배열을 빈 칸으로 초기화
        for (int i = 0; i < 4 * N - 3; i++) {
            for (int j = 0; j < 4 * N - 3; j++) {
                map[i][j] = ' ';
            }
        }

        recur(N, 0);

        for (int i = 0; i < 4 * N - 3; i++) {
            for (int j = 0; j < 4 * N - 3; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}