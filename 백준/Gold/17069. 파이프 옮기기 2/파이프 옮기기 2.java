import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	작성자 : 남보우
	문제 : [G5] 파이프 옮기기1 - 17070번
	제출 : 2024년 2월 28일
	결과 : 통과
	성능 요약 : 메모리 11796KB, 시간 80ms
	아이디어 : dp를 이용하여 풀었습니다.
		1. (i, j)에 놓을 수 있는 파이프의 수 = (i, j)에 각 가로, 대각선, 세로로 놓을 수 있는 파이프의 수의 합
		2-1. (i, j)에 가로로 놓을 수 있는 파이프의 수 = (i, j-1)에 가로 + (i-1, j-1)에 대각선으로 놓을 수 있는 파이프의 수
		2-2. (i, j)에 대각선으로 놓을 수 있는 파이프의 수 = (i, j-1)에 가로 + (i-1, j-1)에 대각선 + (i-1, j)에 세로로 놓을 수 있는 파이프의 수
		2-3. (i, j)에 세로로 놓을 수 있는 파이프의 수 = (i-1, j-1)에 대각선 + (i-1, j)에 세로로 놓을 수 있는 파이프의 수
*/

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
		long[][][] dp = new long[N][N][3];
		
		for (int j=1; j<N; j++) {
			// 벽이 가로막고 있으면 이 이후로 파이프 놓을 수 없음
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