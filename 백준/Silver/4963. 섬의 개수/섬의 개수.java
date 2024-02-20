import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int w, h;
	static int[][] island;
	static boolean[][] isVisited;
	static int[][] dxdy = {{-1, -1}, {-1, 0}, {-1, 1},
			{0, -1}, {0, 1},
			{1, -1}, {1, 0}, {1, 1}};
	
//	static void dfs() {
//		
//	}
	
	static void bfs(int row, int col) {
		
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(row, col));
		isVisited[row][col] = true;
		
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			int r = temp.row;
			int c = temp.col;
						
			for (int i=0; i<8; i++) {
				int dx = r + dxdy[i][0];
				int dy = c + dxdy[i][1];
				
				if (dx >=0 && dx<h && dy>=0 && dy<w) {
					if(!isVisited[dx][dy] && island[dx][dy] == 1) {
						isVisited[dx][dy] = true;
						q.offer(new Node(dx, dy));
					}
				}
			}
			
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// w = 너비(열)
			w = Integer.parseInt(st.nextToken());
			// h = 높이(행)
			h = Integer.parseInt(st.nextToken());
			island = new int[h][w];
			
			if (w == 0 && h == 0) break;
			
			for (int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<w; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			isVisited = new boolean[h][w];
			
			// bfs
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (!isVisited[i][j] && island[i][j] == 1) {
						bfs(i, j);
						ans+=1;
					}
				}
			}
			
			System.out.println(ans);
			
//			dfs();
		}
		
	}

}