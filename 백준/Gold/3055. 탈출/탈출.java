import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;
	static Queue<int[]> wq = new ArrayDeque<>();
	static Queue<int[]> sq = new ArrayDeque<>();
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] wVisited;
	static boolean[][] sVisited;
	
	static void bfs() {
		
		while (!wq.isEmpty() || !sq.isEmpty()) {
			// 물 이동
			int wSize = wq.size();
			
			for (int w=0; w<wSize; w++) {
				int[] wtemp = wq.poll();
				int wx = wtemp[0];
				int wy = wtemp[1];
				
				for (int i=0; i<4; i++) {
					int wdx = wx + dxdy[i][0];
					int wdy = wy + dxdy[i][1];
					
					if (!isIn(wdx, wdy) || wVisited[wdx][wdy]) continue;
					wVisited[wdx][wdy] = true;
					
					if (map[wdx][wdy] == 'D' || map[wdx][wdy] == 'X') continue;
					
					map[wdx][wdy] = '*';
					wq.offer(new int[] {wdx, wdy});
				}
			}
			
			// 고슴도치 이동
			int sSize = sq.size();
			
			for (int s=0; s<sSize; s++) {
				int[] stemp = sq.poll();
				int sx = stemp[0];
				int sy = stemp[1];
				int time = stemp[2];
				
				for (int i=0; i<4; i++) {
					int sdx = sx + dxdy[i][0];
					int sdy = sy + dxdy[i][1];
					
					if (!isIn(sdx, sdy) || sVisited[sdx][sdy]) continue;
					sVisited[sdx][sdy] = true;
					
					// 비버굴 도착
					if (map[sdx][sdy] == 'D') {
						System.out.println(time + 1); // 시간 출력
						System.exit(0);
					}
					
					// 물
					if (map[sdx][sdy] == '*' || map[sdx][sdy] == 'X') continue;
					
					map[sdx][sdy] = 'S';
					sq.offer(new int[] {sdx, sdy, time + 1});
				}
				
				
			}

		}

	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		wVisited = new boolean[R][C];
		sVisited = new boolean[R][C];
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					wq.offer(new int[] {i, j});
					wVisited[i][j] = true;
				}
				
				if (map[i][j] == 'S') {
					sq.offer(new int[] {i, j, 0});
					sVisited[i][j] = true;
				}
			}
		}
	
		bfs();
		System.out.println("KAKTUS");
	}
	
	static boolean isIn(int x, int y) {
		if (x>=0 && x<R && y>=0 && y<C) return true;
		return false;
	}
	
	static void printMap() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	

}