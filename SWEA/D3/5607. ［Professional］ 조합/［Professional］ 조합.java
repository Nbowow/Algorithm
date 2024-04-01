import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final int BN = 1234567891;
    static long rfac, n_rfac;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long nfac = 1;
            for (int i = 1; i <= N; i++) {
                nfac = (nfac * i) % BN;
                if (i == R) rfac = nfac;
                if (i == (N-R)) n_rfac = nfac;
            }

            long mulnum = fermat((rfac * n_rfac) % BN, BN - 2);

            System.out.println("#" + tc + " " + (nfac * mulnum) % BN);
        }
    }

    static long fermat(long num, long mod) {
        if (mod == 0) return 1;
        else if (mod == 1) return num;

        if ((mod % 2) == 0) {
            long temp = fermat(num, mod / 2);
            return (temp * temp) % BN;
        }

        else {
            long temp = fermat(num, mod - 1);
            return (temp * num) % BN;
        }

    }
}