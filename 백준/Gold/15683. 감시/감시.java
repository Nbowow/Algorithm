import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class CCTV {
		int row;
		int col;
		
		public CCTV(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int N, M;
	// 사각 지대의 최소 크기
	static int ans = (int) 10e8;
	static int[][] map;
	static int[][] temp;
	// cctv 번호 및 정보를 담고 있는 배열 
	// ex) int[1][1] : 2번 cctv의 두번째 방향(세로)
	static int[][] cctv = {{0, 1, 2, 3}, {0, 1}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0}};
	// 각 cctv의 방향에 대한 체크 여부
	static boolean[][] isVisited = {{false, false, false, false},
			{false, false},
			{false, false, false, false},
			{false, false, false, false},
			{false}
	};
	// 현재 위치한 cctv정보를 담은 리스트
	static List<Integer> cctvDetails = new ArrayList<>();
	// 현재 위치한 cctv 및 방향 정보를 담은 리스트
	static List<int[]> comb = new ArrayList<>();
	// cctv가 위치한 위치 정보를 담은 리스트
	static List<CCTV> cctvLocation = new ArrayList<>();
	
	static void checkUp(int startIndex, int col) {
		for (int i=startIndex; i>=0; i--) {
			if (temp[i][col] == 6) break;
			
			if (temp[i][col] == 0) temp[i][col] = -1;
		}
	}
	
	static void checkDown(int startIndex, int col) {
		for (int i=startIndex; i<N; i++) {
			if (temp[i][col] == 6) break;
			
			if (temp[i][col] == 0) temp[i][col] = -1;
		}
	}
	
	static void checkRight(int row, int startIndex) {
		for (int j=startIndex; j<M; j++) {
			if (temp[row][j] == 6) break;
			
			if (temp[row][j] == 0) temp[row][j] = -1;
		}
	}
	
	static void checkLeft(int row, int startIndex) {
		for (int j=startIndex; j>=0; j--) {
			if (temp[row][j] == 6) break;
			
			if (temp[row][j] == 0) temp[row][j] = -1;
		}
	}
	
	static void shoot(int num, int direction, int row, int col) {
		
		// 1번 cctv
		if (num == 0) {
			// 오른쪽
			if (direction == 0) checkRight(row, col);
			// 아래
			else if (direction == 1) checkDown(row, col);
			// 왼쪽
			else if (direction == 2) checkLeft(row, col);
			// 위
			else if (direction == 3) checkUp(row, col);
		}
		// 2번 cctv
		else if (num == 1) {
			// 좌우
			if (direction == 0) {
				checkRight(row, col);
				checkLeft(row, col);
			}
			// 상하
			else if (direction == 1) {
				checkUp(row, col);
				checkDown(row, col);
			}
		}
		// 3번 cctv
		else if (num == 2) {
			// Up, Right
			if (direction == 0) {checkUp(row, col); checkRight(row, col);}
			// Right, Down
			else if (direction == 1) {checkRight(row, col); checkDown(row, col);}
			// Down, Left
			else if (direction == 2) {checkDown(row, col); checkLeft(row, col);}
			// Left, Up
			else if (direction == 3) {checkLeft(row, col); checkUp(row, col);}
		}
		// 4번 cctv
		else if (num == 3) {
			// Left, Up, Right
			if (direction == 0) {checkLeft(row, col); checkUp(row, col); checkRight(row, col);}
			// Up, Right, Down
			else if (direction == 1) {checkUp(row, col); checkRight(row, col); checkDown(row, col);}
			// Right, Down, Left
			else if (direction == 2) {checkRight(row, col); checkDown(row, col); checkLeft(row, col);}
			// Down, Left, Up
			else if (direction == 3) {checkDown(row, col); checkLeft(row, col); checkUp(row, col);}
		}
		// 5번 cctv
		else if (num == 4) {
			checkUp(row, col);
			checkDown(row, col);
			checkRight(row, col);
			checkLeft(row, col);
		}
			
	}
	
	static void monitor() {
		// 사무실 감시하기
		temp = new int[N][M];
		copyMap();
		
		// 모든 감시카메라의 설정된 방향에 따라 지도 감시
		for (int i=0; i<comb.size(); i++) {
			int cctvNumber = comb.get(i)[0];
			int cctvDirection = comb.get(i)[1];
			int row = cctvLocation.get(i).row;
			int col = cctvLocation.get(i).col;
			
			shoot(cctvNumber, cctvDirection, row, col);
		}
		
	}
	
	static void makeComb(int index) {
		// cctv 갯수에 대한 조합 완료
		if (index == cctvDetails.size()) {
			
			monitor();
			
			int count = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (temp[i][j] == 0) count++; 
				}
			}
			
			ans = Math.min(ans, count);
			
			return;
		}
		
		
		// 1번 cctv
		if (cctvDetails.get(index) == 1) {
			for (int i=0; i<cctv[0].length; i++) {
				comb.add(new int[] {0, i});
				makeComb(index+1);
				comb.remove(comb.size()-1);
			}
		}
		// 2번 cctv
		if (cctvDetails.get(index) == 2) {
			for (int i=0; i<cctv[1].length; i++) {
				comb.add(new int[] {1, i});
				makeComb(index+1);
				comb.remove(comb.size()-1);
			}
		}
		// 3번 cctv
		if (cctvDetails.get(index) == 3) {
			for (int i=0; i<cctv[2].length; i++) {
				comb.add(new int[] {2, i});
				makeComb(index+1);
				comb.remove(comb.size()-1);
			}
		}
		// 4번 cctv
		if (cctvDetails.get(index) == 4) {
			for (int i=0; i<cctv[3].length; i++) {
				comb.add(new int[] {3, i});
				makeComb(index+1);
				comb.remove(comb.size()-1);
			}
		}
		// 5번 cctv
		else if (cctvDetails.get(index) == 5) {
			for (int i=0; i<cctv[4].length; i++) {
				comb.add(new int[] {4, i});
				makeComb(index+1);
				comb.remove(comb.size()-1);
			}
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// cctv이면
				if (map[i][j] != 0 && map[i][j] != 6) {
					// cctv 번호 넣어줌
					cctvDetails.add(map[i][j]);
					// cctv 좌표 정보 넣어줌
					cctvLocation.add(new CCTV(i, j));
				}
			}
		}
		
		makeComb(0);
		System.out.println(ans);
	}
	
	static void copyMap() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

}