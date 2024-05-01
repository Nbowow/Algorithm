import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int endIdx, weight;

        Node(int endIdx, int weight) {
            this.endIdx = endIdx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.weight, this.weight);
        }
    }

    static int n, ans;
    static boolean[] isEnd;

    static List<Node>[] nodes;

    static void dfs(boolean[] visit, int idx, int totalWeight) {

        ans = Math.max(ans, totalWeight);

        for (int i = 0; i < nodes[idx].size(); i++) {
            Node cur = nodes[idx].get(i);
            int goIdx = cur.endIdx;
            int weight = cur.weight;

            if (visit[goIdx]) continue;

            visit[goIdx] = true;
            dfs(visit, goIdx, totalWeight + weight);

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        isEnd = new boolean[n+1];
        nodes = new List[n+1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            isEnd[s] = false;
            isEnd[e] = true;

            nodes[s].add(new Node(e, w));
            nodes[e].add(new Node(s, w));
        }

        // 가중치 기준 내림차순 정렬
        for (int i = 0; i < nodes.length; i++) {
            Collections.sort(nodes[i]);
        }

        for (int i = 0; i < nodes.length; i++) {
            boolean[] isVisited = new boolean[n + 1];
            isVisited[i] = true;
            dfs(isVisited, i, 0);
        }

        System.out.println(ans);

    }
}