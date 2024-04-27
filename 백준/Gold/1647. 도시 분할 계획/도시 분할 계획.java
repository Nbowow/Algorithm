import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        int sNode, eNode, dist;

        public Node(int sNode, int eNode, int dist) {
            this.sNode = sNode;
            this.eNode = eNode;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int N, M;
    static int[] parents;
    static Node[] nodes;

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parents[pa] = find(pb);
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int count = 0;
        int ans = 0;
        nodes = new Node[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(a, b, d);

        }

        Arrays.sort(nodes);

        for (int i = 0; i < M; i++) {
            int sn = nodes[i].sNode;
            int en = nodes[i].eNode;
            int d = nodes[i].dist;

            if (union(sn, en)) {
                if (count == 0) {
                    count += 2;
                    ans += d;
                }
                else {
                    count += 1;
                    ans += d;
                }
            }

            if (count == N) {
                ans -= d;
                break;
            }
        }

        System.out.println(ans);

    }
}