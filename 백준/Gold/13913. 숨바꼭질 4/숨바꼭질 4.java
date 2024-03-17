import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
	@author : 남보우
	문제 : [G5] 숨바꼭질3 - 13549번
	제출 : 2024년 3월 15일
	결과 : 통과
	성능 요약 : 메모리 20736KB, 시간 140ms
	아이디어 : bfs로 풀었습니다.
	    1. (현재 값, N에서 시작해서 현재 값까지 도달하는데 걸린 시간) 을 노드 클래스에 넣어주어 값을 핸들링했습니다.
	    2. visitTime 배열을 만들어 i 번째 값까지 도달하는데 걸린시간을 최소로 갱신해주며 최단경로를 찾았습니다.

	    * bfs는 bfs인데 처음 풀어보는 형식의 bfs였습니다.
*/

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
    static int[] parents;

    static void bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(N, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 동생 찾음 (값 도착)
            if (cur.val == K) {
                ans = Math.min(ans, cur.time);
                continue;
            }

            // 빼기 가능
            // 현재 값이 K/2 보다 작을 경우 더 작아질 필요 없음!
            // 현재 값에서 곱셈 또는 덧셈을 하는것이 무조건 뺄셈을 하는 것 보다 최적의 답이기 때문
            if (cur.val > 0) {
                // 현재 값에 도착 시간이 이전에 왔던 시간보다 빠를 경우
                if (visitTime[cur.val - 1] > cur.time + 1) {
                    q.offer(new Node(cur.val - 1, cur.time + 1));
                    // 어디서부터 왔는지 마킹
                    parents[cur.val - 1] = cur.val;
                    visitTime[cur.val - 1] = cur.time + 1;
                }

            }

            // 더하기 및 곱 * 2 가능
            if (cur.val < K) {
                if (visitTime[cur.val + 1] > cur.time + 1) {
                    q.offer(new Node(cur.val + 1, cur.time + 1));
                    parents[cur.val + 1] = cur.val;
                    visitTime[cur.val + 1] = cur.time + 1;
                }

                if (visitTime[cur.val * 2] > cur.time + 1) {
                    q.offer(new Node(cur.val * 2, cur.time + 1));
                    parents[cur.val * 2] = cur.val;
                    visitTime[cur.val * 2] = cur.time + 1;
                }

            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N < K) {
            // 가장 큰수의 두배에 해당하는 값의 크기를 배열로 지정해줌
            // * (+1) 안해도 되는 이유 : K값에 도착했다면 더이상 그 값의 두배를 검사하지 않기 때문에 접근할 일이 없다.
            visitTime = new int[Math.max(N, K) * 2];
            parents = new int[Math.max(N, K) * 2];
            Arrays.fill(visitTime, Integer.MAX_VALUE);

            bfs();

            Stack<Integer> stack = new Stack<>();
            stack.push(K);

            int index = K;

            while (index != N) {
                stack.push(parents[index]);
                index = parents[index];
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop() + " ");
            }

            System.out.println(ans);
            System.out.println(sb);
        }
        else {
            // 낮은 수로 가야 하는 경우 -> 단순히 내림차순 수 출력하면 됨
            for (int i = N; i >= K; i--) {
                sb.append(i + " ");
            }
            System.out.println(N-K);
            System.out.println(sb);
        }


    }
}