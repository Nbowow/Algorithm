import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 1;
        int i = 1;
        int j = 1;
        total:
        while (true) {
            if (count == N) break;
            j++;
            count++;
            while (j > 1) {
                if (count == N) break total;
                j--;
                i++;
                count++;
            }
            if (count == N) break;
            i++;
            count++;
            while (i > 1) {
                if (count == N) break total;
                i--;
                j++;
                count++;
            }

        }
        System.out.println(i+ "/" + j);
    }
}