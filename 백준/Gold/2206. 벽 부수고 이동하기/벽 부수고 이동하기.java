import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int row;
		int col;
		int dist;
		boolean isBreak;
		
		public Node(int row, int col, int dist, boolean isBreak) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.isBreak = isBreak;
		}
	}

	static int N, M;
	static int ans = (int)10e7;
	static int[][] map;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Node> wall = new ArrayDeque<>();
	static Queue<Node> q;
	static boolean[][][] isVisited;
	
	static void bfs() {
		
		q = new ArrayDeque<>();
		q.offer(new Node(0, 0, 1, false));
		// [][][0] : 벽 부수지 않았을 때, [][][1] : 벽 부쉈을 때
		isVisited = new boolean[N][M][2];
		isVisited[0][0][0] = true;
		isVisited[0][0][1] = true;
		
		while (!q.isEmpty()) {
			Node p = q.poll();
			
			int x = p.row;
			int y = p.col;
			int d = p.dist;
			boolean isBreak = p.isBreak;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (dx>=0 && dx<N && dy>=0 && dy<M) {
					// 아직 벽 안 부쉈을 때
					if (!isBreak) {
						if (!isVisited[dx][dy][0]) {
//							System.out.println("dxdy " + dx + " " + dy + " " + isBreak);
							isVisited[dx][dy][0] = true;
							
							// 도착
							if (dx == N-1 && dy == M-1) {
								ans = Math.min(ans, d+1);
								break;
							}
							
							// 벽 부수기 가능
							if (map[dx][dy] == 1) {
								q.offer(new Node(dx, dy, d+1, true));
							}
							
							else if (map[dx][dy] == 0) {
								q.offer(new Node(dx, dy, d+1, isBreak));
							}
						}
						
					}
					// 이미 벽 부순 상태일 때
					else if (isBreak) {
						if (!isVisited[dx][dy][1]) {
							isVisited[dx][dy][1] = true;
							
							// 도착
							if (dx == N-1 && dy == M-1) {
								ans = Math.min(ans, d+1);
								break;
							}
							
							// 벽 더이상 못 부숨
							if (map[dx][dy] == 1) continue;
							
							q.offer(new Node(dx, dy, d+1, isBreak));
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0 ;j<M; j++) {
				map[i][j] = (int)str.charAt(j) - 48;
			}
		}
		
		// N과 M이 1이면(엣지 케이스)
		if (N == 1 && M == 1) System.out.println(1);
		else {
			bfs();
			if (ans < (int)10e7) System.out.println(ans);
			else System.out.println(-1);
		}
		
		
		
	}

}