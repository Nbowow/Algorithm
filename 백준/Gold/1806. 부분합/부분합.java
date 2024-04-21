import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] prefixSum = new int[N];
        st = new StringTokenizer(br.readLine());
        boolean isFirst = true;
        int sumNum = 0;
        int minLen = 0;
        for (int i=0; i<N; i++) {
            sumNum += Integer.parseInt(st.nextToken());
            prefixSum[i] = sumNum;

            if (prefixSum[i] < S) continue;

            // 처음으로 S이상인 부분합에 대해
            if (isFirst) {
                isFirst = false;
                minLen = i + 1;
            }

            for (int j = i - minLen + 1; j <= i; j++) {
                if (prefixSum[i] - prefixSum[j] < S) {
                    minLen = i - (j - 1);
                    break;
                }
            }
        }

        System.out.println(minLen);

    }
}