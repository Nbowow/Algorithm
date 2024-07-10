import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] route;
    static int[] parents;

    static int find(int x) {
        if (x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parents[pa] = pb;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N][N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    // 경로 생성
                    union(i, j);
                }
            }
        }

        route = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 최상단 부모 찾기
        for (int i = 0; i < N; i++) {
            find(i);
        }

        // 이동 가능한 경로인지 판단
        for (int i = 0; i < M; i++) {
            if (parents[route[i]] != parents[route[0]]) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}