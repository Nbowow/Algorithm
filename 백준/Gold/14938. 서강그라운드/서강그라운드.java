import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] items = new int[n];
		for (int i=0; i<n; i++) items[i] = Integer.parseInt(st.nextToken());
		
		// 초기 배열값 초기화
		int[][] adj = new int[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(adj[i], 10001);
			adj[i][i] = 0;
		}
		
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			
			adj[a][b] = l;
			adj[b][a] = l;
		}
		
		// 플로이드-워샬
		for (int k=0; k<n; k++) { // 경로
			for (int i=0; i<n; i++) { // 출발지
				for (int j=0; j<n; j++) { // 도착지
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		// 최대 아이템 갯수 찾기
		int ans = 0;
		for (int i=0; i<n; i++) {
			int count = 0;
			for (int j=0; j<n; j++) {
				// 수색범위를 넘어서지 않을 경우 count
				if (adj[i][j] <= m) count += items[j];
			}
			ans = Math.max(ans, count);
		}
		
		System.out.println(ans);
		
	}

}