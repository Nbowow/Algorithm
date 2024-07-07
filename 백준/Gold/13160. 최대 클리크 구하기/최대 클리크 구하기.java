import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx, num;
        int isStart;

        public Node(int idx, int isStart, int num) {
            this.idx = idx;
            this.isStart = isStart;
            this.num = num;
        }

        @Override
        public int compareTo(Node n) {
            if (this.num == n.num) {
                return Integer.compare(this.isStart, n.isStart);
            }
            return Integer.compare(this.num, n.num);
        }
    }

    static class Ans {
        int idx, num;

        public Ans(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static int N;
    static List<Node> nodes;
    static List<Node> ans;
    static int ansCount;
    static int maxCount;
    static List<int[]> intervals = new ArrayList<>();


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList<>();
        ans = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            nodes.add(new Node(i+1, 0, s));
            nodes.add(new Node(i+1, 1, e));
            intervals.add(new int[]{s, e});
        }

        Collections.sort(nodes);

        int position = 0;

        for (int i = 0; i < nodes.size(); i++) {
            Node cur = nodes.get(i);

            if (cur.isStart == 0) {
                ansCount += 1;
            } else {
                ansCount -= 1;
            }

            if (ansCount > maxCount) {
                maxCount = ansCount;
                position = cur.num;
            }

        }

        System.out.println(maxCount);

        // 가장 많은 정점이 포함된 지점이 각 정점이 시작부분과 끝부분 사이에 있는지 판단
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i)[0] <= position && intervals.get(i)[1] >= position) {
                System.out.print((i + 1) + " ");
            }
        }
    }
}