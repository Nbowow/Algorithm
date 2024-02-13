import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Queue<String>> birds = new ArrayList<>();
    static Queue<String> temp = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            birds.add(new ArrayDeque<>());
        }

        // 앵무새 단어 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                birds.get(i).offer(st.nextToken());
            }
        }

        // 받아 적은 문장
        st = new StringTokenizer(br.readLine());
        boolean isHandled = false;
        while (st.hasMoreTokens()) {
            isHandled = false;
            String str = st.nextToken();
            for (int i = 0; i < N; i++) {
                if (!birds.get(i).isEmpty() && birds.get(i).peek().equals(str)) {
                    isHandled = true;
                    birds.get(i).poll();
                }
            }

            if (!isHandled) break;
        }

        boolean isImpossible = true;
        for (int i = 0; i < N; i++) {
            if (!birds.get(i).isEmpty()) {
                isImpossible = false;
            }
        }

        System.out.println(isImpossible&&isHandled ? "Possible" : "Impossible");
    }
}