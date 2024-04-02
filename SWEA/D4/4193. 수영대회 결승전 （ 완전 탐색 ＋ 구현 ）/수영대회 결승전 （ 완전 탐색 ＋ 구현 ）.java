import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Person {
		int row, col, time;
		public Person(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}

	static int N, sx, sy, ex, ey;
	static int[][] map;
	static Queue<Person> q;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] isVisited;
	
	static int bfs() {
		
		while (!q.isEmpty()) {
			Person cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (dx == ex && dy == ey) {
					// 답
					return cur.time + 1;
				}
				
				if (!isIn(dx, dy) || isVisited[dx][dy]) continue;
				
				isVisited[dx][dy] = true;
				
				if (map[dx][dy] == 1) continue; // 도달 불가능
				if (map[dx][dy] == 2 && (cur.time + 1)% 3 != 0) {
					q.offer(new Person(x, y, cur.time + 1));
					isVisited[dx][dy] = false;
				}
				else q.offer(new Person(dx, dy, cur.time + 1));
				
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			isVisited = new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			map[sx][sy] =3;
			isVisited[sx][sy] = true;
			
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			map[ex][ey] = 4;
			
			q = new ArrayDeque<>();
			q.offer(new Person(sx, sy, 0));
			
			int ans = bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void printMap() {
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