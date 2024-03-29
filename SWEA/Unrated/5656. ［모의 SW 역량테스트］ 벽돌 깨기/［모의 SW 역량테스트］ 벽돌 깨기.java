import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Block {
		int row, col, range;
		public Block(int row, int col, int range) {
			this.row = row;
			this.col = col;
			this.range = range;
		}
	}

	static int N, W, H;
	static int ans;
	static int[][] map;
	static List<Integer> combList;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static void bfs(Queue<Block> q, boolean[][] isVisited, int[][] tempMap) {
		
		while (!q.isEmpty()) {
			Block cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			int range = cur.range;
			
			tempMap[x][y] = 0;
			// 해당 블록만 부심
			if (range == 1) continue;
			
			for (int i=0; i<4; i++) {
				int dx = x;
				int dy = y;
				for (int j=0; j<range-1; j++) {
					dx += dxdy[i][0];
					dy += dxdy[i][1];
					
					if (!isIn(dx, dy) || isVisited[dx][dy]) continue;
					isVisited[dx][dy] = true;
					
					if (tempMap[dx][dy] != 0) {
						q.offer(new Block(dx, dy, tempMap[dx][dy]));
					}
				}
			}
		}
		
		// 남아있는 블록 밑으로 하강
		for (int j=0; j<W; j++) {
			Queue<Integer> tq = new ArrayDeque<>();
			for (int i=H-1; i>=0; i--) {
				if (tempMap[i][j] != 0) {
					tq.offer(tempMap[i][j]);
					tempMap[i][j] = 0;
				}
			}
			
			for (int i=H-1; i>=0; i--) {
				if (tq.isEmpty()) break;
				tempMap[i][j] = tq.poll();
			}
			
		}
//		
//		printMap(tempMap);
//		System.out.println();
		
	}
	
	static void breakBlock() {
		
		int[][] tempMap = copyMap(map);
		
		for (int s=0; s<combList.size(); s++) {
			int w = combList.get(s);
			
			for (int h=0; h<H; h++) {
				// 벽돌일 경우
				if (tempMap[h][w] != 0) {
					boolean[][] isVisited  = new boolean[H][W];
					isVisited[h][w] = true;
					
					Queue<Block> q = new ArrayDeque<>();
					q.offer(new Block(h, w, tempMap[h][w]));
					bfs(q, isVisited, tempMap);
					break;
				}
			}
			
		}
		
		// 모든 벽돌 다 깬후 남은 벽돌 갯수 카운팅
		int count = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (tempMap[i][j] != 0) count++;
			}
		}
		
		// 남은 벽돌의 최솟값 구함
		ans = Math.min(ans,  count);
	}
	
	static void makePermutation(int depth) {
		
		// 부시는 순서도 중요하므로 조합이 아닌 순열을 만들어야한다.
		if (depth == N) {
//			System.out.println(combList.toString());
			breakBlock();
			return;
		}
		
		for (int i=0; i<W; i++) {
			combList.add(i);
			makePermutation(depth + 1);
			combList.remove(combList.size()-1);
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;
			map = new int[H][W];
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combList = new ArrayList<>();
			makePermutation(0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void printMap(int[][] map) {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int[][] copyMap(int[][] arr) {
		int[][] temp = new int[H][W];
		for (int i=0; i<H; i++) temp[i] = map[i].clone();
		
		return temp;
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<H && y>=0 && y<W;
	}

}