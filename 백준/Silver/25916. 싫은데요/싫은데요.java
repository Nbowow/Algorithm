import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] holes = new long[N];
        st = new StringTokenizer(br.readLine());
        Queue<Long> q = new ArrayDeque<>();
        long temp = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            holes[i] = Long.parseLong(st.nextToken());

            q.offer(holes[i]);
            temp += holes[i];
            while (temp > M) {
                temp -= q.poll();
            }
            ans = Math.max(ans, temp);
        }

        System.out.println(ans);
    }
}
