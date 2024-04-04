import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int row, col, value;
		public Node(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node o) { // 오름차순 정렬
			return Integer.compare(this.value, o.value);
		}
	}

	static int N, ans;
	static int[][] map;
	static boolean[][] isVisited;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	
	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		isVisited[0][0] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.row;
			int y = cur.col;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (dx == N-1 && dy == N-1) {
					ans = cur.value;
					return;
				}
				
				if (!isIn(dx, dy) || isVisited[dx][dy]) continue;
				
				isVisited[dx][dy] = true;
				pq.offer(new Node(dx, dy, cur.value + map[dx][dy]));
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			isVisited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				char[] str = br.readLine().toCharArray();
				for (int j=0; j<N; j++) {
					map[i][j] = str[j] - 48;
				}
			}
			
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}