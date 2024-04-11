import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	@author : 남보우
	문제 : [G3] 벽 부수고 이동하기2 - 14442번
	제출 : 2024년 4월 11일
	결과 : 통과
	성능 요약 : 메모리 336144KB, 시간 1520ms
	아이디어 : bfs와 3차원 방문배열을 이용해 풀었습니다.
		isVisited = {row, col, 벽 부순횟수} 를 저장해
		벽을 부순횟수에 따른 방문배열을 이용해 풀었습니다.
*/

public class Main {
	
	static class Node {
		int row, col, breakCount, dis;
		public Node(int row, int col, int breakCount, int dis) {
			this.row = row;
			this.col = col;
			this.breakCount = breakCount;
			this.dis = dis;
		}
	}

	static int N, M, K;
	static int[][] map;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int ans = Integer.MAX_VALUE;
	
	static void bfs() {
		
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0, 0, 1));
		boolean[][][] isVisited = new boolean[N][M][K+1];
		isVisited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			int c = cur.breakCount;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				// 도착
				if (dx == N-1 && dy == M-1) {
					ans = Math.min(ans, cur.dis + 1);
					continue;
				}
				
				// 맵 바깥이거나 이미 도달했던 경우
				if (!isIn(dx, dy) || isVisited[dx][dy][c]) continue;
				
				isVisited[dx][dy][c] = true;
				
				// 벽 만났을 때
				if (map[dx][dy] == 1 && c<K) q.offer(new Node(dx, dy, c + 1, cur.dis + 1));
				
				// 빈 공간일 때
				if (map[dx][dy] == 0) q.offer(new Node(dx, dy, c, cur.dis + 1));
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			String temp = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j) - 48;
			}
		}
		
		// edge case
		if (N == 1 && M == 1) {
			System.out.println(1);
		}
		else {
			bfs();
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
		
	}
	

	static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}