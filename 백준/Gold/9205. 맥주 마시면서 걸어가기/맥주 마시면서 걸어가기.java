import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int row, col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int endX, endY;
	static int[][] map;
	static Queue<Node> q;
	static List<Node> visitList;
	static boolean isHappy;
	
	static void bfs() {
		
		while (true) {
			int visitSize = visitList.size();
			// 목적지 도달 가능 여부 판단
			for (int i=0; i<visitSize; i++) {
//				System.out.println(visitList.get(i).row + " " + visitList.get(i).col);
				if (Math.abs(endX - visitList.get(i).row) + Math.abs(endY - visitList.get(i).col) <= 1000) {
					isHappy = true;
					return;
				}
			}
			
			// 도달 가능한 편의점 찾기
			int qSize = q.size();
			for (int s=0; s<qSize; s++) {
				Node cur = q.poll();
				int x = cur.row;
				int y = cur.col;
				boolean canReach = false;
				for (int i=0; i<visitSize; i++) {
					// 도달 가능한 곳이면
					if (Math.abs(x-visitList.get(i).row) + Math.abs(y-visitList.get(i).col) <= 1000) {
						visitList.add(cur);
						canReach = true;
						break;
					}
				}
				
				// 도달 불가능한 곳이면 다시 큐에 삽입
				if (!canReach) q.offer(cur);
			}
			
			// 도달 가능한 편의점에 변화가 없다면
			if (visitSize == visitList.size()) return;
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=t; tc++) {
			q = new ArrayDeque<>();
			visitList = new ArrayList<>();
			isHappy = false;
			
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			// 도달 가능한 지역
			visitList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				// 아직 도달 못한 지역
				q.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			bfs();
			System.out.println(isHappy ? "happy" : "sad");
			
		}
	}
	

}