import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

    /*
        작성자 : 남보우
        문제 : [S2] 222-풀링 - 17829번
        제출 : 2024년 1월 23일
        결과 : 통과
        성능 요약 : 메모리 139844KB, 시간 900ms
        아이디어 : 문제에 제시된 조건대로 배열의 크기를 /2 씩 줄여가며 두번째로 큰 수를 체크했습니다.
                    해당 수를 넣는 배열을 매 반복마다 새로 정의하여 반복하여 답을 구했습니다.


     */
public class Main {

    public static int N;
    public static List<Integer> maxNumber;
    public static int[][] pulling;
    public static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pulling = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pulling[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (N == 2) {
            List<Integer> easyCase = new ArrayList<>();
            for (int i = 0; i < pulling.length; i++) {
                for (int j = 0; j < pulling.length; j++) {
                    easyCase.add(pulling[i][j]);
                }
            }
            Collections.sort(easyCase);
            System.out.println(easyCase.get(easyCase.size()-2));
        } else {
            while(N >= 2) {
                ans = new int[N / 2][N / 2];
                for (int i = 0; i < N; i += 2) {
                    for (int j = 0; j < N; j += 2) {
                        maxNumber = new ArrayList<>();
                        maxNumber.add(pulling[i][j]);
                        maxNumber.add(pulling[i][j + 1]);
                        maxNumber.add(pulling[i + 1][j]);
                        maxNumber.add(pulling[i + 1][j + 1]);
                        Collections.sort(maxNumber);
                        ans[i / 2][j / 2] = maxNumber.get(maxNumber.size() - 2);
                    }
                }
                pulling = Arrays.copyOf(ans, ans.length);
                N /= 2;
            }

            System.out.println(ans[0][0]);
        }

    }
}