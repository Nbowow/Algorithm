import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
			ans.add(nums[i]);
			backTracking(count + 1);
			ans.remove(ans.size()-1);
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