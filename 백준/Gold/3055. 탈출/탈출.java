import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, ans;
	static boolean isArrive;
	static boolean[][] visitedWater;
	static boolean[][] visitedGosum;
	static Character[][] map;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> qGosum = new ArrayDeque<>();
	static Queue<int[]> qWater = new ArrayDeque<>();
	
	
	static void checkPath(int del, int row, int col) {
		for (int i=0; i<4; i++) {
			int dx = row + dxdy[i][0];
			int dy = col + dxdy[i][1];
			
			if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
				// 물
				if (del == 1 && (map[dx][dy] == '.' || map[dx][dy] == 'S')) {
					map[dx][dy] = '*';
				}
				
				// 고슴도치
				else if (del == 2) {
					// 집 도착
					if (map[dx][dy] == 'D') {
						isArrive = true;
						return;
					}
					else if (map[dx][dy] == '.') {
						map[dx][dy] = 'S';	
					}
				}
			}
		}
		
//		System.out.println("del : " + del);
//		printMap();
	}
	

	static void lookingHome(int count) {
		
		boolean isfail = true;
		
		// 고슴도치 먼저 이동
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {				
				if (visitedGosum[i][j] == false && map[i][j] == 'S') {
					visitedGosum[i][j] = true;
					isfail = false;
					// 모든 고슴도치 좌표 큐에 담음
					qGosum.offer(new int[] {i, j});
//					checkPath(2, i, j);
				}
			}
		}
		
		// 고슴도치 처리
		while(!qGosum.isEmpty()) {
			int[] temp = qGosum.poll();
			int r = temp[0];
			int c = temp[1];
			checkPath(2, r, c);
		}
		
		// 물로 덮음
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (visitedWater[i][j] == false && map[i][j] == '*') {
					visitedWater[i][j] = true;
					// 모든 물 좌표 큐에 담음
					qWater.offer(new int[] {i, j});
				}
			}
		}
		
		// 물 처리
		while(!qWater.isEmpty()) {
			int[] temp = qWater.poll();
			int r = temp[0];
			int c = temp[1];
			checkPath(1, r, c);
		}
		
		if (isArrive) {
			ans = count;
			return;
		} else if(isfail) return;
		else lookingHome(count + 1);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new Character[R][C];
		visitedWater = new boolean[R][C];
		visitedGosum = new boolean[R][C];
		
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		lookingHome(1);
		
		if (isArrive) System.out.println(ans);
		else System.out.println("KAKTUS");
	}
	
	static void printMap() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}

}