import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, X;
	static int[][] map;
	static boolean[][][] isRoad; // (row, col, 검사방향)
	
	static boolean checkCol(int row) {
		int[][] cMap = copyMap();
		int idx = 0;
		// 앞 -> 뒤
		while (true) {
			idx++;
			if (idx >= N) break;
			
			// 올라가는 경사 만났을 경우
			if (cMap[row][idx] > cMap[row][idx-1]) {
				if (cMap[row][idx] - cMap[row][idx-1] > 1) return false;
				
				int originalNum = cMap[row][idx-1];
				for (int i=idx-1; i>idx-1-X; i--) {
					if (i < 0 || cMap[row][i] != originalNum || isRoad[row][i][0]) return false;
					
					isRoad[row][i][0] = true;
				}
				
			}
			// 내려가는 경사 만났을 경우
			else if (cMap[row][idx] < cMap[row][idx-1]) {
				// 차이가 1보다 크면 활주로 설치 불가능
				if (cMap[row][idx-1] - cMap[row][idx] > 1) return false;
				
				int originalNum = cMap[row][idx];
				for (int i=idx; i<idx+X; i++) {
					// 땅이 고르지 않을 경우 -> 활주로 설치가 불가능
					if (i >= N || cMap[row][i] != originalNum) return false;
					isRoad[row][i][0] = true;
				}
				
				idx += (X-1);
			}
			
		}
		
		// 모든 땅이 평평할 경우 활주로 설치 가능
		return true;
	}
	
	static boolean checkRow(int col) {
		int[][] cMap = copyMap();
		int idx = 0;
		// 앞 -> 뒤
		while (true) {
			idx++;
			if (idx >= N) break;
			
			// 올라가는 경사 만났을 경우
			if (cMap[idx][col] > cMap[idx-1][col]) {
				if (cMap[idx][col] - cMap[idx-1][col] > 1) return false;
				
				int originalNum = cMap[idx-1][col];
				for (int i=idx-1; i>idx-1-X; i--) {
					if (i < 0 || cMap[i][col] != originalNum || isRoad[i][col][1]) return false;
					
					isRoad[i][col][1] = true;
				}
				
			}
			// 내려가는 경사 만났을 경우
			else if (cMap[idx][col] < cMap[idx-1][col]) {
				// 차이가 1보다 크면 활주로 설치 불가능
				if (cMap[idx-1][col] - cMap[idx][col] > 1) return false;
				
				int originalNum = cMap[idx][col];
				for (int i=idx; i<idx+X; i++) {
					// 땅이 고르지 않을 경우 -> 활주로 설치가 불가능
					if (i >= N || cMap[i][col] != originalNum) return false;
					isRoad[i][col][1] = true;
				}
				
				idx += (X-1);
			}
			
		}
		// 모든 땅이 평평할 경우 활주로 설치 가능
		return true;
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		isRoad = new boolean[N][N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		// 가로 검사
		for (int i=0; i<N; i++) if(checkCol(i)) ans++;
		// 세로 검사
		for (int j=0; j<N; j++) if(checkRow(j)) ans++;
		
		System.out.println(ans);
		
	}
	
	// 원본 배열 복사 함수
	static int[][] copyMap() {
		int[][] temp = new int[N][N];
		for (int i=0; i<N; i++) temp[i] = map[i].clone();
		return temp;
	}

}