import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	
	static class TrainLoc {
		Train train;
		boolean isVertical;
		int dist;
		
		public TrainLoc(Train train, boolean isVertical, int dist) {
			this.train = train;
			this.isVertical = isVertical;
			this.dist = dist;
		}
	}
	
	static class Train {
		int row, col;
		
		public Train(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, ans;
	static char[][] map;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Train[] start;
	static Train[] end;
	
	static void bfs() {
		Queue<TrainLoc> q = new ArrayDeque<>();
		
		// 가로 세로 누워지는 것
		boolean[][][] isVisited = new boolean[N][N][2];
		boolean isV;
		if (start[0].col == start[1].col) { // 세로
			isVisited[start[1].row][start[1].col][1] = true;
			isV = true;
		}
		else { // 가로
			isVisited[start[1].row][start[1].col][0] = true;
			isV = false;
		}
		
		q.offer(new TrainLoc(start[1], isV, 0));
		
		while (!q.isEmpty()) {
			
			TrainLoc curTrain = q.poll();
			Train train = curTrain.train;
			int dist = curTrain.dist;
			
//			System.out.println(train.row + " " + train.col + " " + curTrain.isVertical + " " + dist);
			
			int r = train.row;
			int c = train.col;
			boolean v = curTrain.isVertical;
			
			
			// 세로 방향으로 기차가 놓여있을 때
			if (v) {
				
				// 답
				if (verticalCheck(r, c) && map[r-1][c] == 'E' && map[r][c] == 'E' && map[r+1][c] == 'E') {
					ans = dist;
					return;
				}
				
				// 상, 하, 좌, 우
				for (int i=0; i<4; i++) {
					int dr = r + dxdy[i][0];
					int dc = c + dxdy[i][1];
					
					if (!verticalCheck(dr, dc) || !verticalMapCheck(dr, dc) || isVisited[dr][dc][1]) continue;
					
					isVisited[dr][dc][1] = true;
					
					q.offer(new TrainLoc(new Train(dr, dc), v, dist + 1));
					
				}
				
				// 회전
				if (verticalCheck(r, c-1) && verticalMapCheck(r, c-1) 
						&& verticalCheck(r, c) && verticalMapCheck(r, c)
						&& verticalCheck(r, c+1) && verticalMapCheck(r, c+1) && !isVisited[r][c][0]) {
					isVisited[r][c][0] = true;
					q.offer(new TrainLoc(train, !v, dist + 1));
				}
				
			}
			// 가로 방향으로 기차가 놓여있을 때
			else {
				
				// 답
				if (horizontalCheck(r, c) && map[r][c-1] == 'E' && map[r][c] == 'E' && map[r][c+1] == 'E') {
					ans = dist;
					return;
				}
				
				// 상, 하, 좌, 우
				for (int i=0; i<4; i++) {
					int dr = r + dxdy[i][0];
					int dc = c + dxdy[i][1];
					
					if (!horizontalCheck(dr, dc) || !horizontalMapCheck(dr, dc) || isVisited[dr][dc][0]) continue;
					
					isVisited[dr][dc][0] = true;
					
					q.offer(new TrainLoc(new Train(dr, dc), v, dist + 1));
					
				}
				
				
				// 회전
				if (horizontalCheck(r-1, c) && horizontalMapCheck(r-1, c)
						&& horizontalCheck(r, c) && horizontalMapCheck(r, c)
						&& horizontalCheck(r+1, c) && horizontalMapCheck(r+1, c) && !isVisited[r][c][1]) {
					isVisited[r][c][1] = true;
					q.offer(new TrainLoc(train, !v, dist + 1));
				}
				
			}
			
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		start = new Train[3];
		end = new Train[3];
		
		int sIdx = 0;
		int eIdx = 0;
		for (int i=0; i<N; i++) {
			String temp = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'B') {
					start[sIdx++] = new Train(i, j);
				}
				else if (map[i][j] == 'E') {
					end[eIdx++] = new Train(i, j);
				}
			}
		}
		
		bfs();
		System.out.println(ans);
	}
	
	static void printMap(char[][] map) {
		for (int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
	static boolean verticalCheck(int x, int y) {
		return x-1>=0 && x+1<N && y>=0 && y<N; 
	}
	
	static boolean horizontalCheck(int x, int y) {
		return x>=0 && x<N && y-1>=0 && y+1<N;
	}
	
	static boolean verticalMapCheck(int x, int y) {
		return map[x-1][y] != '1' && map[x][y] != '1' && map[x+1][y] != '1';
	}
	
	static boolean horizontalMapCheck(int x, int y) {
		return map[x][y-1] != '1' && map[x][y] != '1' && map[x][y+1] != '1';
	}
	
}