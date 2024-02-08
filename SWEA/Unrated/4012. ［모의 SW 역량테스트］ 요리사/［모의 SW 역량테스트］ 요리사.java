import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, tempA, tempB, subMin;
	static int[][] synergy;
	static int[] visited;
	static List<Integer> listA = new ArrayList<>();
	static List<Integer> listB = new ArrayList<>();
	static Queue<Integer> qA = new ArrayDeque<>();
	static Queue<Integer> qB = new ArrayDeque<>();
	// 조합
	static List<Integer> tempList = new ArrayList<>();
	
	// 모든 음식간의 시너지 고려
	static void dfs2(List<Integer> arr, int[] visit, int check, int idx) {
		if (tempList.size() == 2) {
			// A
			if (check == 1) {
				tempA += synergy[tempList.get(0)][tempList.get(1)];
				tempA += synergy[tempList.get(1)][tempList.get(0)];
			}
			
			// B
			else {
				tempB += synergy[tempList.get(0)][tempList.get(1)];
				tempB += synergy[tempList.get(1)][tempList.get(0)];
			}
			return;
		}
		
		for (int i=idx; i<N/2; i++) {
			if(visit[i] == 0) {
				visit[i] = 1;
				tempList.add(arr.get(i));
				dfs2(arr, visit, check, i+1);
				visit[i] = 0;
				tempList.remove(tempList.size()-1);
			}
		}
	}
	
	static void dfs(int count, int idx) {
		// 총 N개 중에 N/2개를 골랐을 경우 계산
		if (count == N/2) {
			listA = new ArrayList<>();
			listB = new ArrayList<>();
			tempA = 0;
			tempB = 0;
			//계산
			for (int i=0; i<N; i++) {
				// foodA 조합
				if (visited[i] == 1) listA.add(i);
				// foodB 조합
				else listB.add(i);
			}
			
			int[] visitedA = new int[N/2];
			int[] visitedB = new int[N/2];
			tempList = new ArrayList<>();
			dfs2(listA, visitedA, 1, 0);
			
			tempList = new ArrayList<>();
			dfs2(listB, visitedB, 2, 0);
			
			subMin = Math.min(subMin, Math.abs(tempA - tempB));

			return;
		}
		
		for (int i=idx; i<N; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				dfs(count + 1, i+1);
				visited[i] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			subMin = (int) 10e8;
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			visited = new int[N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			System.out.println("#" + tc + " " + subMin);
		}
	}

}