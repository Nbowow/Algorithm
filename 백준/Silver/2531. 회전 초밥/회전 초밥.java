import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, d, k, c;
    static int ans;
    static int[] sushi;
    static int[] sushiCount;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sushi = new int[N];

        d = Integer.parseInt(st.nextToken());
        sushiCount = new int[d+1];

        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        // 회전 초밥 정보 입력
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            q.offer(sushi[i]);
        }

        // 초밥의 가짓수
        int cnt = 0;

        // k개의 초밥 선택
        for (int i = 0; i < k; i++) {
            sushiCount[sushi[i]]++;
        }

        for (int i = 0; i < d+1; i++) {
            if (sushiCount[i] > 0) cnt++;
        }
        // 쿠폰 번호 스시 먹지 않았을 경우
        if (sushiCount[c] == 0) ans = Math.max(ans, cnt + 1);
        // 쿠폰 번호 스시 먹었을 경우
        else ans = Math.max(ans, cnt);

//        System.out.println(Arrays.toString(sushiCount));
//        System.out.println("cnt = " + cnt);

        // k개의 초밥 다시 선택 (슬라이딩 윈도우)
        for (int startIndex = 1; startIndex < N; startIndex++) {
            // startIndex부터 k개의 초밥 선택

            // 회전시켜서 돌아오는 초밥 먹어야함
            if (startIndex + k - 1 >= N) {
                // 새로 들어올 스시
                int rotateSushi = q.poll();

                // 앞 스시 뺌
                sushiCount[sushi[startIndex-1]]--;
                if (sushiCount[sushi[startIndex-1]] == 0) cnt--;

                // 새로 들어온 스시 카운팅
                if (sushiCount[rotateSushi] == 0) cnt++;
                sushiCount[rotateSushi]++;

            }

            else {
                // 앞 스시 뺌
                sushiCount[sushi[startIndex - 1]]--;
                if (sushiCount[sushi[startIndex - 1]] == 0) cnt--;

                // 새로 들어온 스시 카운팅
                if (sushiCount[sushi[startIndex + k - 1]] == 0) cnt++;
                sushiCount[sushi[startIndex + k - 1]]++;
            }

            // 쿠폰 번호 스시 먹지 않았을 경우
            if (sushiCount[c] == 0) ans = Math.max(ans, cnt + 1);
            // 쿠폰 번호 스시 먹었을 경우
            else ans = Math.max(ans, cnt);

//            System.out.println(Arrays.toString(sushiCount));
//            System.out.println("cnt = " + cnt + " ans : " + ans);
//            System.out.println("ans = " + ans);
        }

        System.out.println(ans);
    }
}