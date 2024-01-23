import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static List<Integer> maxNumber;
    public static int[][] pulling;
    public static int[][] temp;
    public static int[][] ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pulling = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pulling[i][j] =Integer.parseInt(st.nextToken());
            }
        }

        temp = new int[N/2][N/2];
        for (int i = 0; i < N; i+=2) {
            for (int j = 0; j < N; j += 2) {
                maxNumber = new ArrayList<>();
                maxNumber.add(pulling[i][j]);
                maxNumber.add(pulling[i][j + 1]);
                maxNumber.add(pulling[i + 1][j]);
                maxNumber.add(pulling[i + 1][j + 1]);
                Collections.sort(maxNumber);
                temp[i / 2][j / 2] = maxNumber.get(maxNumber.size() - 2);
            }
        }
        if (N == 2) {
            System.out.println(temp[0][0]);
        } else {
            while(N > 2) {
                N /= 2;
                ans = new int[N][N];

                for (int i = 0; i < N; i+=2) {
                    for (int j = 0; j < N; j += 2) {
                        maxNumber = new ArrayList<>();
                        maxNumber.add(temp[i][j]);
                        maxNumber.add(temp[i][j + 1]);
                        maxNumber.add(temp[i + 1][j]);
                        maxNumber.add(temp[i + 1][j + 1]);
                        Collections.sort(maxNumber);
                        ans[i / 2][j / 2] = maxNumber.get(maxNumber.size()-2);
                    }
                }
                temp = Arrays.copyOf(ans, ans.length);

//            for (int[] num : temp) {
//                System.out.println(Arrays.toString(num));
//            }
            }
            System.out.println(ans[0][0]);
        }

    }
}