import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] disjoint_sets;
	static int find(int x) {
		if (x == disjoint_sets[x]) return x;
		
		// 재귀 돌면서 방문하는 모든 노드 부모값 초기화 시켜줌
		disjoint_sets[x] = find(disjoint_sets[x]);
		return disjoint_sets[x];
	}
	
	static void union(int a, int b) {
		// a보다 b가 더 큰 집합(부모의 인덱스가 더 작은 값)에 속해있다면
		
		int aParents = find(a);
		int bParents = find(b);
		
		if (aParents >= bParents) disjoint_sets[aParents] = bParents;
		else disjoint_sets[bParents] = aParents;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			disjoint_sets = new int[N+1];
			for (int i=1; i<N+1; i++) {
				disjoint_sets[i] = i;
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (oper == 0) {
					union(a, b);
				}
				else if (oper == 1) {
					find(a); find(b);
					if (disjoint_sets[a] == disjoint_sets[b]) sb.append(1);
					else sb.append(0);
				}
				
//				System.out.println(Arrays.toString(disjoint_sets));
			}
			
			System.out.println("#" + tc + " " + sb);
		}
		
		
		
	}

}