import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Customer implements Comparable<Customer> {
		int idx, sx, sy, ex, ey;
		int dist; // 택시까지의 거리
		int leftFuel; // 택시가 해당 손님을 태우러 왔을 때 남은 연료
		boolean isArrive;
		
		public Customer (int idx, int sx, int sy, int ex, int ey, int dist, int leftFuel, boolean isArrive) {
			this.idx = idx;
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
			this.dist = dist;
			this.leftFuel = leftFuel;
			this.isArrive = isArrive;
		}

		@Override
		public int compareTo(Customer o) {
			if (this.dist == o.dist) {
				if (this.sx == o.sx) {
					return Integer.compare(this.sy, o.sy);
				}
				return Integer.compare(this.sx, o.sx);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static class Move { // 택시 이동
		int row, col, dist, leftFuel;
		public Move(int row, int col, int dist, int leftFuel) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.leftFuel = leftFuel;
		}
	}
	
	static class Taxi {
		int row, col, fuel; // 남은 연료
		public Taxi(int row, int col, int fuel) {
			this.row = row;
			this.col = col;
			this.fuel = fuel;
		}
	}

	static int N, M, F;
	static boolean canAns = true;
	static int[][] map;
	static Taxi taxi;
	static Customer[] customers;
	static List<Customer> nextCustomers;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static void goDestination() {
		// 목적지로 이동시켜줘야 할 고객
		Customer curCustomer = null;
		for (int i=0; i<nextCustomers.size(); i++) {
			if (nextCustomers.get(i).isArrive) continue;
			
			curCustomer = nextCustomers.get(i);
			break;
		}
		
		// 더이상 이동 가능한 고객 X
		if (curCustomer == null) {
			canAns = false;
			return;
		}
		
		Queue<Move> q = new ArrayDeque<>();
		q.offer(new Move(curCustomer.sx, curCustomer.sy, 0, curCustomer.leftFuel));
		boolean[][] isVisited = new boolean[N][N];
		isVisited[curCustomer.sx][curCustomer.sy] = true;
		
		// 목적지 이동
		while (!q.isEmpty()) {
			Move cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (!isIn(dx, dy) || isVisited[dx][dy] || cur.leftFuel == 0) continue;
				isVisited[dx][dy] = true;
				
				// 목적지 도착
				if (dx == curCustomer.ex && dy == curCustomer.ey) {
					curCustomer.isArrive = true;
					map[curCustomer.sx][curCustomer.sy] = 0; // 손님 출발한 자리 빈공간으로 바꾸기
					taxi.fuel = cur.leftFuel - 1;
					taxi.fuel += (cur.dist + 1) * 2; // 승객을 태워 이동하면서 소모한 연료 양 두배 충전
					taxi.row = dx;
					taxi.col = dy;
					return;
				}
				
				// 벽이 아니면 이동 가능
				if (map[dx][dy] != -1) {
					q.offer(new Move(dx, dy, cur.dist + 1, cur.leftFuel - 1));
				}
				
			}
			
		}
		
		canAns = false;
		
	}
	
	static void calDist() {
		Queue<Move> q = new ArrayDeque<>();
		q.offer(new Move(taxi.row, taxi.col, 0, taxi.fuel));
		boolean[][] isVisited = new boolean[N][N];
		isVisited[taxi.row][taxi.col] = true;
		
		nextCustomers = new ArrayList<>();
		// 현재 택시 위치에 승객이 있을 경우 고려
		if (map[taxi.row][taxi.col] > 0) {
			customers[map[taxi.row][taxi.col] - 1].dist = 0;
			customers[map[taxi.row][taxi.col] - 1].leftFuel = taxi.fuel;
			nextCustomers.add(customers[map[taxi.row][taxi.col] - 1]);
			return;
		}
		
		while (!q.isEmpty()) {
			Move cur = q.poll();
			int x = cur.row;
			int y = cur.col;
			
			for (int i=0; i<4; i++) {
				int dx = x + dxdy[i][0];
				int dy = y + dxdy[i][1];
				
				if (!isIn(dx, dy) || isVisited[dx][dy] || cur.leftFuel == 0) continue;
				isVisited[dx][dy] = true;
				
				// 손님 만남
				if (map[dx][dy] > 0) {
					// 더 거리가 멀면 현재 우선순위에서 밀림
					if (nextCustomers.size() > 0 && cur.dist + 1 > nextCustomers.get(nextCustomers.size()-1).dist) return;
					
					Customer curCustomer = customers[map[dx][dy] - 1];
					curCustomer.dist = cur.dist + 1; // 손님 만나러 오는데 까지 이동한 거리
					curCustomer.leftFuel = cur.leftFuel - 1; // 손님 만나러 오는데까지 사용한 연료
					nextCustomers.add(customers[map[dx][dy] - 1]);
				}
				
				// 벽이 아니면 이동 가능
				else if (map[dx][dy] != -1) {
					q.offer(new Move(dx, dy, cur.dist + 1, cur.leftFuel - 1));
				}
			}
		}
		
		
	}
	
	static void game() {
		// M명의 고객 다 이동
		for (int c=0; c<M; c++) {
			if (!canAns) return;
			// bfs로 모든 승객과 택시와의 거리 업데이트
			calDist();
			
			// 승객 우선순위 업데이트
			Collections.sort(nextCustomers);
			
			// 우선순위가 높은 승객부터 태워서 목적지로 이동 bfs
			goDestination();
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) map[i][j] = -1; // 벽 1로 표시
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, F);
		
		customers = new Customer[M];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			
			customers[i] = new Customer(i+1, sx, sy, ex, ey, 0, F, false);
			// 각 손님 번호 map에 표시
			map[sx][sy] = i+1;
		}
		
		game();
		
		System.out.println(canAns ? taxi.fuel : -1);
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}