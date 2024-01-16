import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] colored = new int[100][100];
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            for(int j = A; j<A+10; j++) {
                for(int l = B; l<B+10; l++) {
                    colored[j][l] = 1;
                }
            }
        }

        int ans = 0;
        for(int i=0; i<100; i++) {
            for (int j=0; j<100l; j++) {
                if (colored[i][j] == 1) {
                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }
}