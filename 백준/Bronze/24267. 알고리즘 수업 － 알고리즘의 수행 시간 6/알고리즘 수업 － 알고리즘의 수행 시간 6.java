import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());

        // NC3
        if (N == 1 || N == 2) {
            System.out.println(0);
            System.out.println(3);
        }
        else {
            System.out.println(N * (N-1) * (N-2) / 6);
            System.out.println(3);
        }

    }
}