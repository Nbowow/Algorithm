import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int row, col, key, move;
		public Node(int row, int col, int key, int move) {
			this.row = row;
			this.col = col;
			this.key = key;
			this.move = move;
		}
	}

	static int N, M;
	static char[][] map;
	static boolean[][][] isVisited;
	static Queue<Node> q = new ArrayDeque<>();
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int ans = -1;
	
	static void bfs() {

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			int key = cur.key;
			int move = cur.move;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (!isIn(dx, dy) || isVisited[dx][dy][key]) continue;
				
				isVisited[dx][dy][key] = true;
				
				if (map[dx][dy] == '.') q.offer(new Node(dx, dy, key, move + 1));
				else if (map[dx][dy] == '1') {
					ans = move + 1;
					return;
				}
				
				// 열쇠
				switch (map[dx][dy]) {
				case 'a': {
					isVisited[dx][dy][1<<0] = true;
					q.offer(new Node(dx, dy, key | 1<<0, move + 1));
					break;
					}
				case 'b': {
					isVisited[dx][dy][1<<1] = true;
					q.offer(new Node(dx, dy, key | 1<<1, move + 1));
					break;
					}
				case 'c': {
					isVisited[dx][dy][1<<2] = true;
					q.offer(new Node(dx, dy, key | 1<<2, move + 1));
					break;
					}
				case 'd': {
					isVisited[dx][dy][1<<3] = true;
					q.offer(new Node(dx, dy, key | 1<<3, move + 1));
					break;
					}
				case 'e': {
					isVisited[dx][dy][1<<4] = true;
					q.offer(new Node(dx, dy, key | 1<<4, move + 1));
					break;
					}
				case 'f': {
					isVisited[dx][dy][1<<5] = true;
					q.offer(new Node(dx, dy, key | 1<<5, move + 1));
					break;
					}
				}
				
				// 문
				switch (map[dx][dy]) {
				case 'A': {
					// 해당 문으로 가는 열쇠를 가지고 있다면
					if ((key & 1<<0) == 1<<0) q.offer(new Node(dx, dy, key, move + 1));
					break;
					}
				case 'B': {
					if ((key & 1<<1) == 1<<1) q.offer(new Node(dx, dy, key, move + 1));
					break;
					}
				case 'C': {
					if ((key & 1<<2) == 1<<2) q.offer(new Node(dx, dy, key, move + 1));
					break;
					}
				case 'D': {
					if ((key & 1<<3) == 1<<3) q.offer(new Node(dx, dy, key, move + 1));
					break;
					}
				case 'E': {
					if ((key & 1<<4) == 1<<4) q.offer(new Node(dx, dy, key, move + 1));
					break;
					}
				case 'F': {
					if ((key & 1<<5) == 1<<5) q.offer(new Node(dx, dy, key, move + 1));
					break;
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
		isVisited = new boolean[N][M][64];
		
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					q.offer(new Node(i, j, 0, 0));
					isVisited[i][j][0] = true;
					map[i][j] = '.';
				}
			}
		}
		
		bfs();
		System.out.println(ans);
		
	}
	
	static boolean isIn(int x, int y) {
		if (x>=0 && x<N && y>=0 && y<M) return true;
		return false;
	}

}