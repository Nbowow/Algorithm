import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, R;
	static int[][] array;
	static int[][] ans;

	static void rotate(int srow, int scol, int lrow, int lcol) {
		
		// 아래로
		for (int i = srow; i<lrow-1; i++) {
			ans[i+1][scol] = array[i][scol];
		}
		

		// 오른쪽으로
		for (int j = scol; j<lcol-1; j++) {
			ans[lrow-1][j+1] = array[lrow-1][j];
		}
		

	
		// 위로
		for (int i = lrow-1; i>srow; i--) {
			ans[i-1][lcol-1] = array[i][lcol-1];
		}
		

		// 왼쪽으로
		for (int j = lcol-1; j>scol; j--) {
			ans[scol][j-1] = array[scol][j];
		}

		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				array[i][j] = ans[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		ans = new int[N][M];
		R = Integer.parseInt(st.nextToken());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				String str = st.nextToken();
				array[i][j] = Integer.parseInt(str);
				ans[i][j] = Integer.parseInt(str);
			}
		}
		
		// 회전해야하는 사각형 갯수
		for (int i=0; i<Math.min(N, M)/2; i++) {
			// 각 사각형마다 회전해야하는 횟수
			int tempN = N - 2*i;
			int tempM = M - 2*i;
			int boxSize = (Math.max(tempN, tempM) + Math.min(tempN, tempM) - 2) * 2;
			for (int j=0; j<R%boxSize; j++) {
				rotate(i, i, N-i, M-i);	
			}
		}

		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(ans[i][j] +  " ");
			}
			System.out.println();
		}
	
	}

}