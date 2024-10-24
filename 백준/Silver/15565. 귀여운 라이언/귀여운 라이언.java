import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sliding = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sliding[i] = Integer.parseInt(st.nextToken());
        }

        int sIdx = 0;
        int eIdx = 0;
        int count = 0;
        int minCount = Integer.MAX_VALUE;
        if (sliding[0] == 1) {
            count = 1;
            if (K == 1) minCount = 1;
        }
        while (sIdx <= eIdx) {
            if (count < K) {
                if (eIdx == N-1) break;

                eIdx++;
                if (sliding[eIdx] == 1) count++;
            }
            else {
                if (sIdx == N-1) break;

                minCount = Math.min(minCount, eIdx - sIdx + 1);
                sIdx++;
                if (sliding[sIdx - 1] == 1) count--;

            }
        }

        System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
    }
}