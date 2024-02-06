import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int idx;
	int value;
	
	Node(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
}

public class Main {

	static int V, E, K;
	static List<List<Node>> nodes = new ArrayList<>();
	// 노드의 방문 여부 저장
	static boolean[] visited;
	// 시작노드부터 해당노드까지의 거리
	static int[] d;
	
	static void Dijkstra() {
		// 노드 거리 초기화
		for (int i=1; i<V+1; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		//  시작 노드 값 초기화
		d[K] = 0;
		
		// 노드 갯수만큼 반복
		for (int i=0; i<V; i++) {
			
			// 해당 노드까지의 거리의 최솟값을 찾기 위한 변수
			int nodeValue = Integer.MAX_VALUE;
			
			// 거리의 최솟값을 가지고 있는 노드의 인덱스
			int nodeidx = 0;
			
			// 방문하지 않는 노드 중 거리가 최소인 노드 찾기
			for (int j=1; j<V+1; j++) {
				if (!visited[j] && d[j] < nodeValue) {
					nodeValue = d[j];
					nodeidx = j;
				}
			}
			// 거리 최솟값 노드 방문
			visited[nodeidx] = true;
			
			// 해당 노드와 연결된 모든 노드에 대해
			for (int j=0; j<nodes.get(nodeidx).size(); j++) {
				Node adjNode = nodes.get(nodeidx).get(j);
				
				// 시작 노드(K)에서 해당 노드(nodeIdx)를 거쳐 다른 노드(adjNode.idx)로 가는 것이 더 최솟값이라면
				if (d[adjNode.idx] > d[nodeidx] + adjNode.value) {
					// 해당 노드 거리 최솟값으로 변경
					d[adjNode.idx] = d[nodeidx] + adjNode.value;
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		visited = new boolean[V+1];
		d = new int[V+1];
		

		for (int i=0; i<V+1; i++) {
			nodes.add(new ArrayList<>());
		}
		E = Integer.parseInt(st.nextToken());
		
		// 시작 노드
		K = Integer.parseInt(br.readLine());
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			nodes.get(u).add(new Node(v, w));
		}
		
		Dijkstra();
		
		// 1번 노드부터 출력
		for (int i=1; i<d.length; i++) {
			if (d[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(d[i]);
		}
	}
}