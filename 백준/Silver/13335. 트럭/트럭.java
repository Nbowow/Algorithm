import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, w, L;
    static int weights, t;
    // 0행 = 트럭의 무게, 1행 = 트럭이 들어온 시간
    static int[][] trucks;
    // 다리에 올라와있는 차 갯수
    static Queue<Integer> q = new ArrayDeque<>();

    static void calTime() {
        t = 1;
        for (int i = 0; i < n; i++) {
            if (weights + trucks[i][0] <= L) {
                q.offer(i);
                weights += trucks[i][0];
                trucks[i][1] = t;
            } else {
                while (weights + trucks[i][0] > L) {
                    weights -= trucks[q.peek()][0];
                    t = trucks[q.poll()][1] + w;
                }

                // 이제 다리에 올라갈 수 있음
                weights += trucks[i][0];
                q.offer(i);
                trucks[i][1] = t;
            }
//            System.out.println("num = " + trucks[i][0] + " weights : " + weights + " t : " + t);
            t++;
            while (t > trucks[q.peek()][1] + w) {
                weights -= trucks[q.poll()][0];
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        trucks = new int[n][2];
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i][0] = Integer.parseInt(st.nextToken());
        }

        calTime();
        System.out.println(t+w-1);
    }
}