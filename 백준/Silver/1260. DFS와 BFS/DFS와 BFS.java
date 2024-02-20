import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node{
		int start;
		int end;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static int N, M, V;
	static List<List<Integer>> list = new ArrayList<>();
	static boolean[] isVisited;
	static StringBuilder dfsSb = new StringBuilder();
	static StringBuilder bfsSb = new StringBuilder();


	
	static void bfs(int startIndex) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startIndex);
		
		isVisited[startIndex] = true;
		while (!q.isEmpty()) {
			int index = q.poll();
			
			for (int i=0; i<list.get(index).size(); i++) {
				int nextIndex = list.get(index).get(i);
				if (!isVisited[nextIndex]) {
					isVisited[nextIndex] = true;
					bfsSb.append(nextIndex + " ");
					q.offer(nextIndex);
				}
			}
			
		}
		
	}
	
	static void dfs(int startIndex) {
		
		isVisited[startIndex] = true;
		
		for (int i=0; i<list.get(startIndex).size(); i++) {
			int nextIndex = list.get(startIndex).get(i);
			if (!isVisited[nextIndex]) {
				dfsSb.append(nextIndex + " ");
				dfs(nextIndex);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N+1; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		for (int i=0; i<N+1 ;i++) {
			Collections.sort(list.get(i));
		}
		
		isVisited = new boolean[N+1];
		dfsSb.append(V + " ");
		dfs(V);
		System.out.println(dfsSb);
		
		isVisited = new boolean[N+1];
		bfsSb.append(V+ " ");
		bfs(V);
		System.out.println(bfsSb);
		
	}

}