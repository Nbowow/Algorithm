import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int value, clipboard, time;
        public Point(int value, int clipboard, int time) {
            this.value = value;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
    static int S, ans;
    static Queue<Point> q = new ArrayDeque<>();
    static int[][] visitTime;

    static void bfs() {
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int value = cur.value;
            int clipboard = cur.clipboard;
            int time = cur.time;

            // 이모티콘 S개 완성
            if (value == S) {
                ans = time;
                return;
            }

            if (time > visitTime[value][clipboard]) continue;

            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
            if (visitTime[value][value] > time + 1) {
                q.offer(new Point(value, value, time + 1));
                visitTime[value][value] = time + 1;
            }

            // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
            if (value + clipboard < S * 2 && visitTime[value + clipboard][clipboard] > time + 1) {
                q.offer(new Point(value + clipboard, clipboard, time + 1));
                visitTime[value + clipboard][clipboard] = time + 1;
            }

            // 3. 화면에 있는 이모티콘 중 하나 삭제
            if (value > 1 && visitTime[value - 1][clipboard] > time + 1) {
                q.offer(new Point(value - 1, clipboard, time + 1));
                visitTime[value - 1][clipboard] = time + 1;
            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        // visitTime[value][clipboard]
        visitTime = new int[S * 2][S * 2];
        for (int i = 0; i < visitTime.length; i++) {
            Arrays.fill(visitTime[i], Integer.MAX_VALUE);
        }

        visitTime[1][0] = 0;
        q.offer(new Point(1, 0, 0));
        bfs();

        System.out.println(ans);
    }
}