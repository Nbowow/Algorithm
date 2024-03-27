import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Item {
		int bite;
		int cost;
		public Item(int bite, int cost) {
			this.bite = bite;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		StringTokenizer bites = new StringTokenizer(br.readLine());
		StringTokenizer costs = new StringTokenizer(br.readLine());
		
		int[][] dp = new int[N][10001];
		Item[] items = new Item[N];
		
		for (int i=0; i<N; i++) {
			int bite = Integer.parseInt(bites.nextToken());
			int cost = Integer.parseInt(costs.nextToken());
			
			items[i] = new Item(bite, cost);
		}
		
		// 첫번째 앱 초기값 설정
		dp[0][items[0].cost] = items[0].bite;
		
		int ans = 0;
		// 가능한 모든 cost 확인
		total:
		for (int j=0; j<10001; j++) {
			// 모든 앱 확인
			for (int i=0; i<N; i++) {
				// 첫번째 앱
				if (i == 0) {
					if (j != 0) dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
				}
				
				else {
					if (j-items[i].cost < 0) dp[i][j] = dp[i-1][j];
					
					else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-items[i].cost] + items[i].bite);
				}
				
				if (dp[i][j] >= M) {
					ans = j;
					break total;
				}
			}
		}
		
		System.out.println(ans);

	}

}