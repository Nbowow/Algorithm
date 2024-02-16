import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    static int ans;
    static char[][] map;

    static boolean[][] isVisited;
    // 상, 하, 좌, 우
    static int[][] dxdy = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static void dfs(int row, int col) {
        if (ans == 1) return;

        for (int i = 0; i < 4; i++) {
            int dx = row + dxdy[i][0];
            int dy = col + dxdy[i][1];

            if (dx >= 0 && dx < 100 && dy >= 0 && dy < 100) {
                if (!isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;

                    if (map[dx][dy] == '0') {
                        dfs(dx, dy);
                    }
                    else if (map[dx][dy] == '3') {
                        ans = 1;
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 0; tc < 10; tc++) {
            int T = Integer.parseInt(br.readLine());

            map = new char[100][100];
            isVisited = new boolean[100][100];
            for (int i = 0; i < 100; i++) {
                map[i] = br.readLine().toCharArray();
            }
            ans = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (map[i][j] == '2') {
                        dfs(i, j);
                        isVisited[i][j] = true;
                    }
                }
            }

            System.out.println("#" + T + " " + ans);
        }
    }

}