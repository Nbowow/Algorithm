import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean isMove;
	static int N, L, R, t;
	static int[][] map;
	static boolean[][] isVisited;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static void bfs(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {row, col});
		isVisited[row][col] = true;
		
		// 연결된 나라 리스트
		List<int[]> list = new ArrayList<>();
		list.add(new int[] {row, col});
		
		int total = map[row][col];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (!isIn(dx, dy) || isVisited[dx][dy]) continue;
				
				int gap = Math.abs(map[dx][dy] - map[x][y]);
				
				// 인구 차이가 L명 이상, R명 이하
				if (gap >= L && gap <= R) {
					isVisited[dx][dy] = true;
					q.offer(new int[] {dx, dy});
					list.add(new int[] {dx, dy});
					total += map[dx][dy];
				}
			}
		}
		
		if (list.size() > 1) isMove = true;
		// 인구 분배
		for (int i=0; i<list.size(); i++) {
			map[list.get(i)[0]][list.get(i)[1]] = total/list.size();
		}
		
	}
	
	static void game() {
		// t초 (인구 이동이 없을 때 까지 반복)
		while (true) {
			isMove = false;
			isVisited = new boolean[N][N];
			// 모든 구역 연결 진행
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!isVisited[i][j]) {
						isVisited[i][j] = true;
						bfs(i, j);
					}
				}
			}

			// 이동 진행하지 않았을 경우 끝
			if (!isMove) break;
			t++;
		}

	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		game();
		
		System.out.println(t);
	}
	
	static void printMap(int[][] map) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}