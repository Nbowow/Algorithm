import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Tree implements Comparable<Tree>{
		int from;
		int to;
		int weight;
		
		public Tree(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 오름차순 정렬
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E;
	static List<Tree> spTree = new ArrayList<>();
	static int[] parents;
	
	static int find(int x) {
		if (x==parents[x]) return x;
		
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa==pb) return false;
		
		parents[pb] = pa;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		
		E = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			spTree.add(new Tree(a, b, c));
		}
		
		// 간선들의 가중치 기준 정렬
		Collections.sort(spTree);
		
		for (int i=1; i<parents.length; i++) {
			parents[i] = i;
		}
		
		long ans = 0;
		for (int i=0; i<spTree.size(); i++) {
			Tree temp = spTree.get(i);
			
			// 이미 MST에 포함되어 있다면 해당 간선 선택하지 않음
			// MST에 포함되지 않은 간선이면 MST에 포함되는 간선이므로 선택
			if (union(temp.from, temp.to)) ans += temp.weight; 
		}
		
		System.out.println(ans);
		
	}

}