import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int C;
    static int ans;
    static int[][] map;
    static boolean[] isVisited;

    static void backTracking(int idx, int val) {

        if (idx == 11) {

            ans = Math.max(ans, val);
            return;
        }

        for (int j = 0; j < 11; j++) {
            if (!isVisited[j] && map[idx][j] != 0) {
                isVisited[j] = true;
                backTracking(idx + 1, val + map[idx][j]);
                isVisited[j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= C; tc++) {
            ans = 0;
            map = new int[11][11];
            isVisited = new boolean[11];
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backTracking(0, 0);
            sb.append(ans + "\n");
        }


        System.out.println(sb);
    }
}