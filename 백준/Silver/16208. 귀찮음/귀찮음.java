import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Long.parseLong(st.nextToken()));
        }

        long ans = 0;
        while (nums.size() > 1) {
            Collections.sort(nums);

            int size = nums.size();
            List<Long> temp = new ArrayList<>();
            for (int i = 0; i < size / 2; i++) {
                ans += nums.get(i) * nums.get(size - i - 1);
                temp.add(nums.get(i)+nums.get(size - i - 1));
//                System.out.println(nums.get(i) * nums.get(size - i - 1));
            }

            // 홀수
            if (size % 2 == 1) {
                temp.add(nums.get(size / 2));
            }

            nums = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                nums.add(temp.get(i));
            }

        }

        System.out.println(ans);

    }

}