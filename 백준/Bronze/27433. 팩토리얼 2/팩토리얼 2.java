import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static long recur(int n) {
        if (n == 0) return 1;
        else return n * recur(n-1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        System.out.println(recur(N));
//        bw.write(recur(N));
//        bw.flush();
//        bw.close();
    }
}