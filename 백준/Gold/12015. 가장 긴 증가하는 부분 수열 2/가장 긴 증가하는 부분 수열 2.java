import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] LIS;
    static int binarySearch(int start, int end, int target) {

        while (start < end) {
            int mid = (start + end) / 2;
            if (LIS[mid] < target) start = mid + 1;
            else end = mid;
        }
        return end;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[N];

        for (int i=0; i<N; i++) input[i] = Integer.parseInt(st.nextToken());

        LIS = new int[N];
        LIS[0] = input[0];
        int end = 0;
        for (int i = 1; i < N; i++) {
            if (input[i] > LIS[end]) LIS[++end] = input[i];
            else {
                int idx = binarySearch(0, end, input[i]);
                LIS[idx] = input[i];
            }
        }

        System.out.println(end + 1);

    }
}