import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(map[i], 10000 * 500);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
        }

        // 플로이드 워셜
        int minCycle = 10000 * 500;
        for (int k = 0; k < V; k++) { // 경로
            for (int i = 0; i < V; i++) { // 출발지
                for (int j = 0; j < V; j++) { // 도착지
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    minCycle = Math.min(minCycle, map[i][j] + map[j][i]);
                }
            }
        }

        System.out.println(minCycle == 10000 * 500 ? -1 : minCycle);
    }
}