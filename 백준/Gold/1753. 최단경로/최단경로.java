import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int nextIndex;
		int weight;
		
		public Node(int nextIndex, int weight) {
			this.nextIndex = nextIndex;
			this.weight = weight;
		}
	}

	static int V, E, K;
	static List<List<Node>> node = new ArrayList<>();
	static int[] d;
	static boolean[] isVisited;
	
	static void dijkstra() {
		
		// 시작 정점 K 부터 각 노드로 가는 최단 경로
		Arrays.fill(d, Integer.MAX_VALUE);
		
		d[K] = 0;
		
		// 모든 정점 탐색
		for (int i=1; i<V+1; i++) {
			
			// 최단경로의 간선 길이
			int nodeValue = Integer.MAX_VALUE;
			
			// 최단경로인 인덱스 번호
			int nodeIndex = -1;
			
			// 초기값 설정 및 연결된 노드중 최단경로 선택
			for (int j=1; j<V+1; j++) {
				if (!isVisited[j] && d[j] < nodeValue) {
					nodeValue = d[j];
					nodeIndex = j;
				}
			}
			
			if (nodeIndex == -1) break;
			
			isVisited[nodeIndex] = true;
			
			// nodeIndex와 연결된 모든 정점들에 대해 최단 경로 업데이트
			for (int j=0; j<node.get(nodeIndex).size(); j++) {
				Node temp = node.get(nodeIndex).get(j);
				if (!isVisited[temp.nextIndex] && d[nodeIndex] + temp.weight < d[temp.nextIndex]) {
					d[temp.nextIndex] = d[nodeIndex] + temp.weight;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		// 정점번호 : 1~V
		for (int i=0; i<V+1; i++) {
			node.add(new ArrayList<>());
		}
		d = new int[V+1];
		isVisited = new boolean[V+1];
		
		E = Integer.parseInt(st.nextToken());
		
		// 시작 정점
		K = Integer.parseInt(br.readLine());
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			node.get(a).add(new Node(b, c));
			
			// 단선 그래프이기 때문에 밑에 코드는 사용하면 안됨
//			node.get(b).add(new Node(a, c));
		}
		
		dijkstra();
		
		for (int i=1; i<V+1; i++) {
			if (d[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(d[i]);
		}
	}

}