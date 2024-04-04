import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, B;
	static int[] heights;
	static int ans;
	
	static void backtracking(int idx, int totalHeight) {
		// backtracking
		if (totalHeight - B > ans) return;
		
		if (totalHeight >= B) {
			ans = Math.min(ans, totalHeight - B);
			return;
		}
		
		for (int i=idx; i<N; i++) {
			backtracking(i + 1, totalHeight + heights[i]);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			heights = new int[N];
			int total = 0;
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
				total += heights[i];
			}
			
			ans = Integer.MAX_VALUE;
			backtracking(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

}