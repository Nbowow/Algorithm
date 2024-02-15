import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.Math.abs;

/*
	@author : 남보우
	문제 : [SW문제해결 응용] 3일차 - 최적 경로 - 1210번
	제출 : 2024년 2월 15일
	결과 : 통과
	성능 요약 : 메모리 - 22368kb, 시간 - 3333ms
	아이디어 : 
		1. N+2크기의 배열을 설정한 후, 0번째 인덱스는 회사, N+1번째 인덱스는 집 좌표를 넣어준다.
		2. 1~N인덱스를 돌며 나올수 있는 모든 경우의 수를 순열로 구한 후 각각의 거리를 계산해준다.
		3. 최단경로를 업데이트 해준다.
*/

public class Solution {
	
	static int N, minDistance;
	static int[][] map;
	static boolean[] isVisited;
	static List<Integer> perm;

	static void dfs(int depth, int bindex, int sum) {
		if (sum > minDistance) return;
		
		// 모든 고객을 다 돌았을 경우 거리 계산
		if (depth == N) {
			// 마지막고객, 집 거리 구함
			sum += abs(map[N+1][0] - map[bindex][0]) + abs(map[N+1][1] - map[bindex][1]); 
			
			minDistance = Math.min(minDistance, sum);
			return;
		}
		
		// 인덱스 0은 회사, 인덱스 N+1은 집
		for (int i=1; i<N+1; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				dfs(depth + 1, i, sum + abs(map[bindex][0] - map[i][0]) + abs(map[bindex][1] - map[i][1]));
				isVisited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][2];
			isVisited = new boolean[N+2];
			minDistance = (int)10e4;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 회사
			map[0][0] = Integer.parseInt(st.nextToken());
			map[0][1] = Integer.parseInt(st.nextToken());
			
			// 집
			map[N+1][0] = Integer.parseInt(st.nextToken());
			map[N+1][1] = Integer.parseInt(st.nextToken());
			
			// 고객
			for (int i=1; i<N+1; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + minDistance);
			
		}
	}

}