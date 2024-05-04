import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] value;
    static int[] son;
    static int[] ans;
    static List<List<Integer>> list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        value = new int[N];
        son = new int[N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) break;

                // i 번째 요소 자식 갯수 + 1
                son[i]++;

                // i번째 요소의 자식 정보 담기
                list.get(next-1).add(i);
            }
        }

        ans = new int[N];

        int count = 0;
        boolean[] isVisited = new boolean[N];
        while (count < N) {

            for (int i = 0; i < N; i++) {
                // 자식이 없는 노드 찾기
                if (son[i] == 0 && !isVisited[i]) {
                    count++;
                    isVisited[i] = true;

                    for (int j = 0; j < list.get(i).size(); j++) {
                        int idx = list.get(i).get(j);

                        ans[idx] = Math.max(ans[idx], ans[i] + value[i]);
                        son[idx]--; // 자식 갯수 -1
                    }

                }

            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(ans[i] + value[i]);
        }

    }
}