import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.Math.abs;

public class Solution {
	
	static int N, minDistance;
	static int[][] map;
	static boolean[] isVisited;
	static List<Integer> perm;

	static void dfs(int depth) {
		// 거리 계산
		if (depth == N) {
			int distance = 0;
			// 회사, 첫번째 고객 거리 구함
			distance += abs(map[0][0] - map[perm.get(0)][0]) + abs(map[0][1] - map[perm.get(0)][1]);
			
			for (int i=0; i<perm.size()-1; i++) {
				// 인덱스 i번째 고객, i+1번째 고객 사이 거리 구함
				distance += (abs(map[perm.get(i)][0] - map[perm.get(i+1)][0]) + abs(map[perm.get(i)][1] - map[perm.get(i+1)][1]));
			}
			
			// 마지막고객, 집 거리 구함
			distance += abs(map[N+1][0] - map[perm.get(perm.size()-1)][0]) + abs(map[N+1][1] - map[perm.get(perm.size()-1)][1]); 
			
			minDistance = Math.min(minDistance, distance);
			return;
		}
		
		// 인덱스 0은 회사, 인덱스 N+1은 집
		for (int i=1; i<N+1; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				perm.add(i);
				dfs(depth + 1);
				isVisited[i] = false;
				perm.remove(perm.size()-1);
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
			perm = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 집
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());
			map[0][0] = homeX;
			map[0][1] = homeY;
			
			// 회사
			int companyX = Integer.parseInt(st.nextToken());
			int companyY = Integer.parseInt(st.nextToken());
			map[N+1][0] = companyX;
			map[N+1][1] = companyY;
			
			// 고객
			for (int i=1; i<N+1; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			dfs(0);
			System.out.println("#" + tc + " " + minDistance);
//			printArray();
			
		}
	}
	
	static void printArray() {
		for(int i=0; i<N+2; i++) {
			for (int j=0; j<2; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}