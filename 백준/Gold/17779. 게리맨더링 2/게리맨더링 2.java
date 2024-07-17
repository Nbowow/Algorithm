import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[][] population;
    static int[][] region;

    static void cal() {

        int[] reg = new int[5];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (region[i][j] == 1) {
                    reg[0] += population[i][j];
                } else if (region[i][j] == 2) {
                    reg[1] += population[i][j];
                } else if (region[i][j] == 3) {
                    reg[2] += population[i][j];
                } else if (region[i][j] == 4) {
                    reg[3] += population[i][j];
                } else reg[4] += population[i][j];
            }
        }

        Arrays.sort(reg);
        ans = Math.min(ans, reg[4] - reg[0]);

    }

    static void makeRegion(int x, int y, int d1, int d2) {

        // 5번구역
        for (int d = 0; d <= d1; d++) {
            region[x-1+d][y-1-d] = 5;
        }
        for (int d = 0; d <= d2; d++) {
            region[x-1+d][y-1+d] = 5;
        }
        for (int d = 0; d <= d2; d++) {
            region[x-1+d1+d][y-1-d1+d] = 5;
        }
        for (int d = 0; d <= d1; d++) {
            region[x-1+d2+d][y-1+d2-d] = 5;
        }

        for (int i = x - 1 + 1; i <= x - 1 + d1 + d2 - 1; i++) {
            loop:
            for (int j = 0; j < N; j++) {
                if (region[i][j] == 5) {
                    int k = j;
                    while (true) {
                        region[i][k++] = 5;
                        if (region[i][k] == 5) break loop;
                    }
                }
            }
        }

        // 1번구역
        for (int i = 0; i < x + d1 -1; i++) {
            for (int j = 0; j <= y - 1; j++) {
                if (region[i][j] == 5) break;
                region[i][j] = 1;
            }
        }

        // 2번구역
        for (int i = 0; i <= x + d2 -1; i++) {
            for (int j = y; j <= N - 1; j++) {
                if (region[i][j] == 0) region[i][j] = 2;
            }
        }

        // 3번구역
        for (int i = x + d1 - 1; i <= N - 1; i++) {
            for (int j = 0; j < y - d1 + d2 - 1; j++) {
                if (region[i][j] == 5) break;
                region[i][j] = 3;
            }
        }

        // 4번구역
        for (int i = x + d2; i <= N - 1; i++) {
            for (int j = y - d1 + d2 -1 ; j <= N - 1; j++) {
                if (region[i][j] == 0) region[i][j] = 4;
            }
        }

        cal();

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        population = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 기준점(x, y)과 d1, d2 정하기
        for (int x = 1; x < N; x++) {
            for (int y = 1; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (!isRegion(x, y, d1, d2)) continue;

                        region = new int[N][N];
                        makeRegion(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);

    }

    static boolean isRegion(int x, int y, int d1, int d2) {
        return x+d1+d2 <= N && y-d1 >= 1 && y+d2 <= N;
    }

}