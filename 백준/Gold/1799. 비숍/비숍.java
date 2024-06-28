import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int odd_totalBishop, even_totalBishop;
    static int odd_maxBishop, even_maxBishop;
    static int[][] map;
    static int[][] bishopMap;
    static List<Dot>[] dotList;

    // 대각선 방향 벡터 (좌상, 좌우만 검사)
    static int[][] dxdy = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    static boolean isPossible(int x, int y) {
        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < 2; j++) {
                int tx = x + dxdy[j][0] * i;
                int ty = y + dxdy[j][1] * i;

                if (!isIn(tx, ty)) continue;

                if (map[tx][ty] == 2) return false;
            }
        }

        return true;
    }

    static void backTracking(int deli, int idx, int count) {

        for (int i = idx; i < dotList[deli].size(); i++) {

            Dot temp = dotList[deli].get(i);

            int r = temp.x;
            int c = temp.y;

            if (isPossible(r, c)) {
                map[r][c] = 2;
                backTracking(deli, i + 1, count + 1);
                map[r][c] = 1;
            }

        }

        if (deli == 0) even_maxBishop = Math.max(even_maxBishop, count);
        else odd_maxBishop = Math.max(odd_maxBishop, count);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dotList = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            dotList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    if ((i+j) % 2 == 0) dotList[0].add(new Dot(i, j));
                    else dotList[1].add(new Dot(i, j));
                }
            }
        }

        // 홀수 칸, 짝수 칸 나눠서 백트래킹 돌리기

        // 짝수 칸
        bishopMap = new int[N][N];
        backTracking(0, 0, 0);

        // 홀수 칸
        bishopMap = new int[N][N];
        backTracking(1, 0, 0);

        System.out.println(even_maxBishop + odd_maxBishop);
    }

    static void printMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}