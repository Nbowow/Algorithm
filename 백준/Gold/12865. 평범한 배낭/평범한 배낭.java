import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Item{
		int weight;
		int value;
		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	static int N, K;
	static Item[] items;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		items = new Item[N];
		
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			items[i] = new Item(w, v);
		}
		
		// 모든 무게에 대해
		for (int i=1; i<K+1; i++) {
			// 모든 물품에 대해
			for (int j=0; j<items.length; j++) {
				if (j==0) {
					if (i >= items[j].weight) dp[i][j] = items[j].value;
					continue;
				}
				
				if (i-items[j].weight < 0) {
					dp[i][j] = dp[i][j-1];
					continue;
				}
					
				dp[i][j] = Math.max(dp[i-items[j].weight][j-1] + items[j].value, dp[i][j-1]);
				
			}
		}
		
		System.out.println(dp[K][N-1]);
		
	}

}