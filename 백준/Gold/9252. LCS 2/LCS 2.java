import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    static char[] str1;
    static char[] str2;
    static List<Character> ans = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        int[][] dp = new int[str1.length + 1][str2.length + 1];
        for (int i=1; i<=str1.length; i++) {
            for (int j=1; j<=str2.length; j++) {
                if (str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int i = str1.length;
        int j = str2.length;
        Stack<Character> stack = new Stack<>();
        while (i > 0 && j > 0) {
            // 위 검사
            if (dp[i - 1][j] == dp[i][j]) {
                i-=1;
                continue;
            }

            // 왼쪽 검사
            if (dp[i][j - 1] == dp[i][j]) {
                j-=1;
                continue;
            }

            stack.push(str1[i-1]);
            i-=1;
            j-=1;
        }

        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int s=0; s<ans.size(); s++) sb.append(ans.get(s));

        System.out.println(ans.size());
        System.out.println(sb);

    }

}