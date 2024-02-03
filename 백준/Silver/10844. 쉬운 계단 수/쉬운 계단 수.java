import java.io.BufferedReader;
import java.io.InputStreamReader;

// int형 범위 : 21조
// long형 범위 : 920경
public class Main {

    static long ans;
    static final int DIVIDE_NUM = 1000000000;
    static long[][] numStairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        numStairs = new long[N][10];

        // 1 자릿수
        for (int i = 1; i < 10; i++) {
            numStairs[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            // i 자릿수 중 0 으로 끝나는 수
            numStairs[i][0] = numStairs[i - 1][1] % DIVIDE_NUM;
            // i 자릿수 중 1~8 으로 끝나는 수
            for (int j = 1; j <= 8; j++) {
                numStairs[i][j] = (numStairs[i - 1][j - 1] + numStairs[i - 1][j + 1]) % DIVIDE_NUM;
            }
            // i 자릿수 중 9 로 끝나는 수
            numStairs[i][9] = numStairs[i - 1][8] % DIVIDE_NUM;

        }


        for (int i = 0; i < 10; i++) {
            ans += numStairs[N-1][i] % DIVIDE_NUM;
        }
        System.out.println(ans % DIVIDE_NUM);
    }
}