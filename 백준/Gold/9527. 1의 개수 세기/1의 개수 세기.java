import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long A, B;
    static long[] dp;

    static long cal(long x) {

        // 0번 비트의 홀 짝여부를 왜 판단해 줘야하지?
        // ->만약 x가 23 (10111) 이면 1<<1까지만 검사를 하므로 맨 마지막 1은 검사 해줘야함
        long ans = x & 1;

        for (int i = 60; i > 0; i--) {
            // 최상위 비트 확인
            if ((x & (1L<<i)) > 0) {
                ans += dp[i-1] + (x - (1L<<i) + 1);
                x -= (1L<<i);
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        // 2의 60승 까지 계산
        dp = new long[60];

        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 2*dp[i-1] + (1L<<i);
        }

        System.out.println(cal(B) - cal(A-1));
    }
}