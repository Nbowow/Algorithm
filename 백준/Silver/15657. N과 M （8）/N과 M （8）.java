import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
	작성자 : 남보우
	문제 : [S3] N과M7 - 15656번
	제출 : 2024년 1월 24일
	결과 : 통과
	성능 요약 : 메모리 285064KB, 시간 888ms
	아이디어 : 백트래킹을 이용하여 문제를 풀었습니다.
			다만 일반적으로 ans라는 List에 M만큼의 숫자가 담기면 출력해주는 방식으로 진행했었습니다.
			하지만 이 문제는 그렇게 풀면 시간초과가 나고 StringBuilder를 이용하여 출력물을 담은 후
			한번에 출력하는 방식으로 문제를 해결하였습니다.
*/


public class Main {
	
	public static int N;
	public static int M;
	public static int[] nums;
	public static List<Integer> ans = new ArrayList<>();
	public static StringBuilder sb = new StringBuilder();
	
	public static void backTracking(int count) {
		if (count == M) {
			for(int num : ans) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (ans.isEmpty()) {
				ans.add(nums[i]);
				backTracking(count + 1);
				ans.remove(ans.size()-1);
			}
			else if (ans.get(ans.size()-1) <= nums[i]){
				ans.add(nums[i]);
				backTracking(count + 1);
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
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		backTracking(0);
		
		System.out.println(sb);
	}

}