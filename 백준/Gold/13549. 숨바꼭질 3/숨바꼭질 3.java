import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int val;
        int time;

        public Node(int val, int time) {
            this.val = val; // 현재 위치 값
            this.time = time; // 현재 위치까지 오는데 걸린 시간
        }
    }
    static int N, K;
    // visitTime[10] = 3; -> 10의 자리에 오는데 3의 시간이 걸림
    static int[] visitTime;
    static int ans = Integer.MAX_VALUE;

    static void bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(N, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

//            System.out.println(cur.val + " " + cur.time);
            // 동생 찾음 (값 도착)
            if (cur.val == K) {
                ans = Math.min(ans, cur.time);
                continue;
            }

            // 빼기 가능 (1일때도 가능!!)
            if (cur.val > 0) {
                // 현재 값에 도착 시간이 이전에 왔던 시간보다 빠를 경우
                if (visitTime[cur.val - 1] > cur.time + 1) {
                    q.offer(new Node(cur.val - 1, cur.time + 1));
                    visitTime[cur.val - 1] = cur.time + 1;
                }


            }

            // 더하기 및 곱 * 2 가능
            if (cur.val < K) {
                if (visitTime[cur.val + 1] > cur.time + 1) {

                    q.offer(new Node(cur.val + 1, cur.time + 1));
                    visitTime[cur.val + 1] = cur.time + 1;
                }

                if (visitTime[cur.val * 2] > cur.time) {
                    q.offer(new Node(cur.val * 2, cur.time));
                    visitTime[cur.val * 2] = cur.time;
                }

            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visitTime = new int[Math.max(N, K) * 2 + 1];
        Arrays.fill(visitTime, Integer.MAX_VALUE);

        bfs();

        System.out.println(ans);
    }
}