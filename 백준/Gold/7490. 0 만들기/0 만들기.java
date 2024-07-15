import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int T, N;
    static int[] sign;
    static StringBuilder sb;

    static void recur(int idx) {
        if (idx == N-1) {

            int ans = 1;
            int num = 2;

            for (int i = 0; i < N-1; i++) {
                if (sign[i] == 2) {
                    ans += num++;
                }
                else if (sign[i] == 3) {
                    ans -= num++;
                }
                else { // 공백이 입력되었을 경우
                    if (i == 0) {
                        ans = 12;
                        num++;
                    } else if (sign[i - 1] == 2) { // 전에 더했을 경우
                        ans -= (num - 1);
                        ans += (num - 1) * 10 + num;
                        num++;
                    } else if (sign[i - 1] == 3) { // 전에 뺐을 경우
                        ans += (num - 1);
                        ans -= (num - 1) * 10 + num;
                        num++;
                    } else { // 전에도 공백이였을 경우 => 3<= N <=9 범위에서는 불가능한 경우
                        return;
                    }
                }
            }

            if (ans == 0) {
                // 출력
                for (int i = 0; i < N-1; i++) {
                    sb.append(i + 1);
                    if (sign[i] == 2) sb.append('+');
                    else if (sign[i] == 3) sb.append('-');
                    else if (sign[i] == 1) sb.append(' ');
                }
                sb.append(N);
                sb.append("\n");

            }

            return;
        }

        sign[idx] = 1; // ' '
        recur(idx + 1);
        sign[idx] = 2; // '+'
        recur(idx + 1);
        sign[idx] = 3; // '-'
        recur(idx + 1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        for (int tc=1;tc<=T;tc++) {
            N = Integer.parseInt(br.readLine());

            sign = new int[N];
            recur(0);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}