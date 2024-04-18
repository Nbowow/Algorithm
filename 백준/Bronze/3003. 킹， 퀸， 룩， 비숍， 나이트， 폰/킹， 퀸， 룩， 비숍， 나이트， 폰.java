import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] data = new int[6];
        data[0] = 1;
        data[1] = 1;
        data[2] = 2;
        data[3] = 2;
        data[4] = 2;
        data[5] = 8;

        for (int i=0; i<6; i++) {
            int num = Integer.parseInt(st.nextToken());
            data[i] -= num;
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(data[i] + " ");
        }


    }
}