import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
	작성자 : 남보우
	문제 : [S3] N과M8 - 15657번
	제출 : 2024년 1월 24일
	결과 : 통과
	성능 요약 : 메모리 20588KB, 시간 236ms
	아이디어 : 백트래킹을 이용하여 문제를 풀었습니다.
			다만 일반적으로 ans라는 List에 M만큼의 숫자가 담기면 출력해주는 방식으로 진행했었습니다.
			비내림차순 조건을 구현하였습니다.
*/


public class Main {

    public static int N;
    public static int M;
    public static int[] nums;
    public static int[] visited;
    public static List<Integer> ans = new ArrayList<>();
    public static LinkedHashSet<String> set = new LinkedHashSet<>();
    public static StringBuilder sb;

    public static void backTracking(int count) {
        if (count == M) {
            sb =  new StringBuilder();
            for (int num : ans) {
                sb.append(num + " ");
            }
            set.add(sb.toString());
            return;
        }

        for (int i=0; i<N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                ans.add(nums[i]);
                backTracking(count + 1);
                visited[i] = 0;
                ans.remove(ans.size()-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        visited = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        backTracking(0);

        String [] arr = set.toArray(new String[0]);
        for (String s : arr) {
            System.out.println(s);
        }
    }

}