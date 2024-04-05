import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int cur, time;

        public Point(int cur, int time) {
            this.cur = cur;
            this.time = time;
        }
    }
    static int N, K, ansCount, ansTime;
    static Queue<Point> q = new ArrayDeque<>();
    static int[] visitTime;

    static void bfs() {

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int idx = cur.cur;
            int time = cur.time;

            // 동생 찾음
            if (idx == K) {
                if (time == ansTime) ansCount++;
                else {
                    ansTime = time;
                    ansCount = 1;
                }
                continue;
            }

//            if (idx < 0 || idx >= K * 2 || time > visitTime[idx]) continue;

            // X-1
            if (idx - 1 >= 0 && visitTime[idx - 1] >= time + 1) {
                q.offer(new Point(idx - 1, time + 1));
                visitTime[idx - 1] = time + 1;
            }

            // X+1
            if (idx + 1 < K * 2 && visitTime[idx + 1] >= time + 1) {
                q.offer(new Point(idx + 1, time + 1));
                visitTime[idx + 1] = time + 1;
            }


            // X*2
            if (idx * 2 < K * 2 && visitTime[idx * 2] >= time + 1) {
                q.offer(new Point(idx * 2, time + 1));
                visitTime[idx * 2] = time + 1;
            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visitTime = new int[K * 2];
        Arrays.fill(visitTime, Integer.MAX_VALUE);
        // 예외 처리
        if (N == K) { // N == K 일때 =>그자리 가만히 있는 경우 1가지
            System.out.println(0);
            System.out.println(1);
        }
        else if (N > K) { // N > K 일때 => -1만해서 이동해야하는 경우 1가지
            System.out.println(N - K);
            System.out.println(1);
        }
        else {
            ansTime = Integer.MAX_VALUE;
            visitTime[N] = 0;
            q.offer(new Point(N, 0));
            bfs();

            System.out.println(ansTime);
            System.out.println(ansCount);
        }
    }
}