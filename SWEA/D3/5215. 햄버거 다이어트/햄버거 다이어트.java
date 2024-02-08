import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, L, ans;
	static int[][] TK;
	static boolean[] isVisited;
	static List<Integer> taste = new ArrayList<>();
	
	static void dfs(int limitKal, int idx) {
		if (limitKal <= L) {
			int temp = 0;
			for (int i=0; i<taste.size(); i++) {
				temp += taste.get(i);
			}
			ans = Math.max(ans, temp);
		}
		
		for (int i=idx; i<N; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				// 맛 넣기
				taste.add(TK[i][0]);
				dfs(limitKal + TK[i][1], i+1);
				isVisited[i] = false;
				taste.remove(taste.size()-1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			TK = new int[N][2];
			isVisited = new boolean[N];
			L = Integer.parseInt(st.nextToken());
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				TK[i][0] = Integer.parseInt(st.nextToken());
				TK[i][1] = Integer.parseInt(st.nextToken());
			}			
			
			dfs(0, 0);
			
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}