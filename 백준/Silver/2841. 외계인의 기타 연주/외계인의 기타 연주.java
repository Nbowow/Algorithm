import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {

    static int N, P, move;
    static List<Stack<Integer>> guitar = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 7; i++) {
            // 1~6 기타 줄
            guitar.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int pret = Integer.parseInt(st.nextToken());

            // 비어 있으면 push
            if (guitar.get(line).isEmpty()) {
                guitar.get(line).push(pret);
                move += 1;
            }

            // 비어 있지 않고 입력 받은 수보다 작은수가 들어있으면 push
            else if (guitar.get(line).peek() < pret) {
                guitar.get(line).push(pret);
                move += 1;
            }


            // 비어 있지 않고 입력 받은 수보다 큰수가 들어있으면 pop
            else {
                // 이미 들어있는 수 이거나, 들어 있지 않은 수
                boolean alreadyHave = false;
                while(!guitar.get(line).isEmpty() && guitar.get(line).peek() >= pret) {
                    // 이미 들어있는 수 일 경우
                    if (guitar.get(line).peek() == pret) {
                        alreadyHave = true;
                        break;
                    }

                    // 들어있지 않은 수 인 경우
                    guitar.get(line).pop();
                    move += 1;
                }
                if (!alreadyHave) {
                    guitar.get(line).push(pret);
                    move += 1;
                }

            }
        }
        System.out.println(move);

    }
}