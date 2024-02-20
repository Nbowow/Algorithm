import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	@author : 남보우
	문제 : [S2] 섬의 개수 - 4963번
	제출 : 2024년 2월 20일
	결과 : 통과
	성능 요약 : 메모리 16344KB, 시간 180ms
	아이디어 : bfs와 dfs 두 방법 모두 이용해 풀어보았습니다.
		bfs : (row, col) 인접한 곳 방문안되었으면 방문처리, q에 넣음 모든 q가 빌때까지 반복
		dfs : (row, col) 과 인접한 곳 방문안되었으면 그곳으로 다시 방문해서 dfs 반복
*/

public class Main {
	
	// 각 (row, col) 값을 담기 위한 클래스
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
	// 8방향
	static int[][] dxdy = {{-1, -1}, {-1, 0}, {-1, 1},
			{0, -1}, {0, 1},
			{1, -1}, {1, 0}, {1, 1}};
	
	static void dfs(int row, int col) {
		
		isVisited[row][col] = true;
		
		for (int i=0; i<8; i++) {
			int dx = row + dxdy[i][0];
			int dy = col + dxdy[i][1];
			
			if (dx >=0 && dx<h && dy>=0 && dy<w) {
				if(!isVisited[dx][dy] && island[dx][dy] == 1) {
					dfs(dx,  dy);
				}
			}
		}
	}
	
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
						// bfs는 항상 q에 넣을 때 방문처리!
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
			
			ans = 0;
			isVisited = new boolean[h][w];
			
			// dfs
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (!isVisited[i][j] && island[i][j] == 1) {
						dfs(i, j);
						ans+=1;
					}
				}
			}
			
			System.out.println(ans);
			
		}
		
	}

}