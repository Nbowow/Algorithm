import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;

public class Main {
	
	private static int N;
	private static int M;
	private static int[] nums;
	private static List<Integer> ans = new ArrayList<>();
	
	public static void backTracking() {
		if (ans.size() == M) {
			for(int n : ans) {
				System.out.print(n + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (ans.size() == 0) {
				ans.add(nums[i]);
				backTracking();
				ans.remove(ans.size()-1);
			}
			
			else if(ans.get(ans.size()-1) < nums[i]) {
				ans.add(nums[i]);
				backTracking();
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
		backTracking();
		
//		for(int n : nums) {
//			System.out.println(n);
//		}
	}

}