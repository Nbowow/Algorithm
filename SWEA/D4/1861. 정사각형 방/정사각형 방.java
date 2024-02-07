import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] rooms;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] ans;
	
	static void move(int val, int count, int srow, int scol, int row, int col) {
		ans[rooms[srow][scol]] = Math.max(ans[rooms[srow][scol]], count);
		
		for (int i=0; i<4; i++) {
			int dx = row + dxdy[i][0];
			int dy = col + dxdy[i][1];
			
			if (dx >= 0 && dx < N && dy >=0 && dy < N) {
				if (rooms[dx][dy] == val + 1) {
					move(rooms[dx][dy], count+1, srow, scol, dx, dy);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			ans = new int[N*N + 1];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					move(rooms[i][j], 1, i, j, i, j);
				}
			}
			
			int[] res = calMax();
			System.out.println("#" + tc + " " + res[0] + " " + res[1]);
			
		}
	}
	
	static int[] calMax() {
		int maxIdx = 0;
		int maxVal = 0;
		
		for (int i=0; i<ans.length; i++) {
			if (ans[i] > maxVal) {
				maxVal = ans[i];
				maxIdx = i;
			}
		}
		return new int[] {maxIdx, maxVal};
		
	}

}