import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
	작성자 : 남보우
	문제 : [G5] 최소비용 구하기 - 1916번
	제출 : 2024년 2월 7일
	결과 : 통과
	성능 요약 : 메모리 48440KB, 시간 484ms
	아이디어 : 
		1. 시작도시(A)에서 인접한 다른 도시들로(B, C) 갈 때 필요한 비용을 구합니다.
		2. 구한 비용중 가장 비용이 작은 도시로 이동(B)하여 해당도시를 거쳐서 다른 도시로 갈때(C)
		3. 비용이 더 적다면 해당 값으로 비용을 갱신해줍니다. (다익스트라)
		4. 1~3을 반복하여 시작도시에서 원하는 도시까지가는데 필요한 최소 비용을 출력합니다.
*/

//노드를 이용하기 위한 클래스 선언(인덱스 번호(idx) 가중치(w)를 담고 있음)
class Node1 {
	// 가고자 하는 도시
	int idx;
	
	// 버스 비용
	int cost;
	
	Node1(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {

	static int N, M, start, end;
	static List<List<Node1>> city = new ArrayList<>();
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
			for (int j=0; j<city.get(nodeIdx).size(); j++) {
				Node1 adjNode = city.get(nodeIdx).get(j);
				
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
		
		// 1~N번 도시
		for (int i=0; i<N+1; i++) {
			city.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			city.get(s).add(new Node1(e, v));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Dijkstra();
		
		// 도착점까지의 최소 거리 출력
		System.out.println(dist[end]);
		
	}

}