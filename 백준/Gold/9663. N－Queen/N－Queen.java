import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int check;
	static int N;
	static int[][] nQ;
	static int[] Q;
	static int ans;
	static int[][] crossCheck = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
	
	static void NQueen(int count, int row) {
//		System.out.println("ans : " + ans);
//		for (int num : Q) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
		if (count == N) {
			ans += 1;
			return;
		}
		
		// 칸 확인
		for (int j=0; j<N; j++) {
			check = 1;
			for (int i=0; i<row; i++) {
				// 가로, 세로, 대각선 위치
				if (Q[i] == j || Math.abs(i-row) == Math.abs(j-Q[i])) {
					check = 0;
					break;
				}
			}
			if (check == 1) {
				Q[row] = j;
				NQueen(count + 1, row + 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Q = new int[N]; // 줄, 칸
		int count = 0;

		NQueen(count, 0);
		System.out.println(ans);
	}
}