import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        int[][] tempMap = new int[N][N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tempMap[i][j] = map[i][j];
                total += map[i][j];
            }
        }

        // 플로이드 워셜
        int ans = 0;
        for (int k = 0; k < N; k++) { // 경로
            for (int i = 0; i < N; i++) { // 출발지
                for (int j = 0; j < N; j++) { // 도착지
                    // 경로와 출발지 or 도착지가 같으면 안됨
                    if (k == i || k == j) continue;

                    // 불가능한(모순된) 경우 (최솟값 보다 더 최소가 존재)
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        System.out.println(-1);
                        System.exit(0);
                    }

                    // 이 다리는 놓을 필요가 없음.
                    // 최소비용으로 다른 경로로 갈 수 있기 때문
                    if (map[i][j] == map[i][k] + map[k][j]) {
                        tempMap[i][j] = 0;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += tempMap[i][j];
            }
        }

        System.out.println(count/2);
    }
    
}