import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 3차원배열 선언 (0 : 가로로 들어올 때, 1 : 대각선으로 들어올 때, 2 : 세로로 들어올 때)
		int[][][] dp = new int[N][N][3];
		
		for (int j=1; j<N; j++) {
			if (map[0][j] == 1) break;
			// 0행은 가로로 밖에 못옴 (단, 1열부터)
			dp[0][j][0] = 1;
		}
		
		// 1행부터
		for (int i=1; i<N; i++) {
			for (int j=2; j<N; j++) {
				// 벽이면 파이프 놓기 불가능
				if (map[i][j] == 1) continue;
				
				// (i, j)에 가로로 들어오는 파이프 -> (i, j-1)에 들어오는 (가로, 대각선) 파이프의 합
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				
				// (i, j)에 대각선으로 들어오는 파이프 -> (i-1, j-1)에 들어오는 파이프의 합
				// 단 (i-1, j), (i, j-1)에 벽 없어야 함
				if (map[i-1][j] != 1 && map[i][j-1] != 1) {
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];	
				}
				
				// (i, j)에 세로로 들어오는 파이프 -> (i-1, j)에 들어오는 (대각선, 세로)의 합
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
			}
		}
		
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}

}