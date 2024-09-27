import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] HP = br.readLine().toCharArray();
        boolean[] isEat = new boolean[N];

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (HP[i] == 'P') {
                for (int j = i-K; j <= i+K; j++) {
                    if (!isIn(j, N)) continue;
                    if (HP[j] == 'H' && !isEat[j]) {
                        ans += 1;
                        isEat[j] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static boolean isIn(int x, int maxN) {
        return x >= 0 && x < maxN;
    }
}