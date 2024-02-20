import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

//	static class Node {
//		int index;
//		int nextIndex;
//		
//		public Node(int index, int nextIndex) {
//			this.index = index;
//			this.nextIndex = nextIndex;
//		}
//	}
	
	static int N, M;
	static List<List<Integer>> students = new ArrayList<>();
	static int[] counts;
	static Queue<Integer> ans = new ArrayDeque<>();
	
	static void topological_sort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i=1; i<N+1; i++) {
			// 자신에게 들어오는 간선이 없는 경우 큐에 담아줌
			if (counts[i] == 0) q.offer(i);
		}
		
		while (!q.isEmpty()) {
			int index = q.poll();
			// 답 큐에 담아줌
			ans.offer(index);
			
			for (int i=0; i<students.get(index).size(); i++) {
				int hasNode = students.get(index).get(i);
				counts[hasNode]--;
				
				// 만약 위상이 0이 되는 경우 (자신에게 들어오는 간선이 없게 되는 경우) 큐에 넣는다
				if (counts[hasNode] == 0) q.offer(hasNode);
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		counts = new int[N+1];
		for (int i=0; i<N+1; i++) {
			students.add(new ArrayList<>());
		}
		
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			students.get(a).add(b);
			counts[b]++;
		}
		
		topological_sort();
		
		StringBuilder sb = new StringBuilder();
		while (!ans.isEmpty()) {
			sb.append(ans.poll() + " ");
		}
		
		System.out.println(sb);
	}

}