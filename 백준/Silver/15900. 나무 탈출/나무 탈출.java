import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, ans;
    static StringTokenizer st;
    static int[] isVisited;

    static void checkDepth(int depth, int index) {
        // 처음 방문 했을 경우
        if (isVisited[index] == 0) {
            isVisited[index] = 1;

            for (int i = 0; i < trees.get(index).size(); i++) {
                checkDepth(depth+1, trees.get(index).get(i));
            }
            // 리프 노드
            if (index != 1 && trees.get(index).size() == 1) {
                ans += depth;
            }
        }
    }
    static List<List<Integer>> trees = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isVisited = new int[N+1];

        for (int i = 0; i <= N; i++) {
            trees.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            trees.get(a).add(b);
            trees.get(b).add(a);
        }

        checkDepth(0, 1);

        if (ans % 2 == 0) {
            System.out.println("No");
        } else System.out.println("Yes");
    }
}