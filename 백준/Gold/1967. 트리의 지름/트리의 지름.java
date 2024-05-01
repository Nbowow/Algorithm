import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    @author : 남보우
    문제 : [G4] 트리의 지름 - 1967번
    제출 : 2024년 5월 01일
    결과 : 통과
    성능 요약 : 메모리 117760KB, 시간 3596ms
    아이디어 : brute force + dfs
        모든 노드의 양 끝단에서 끝단으로의 경로를 확인하여
        가중치가 가장 크게 나오는 경로를 구하였습니다.
*/

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
            if (isEnd[i]) dfs(isVisited, i, 0);
        }

        System.out.println(ans);

    }
}