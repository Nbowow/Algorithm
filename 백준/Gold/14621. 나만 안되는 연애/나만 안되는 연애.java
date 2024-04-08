import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Road implements Comparable<Road>{
        int start, end, dist;

        public Road(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road r) {
            return Integer.compare(this.dist, r.dist);
        }
    }

    static int N, M;
    static int[] parents;

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        // pb의 부모값 할당
        parents[pa] = pb;
        return true;
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // sex input
        st = new StringTokenizer(br.readLine());
        char[] sex = new char[N];
        for (int i = 0; i < N; i++) {
            sex[i] = st.nextToken().charAt(0);
        }

        // road input
        Road[] roads = new Road[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            roads[i] = new Road(u, v, d);
        }

        // kruscal

        // union-find 기본값 설정
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        Arrays.sort(roads);

        int ans = 0;
        int len = 0;
        for (int i = 0; i < M; i++) {
            Road cur = roads[i];
            int start = cur.start;
            int end = cur.end;

            // 아직 연결되지 않았고, 성별이 같지 않다면 -> 연결
            if (sex[start] != sex[end] && union(start, end)) {
                ans += cur.dist;
                len++;
            }
        }

        System.out.println(len == N-1 ? ans : -1);
    }
}