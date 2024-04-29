import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N][N];
		
		// S, E가 같을 때 1
		for (int i=0; i<N; i++) {
			dp[i][i] = 1;
		}
		
		// 모두 같은 수 일때
		for (int i=0; i<N-1; i++) {
			int j = i;
			while (j<N && nums[i] == nums[j]) {
				dp[i][j] = 1;
				j++;
			}
		}
		
		// dp
		for (int i=0; i<N; i++) {
			int S = i-1;
			int E = i+1;
			
			while (S>=0 && E<N) {
				if (nums[S] == nums[E]) {
					dp[S--][E++] = 1;
				}
				else break;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()) - 1;
			int E = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(dp[S][E] + "\n");
		}
		
		System.out.println(sb);
		
	}

}