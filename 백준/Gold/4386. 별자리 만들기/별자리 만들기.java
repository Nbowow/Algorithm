import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Star implements Comparable<Star>{
        int sIdx, eIdx;
        double d;

        public Star(int sIdx, int eIdx, double d) {
            this.sIdx = sIdx;
            this.eIdx = eIdx;
            this.d = d;
        }

        @Override
        public int compareTo(Star o) {
            return Double.compare(this.d, o.d);
        }
    }

    static double[][] stars;
    static double ans;
    static List<Star> dist;
    static int[] parents;

    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        // 이미 MST에 포함되어 있는 간선
        if (pa == pb) return false;

        parents[pa] = find(pb);
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        stars = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i][0] = x;
            stars[i][1] = y;
        }

        dist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                double starDist = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
                dist.add(new Star(i, j, starDist));
            }
        }

        Collections.sort(dist);

        // kruscal
        // 기본 배열 초기화
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        // union - find
        for (int i = 0; i < dist.size(); i++) {
            int sIdx = dist.get(i).sIdx;
            int eIdx = dist.get(i).eIdx;
            double d = dist.get(i).d;

            if (union(sIdx, eIdx)) ans+=d;
        }

        System.out.printf("%.2f",ans);

    }
}