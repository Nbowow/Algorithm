import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int L, Ml, Mk, C;
    static int[] zombie;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        L = Integer.parseInt(br.readLine());
        zombie = new int[L];

        st = new StringTokenizer(br.readLine());
        Ml = Integer.parseInt(st.nextToken());
        Mk = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());

        if (C>=L) {
            System.out.println("YES");
        }
        else {
            int dis = 1;
            boolean isAlive = true;

            for (int i=0; i<L; i++) {

                // 살상거리 이하에 위치한 좀비들
                if (i<Ml) {
                    zombie[i] = Integer.parseInt(br.readLine()) - (dis++ * Mk);
                }
                // 살상거리보다 먼 자리에 위치한 좀비들
                else {
                    zombie[i] = Integer.parseInt(br.readLine()) - (Ml * Mk);
                }

                // 수류탄 투척할때부터 Ml의 거리에 도달하면 poll();
                while(!q.isEmpty() && q.peek() < i) q.poll();

                // 못때린 만큼(q의 사이즈만큼) 더해줘야함
                if (!q.isEmpty()) {
                    zombie[i] += (q.size() * Mk);
                }

                // 수류탄 써야함
                if (zombie[i] > 0) {
                    // 수류탄이 더이상 남아 있지 않을 경우
                    if (C<=0) {
                        isAlive = false;
                        break;
                    }

                    C--;
                    // 앞으로 (Ml-1)의 길이동안은 총으로 한대씩 더 못 때림
                    // i + (Ml-1)의 인덱스동안 유효
                    int duration = i + (Ml-1);
                    q.offer(duration);
                }
            }
            System.out.println(isAlive ? "YES" : "NO");
        }

    }

}