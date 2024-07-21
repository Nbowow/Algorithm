import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int max_square;

    static void check(int r, int c) {

        // 맵의 끝이거나 이 전에 정사각형이 만들어지지 않았을 경우
        if (!isIn(r-1, c-1) || map[r-1][c-1] == 0) return;

        int curLen = 1;
        for (int i = 1; i <= map[r-1][c-1]; i++) {
            if (map[r-i][c] == 0 || map[r][c-i] == 0) break;
            curLen = i + 1;
        }

        map[r][c] = curLen;

        max_square = Math.max(max_square, map[r][c]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j] - '0';
                if (map[i][j] == 1) max_square = 1;
            }
        }


        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    check(i, j);
                }
            }
        }
        System.out.println(max_square * max_square);

    }

    static void printMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}