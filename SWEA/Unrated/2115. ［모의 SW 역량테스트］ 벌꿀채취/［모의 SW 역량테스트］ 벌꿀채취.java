import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, C;
	static int[][] map;
	static Integer[] A;
	static Integer[] B;
	static int maxA, maxB;
	static int ans;
	
	static void dfs(int aORb, Integer[] temp, int idx, int sum, int income) {
		if (sum <= C) {
			if (aORb == 0) maxA = Math.max(maxA, income);
			else maxB = Math.max(maxB, income);
		}
		
		if (idx == temp.length) return;
		
		// 이번 idx 포함
		dfs(aORb, temp, idx+1, sum+temp[idx], income+(int)Math.pow(temp[idx], 2));
		
		// 이번 idx 포함 X
		dfs(aORb, temp, idx+1, sum, income);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			// A 완탐
			for (int n=0; n<N*N; n++) {
				A = new Integer[M];
				int a = 0;
				int ai = n/N;
				boolean canA = true;
				for (int i=n; i<n+M; i++) {
					if (i/N != ai) {
						canA = false;
						break;
					}
					A[a++] = map[i/N][i%N];
				}
				
				
				// B 완탐
				if (canA) {
					for (int j=n+M; j<N*N; j++) {
						B = new Integer[M];
						int b = 0;
						int bi = j/N;
						boolean canB = true;
						for (int bj=j; bj<j+M; bj++) {
							if (bj/N != bi) {
								canB = false;
								break;
							}
							B[b++] = map[bj/N][bj%N];
						}
						
						// A, B모두 구함
						if (canA && canB) {
							maxA = 0;
							maxB = 0;
							dfs(0, A, 0, 0, 0);
							dfs(1, B, 0, 0, 0);
							
							ans = Math.max(ans, maxA + maxB);
						}
					}
				}
			}
			
			System.out.println("#" + tc+ " " + ans);
		}
	}
}