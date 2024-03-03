import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int w1 = 0;
        int w2 = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());

            int h_2 = h / 2; // 2로 나눈 몫
            int h_1 = h % 2; // 2로 나눈 나머지

            w2 += h_2; // 2의 갯수 카운트
            w1 += h_1; // 1의 갯수 카운트
        }

        // 2 * (2의 갯수) + 1 * (1의 갯수) = 3 * k 를 만족하는 정수 k가 존재해야 하고,
        // k는 w2(2의 갯수) 보다 적거나 같아야 한다.
        // 이유 : k는 (1과 2를 동시에 뿌리는 횟수) 이므로 적어도 (2를 뿌리는 횟수)보다 적거나 같아야 함
        int res = (2 * w2 + w1) / 3;
        int rest = (2 * w2 + w1) % 3;

        if (rest == 0 && res <= w2) {
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
}