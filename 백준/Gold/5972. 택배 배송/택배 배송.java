import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx, value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    static int N, M;
    static List<Node>[] nodes;
    static int[] dp;

    static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        boolean[] isVisited = new boolean[N];

        dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int sIdx = cur.idx;

            if (isVisited[sIdx]) continue;
            isVisited[sIdx] = true;

            for (Node temp : nodes[sIdx]) {
                if (dp[temp.idx] > dp[sIdx] + temp.value) {
                    dp[temp.idx] = dp[sIdx] + temp.value;
                    pq.offer(new Node(temp.idx, dp[temp.idx]));
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new List[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, v));
            nodes[e].add(new Node(s, v));

        }

        dijkstra();
        
        System.out.println(dp[N-1]);
    }
}