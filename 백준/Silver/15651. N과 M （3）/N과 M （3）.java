import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
        작성자 : 남보우
        문제 : [S3] N과 M(2) - 15650
        제출 : 2024년 1월 25일
        결과 : 통과
        성능 요약 : 메모리 15776KB, 시간 144ms
        아이디어 : N과M1에서 오름차순 수열만 뽑은 것으로 백트래킹 기법으로 숫자를 넘겨줄 때
                현재 index번호를 넘겨주어 그 index이후의 숫자만 체킹하도록 하였습니다.
*/

public class Main {

    static int N;
    static int M;
    static List<Integer> ans = new ArrayList<>();
    static int[] visited;
    static StringBuilder sb = new StringBuilder();

    static void backTracking(int count) {
        if (count == M) {
            for (int num : ans) {
                sb.append(num+1 + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            ans.add(i);
            backTracking(count + 1);
            ans.remove(ans.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[N];

        int count = 0;
        backTracking(count);
        System.out.println(sb);
    }
}