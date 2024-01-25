import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static List<Integer> ans = new ArrayList<>();
    static int[] visited;
    static StringBuilder sb = new StringBuilder();

    static void backTracking(int count, int r) {
        if (count == M) {
            for (int num : ans) {
                sb.append(num+1 + " ");
            }
            sb.append("\n");
        }

        for (int i = r; i < N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                ans.add(i);
                backTracking(count + 1, i);
                visited[i] = 0;
                ans.remove(ans.size() - 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[N];

        int count = 0;
        backTracking(count, 0);
        System.out.println(sb);
    }
}