import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a1 = Integer.parseInt(st.nextToken());
        int a2 = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        // a1*n + a2 <= c * n
        // a1 + a2/n <= c

        // a2가 음수일 때도 고려
        if (a1 + (double)a2/n <= c && a1 <= c) System.out.println(1);
        else System.out.println(0);

    }
}