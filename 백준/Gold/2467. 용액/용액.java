import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int i = 0;
        int j = N-1;
        int fIdx = 0;
        int eIdx = N-1;
        while (i < j) {

            int cur = nums[i] + nums[j];

            if (ans > Math.abs(cur)) {
                fIdx = i;
                eIdx = j;
                ans = Math.abs(cur);
            }

            // 합이 0일 경우 -> 답
            if (cur == 0) break;

            // 합이 양수일 경우
            if (cur > 0) j--;
            // 합이 음수일 경우
            else i++;
        }

        System.out.println(nums[fIdx] + " " + nums[eIdx]);
    }
}