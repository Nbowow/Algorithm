import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int idx;
	int cost;
	
	Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {

	static int N, M, start, end;
	static List<List<Node>> bus = new ArrayList<>();
	static boolean[] visited;
	static int[] dist;
	
	static void Dijkstra() {
		
		for (int i=1; i<N+1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		// 시작도시의 거리 = 0;
		dist[start] = 0;
		
		// 버스 갯수만큼 반복
		for (int i=0; i<N; i++) {
			
			// 최소 거리를 구하기 위한 변수
			int nodeValue = Integer.MAX_VALUE;
			
			// 최소 거리인 도시의 인덱스
			int nodeIdx = 0;
			
			for (int j=1; j< N+1; j++) {
				if (!visited[j] && dist[j] < nodeValue) {
					nodeValue = dist[j];
					nodeIdx = j;
				}
			}
			// 최소 거리인 도시 방문
			visited[nodeIdx] = true;
			
			// 도시와 연결된 모든 도시에 대해 최소 거리 갱신
			for (int j=0; j<bus.get(nodeIdx).size(); j++) {
				Node adjNode = bus.get(nodeIdx).get(j);
				
				if (dist[adjNode.idx] > dist[nodeIdx] + adjNode.cost) {
					dist[adjNode.idx] = dist[nodeIdx] + adjNode.cost;
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		dist = new int[N+1];
		M = Integer.parseInt(br.readLine());
		
		// 1~N번 버스
		for (int i=0; i<N+1; i++) {
			bus.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			bus.get(s).add(new Node(e, v));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Dijkstra();
		
		// 도착점까지의 최소 거리 출력
		System.out.println(dist[end]);
		
	}

}