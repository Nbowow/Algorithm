import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R;
	static int[][] nums;
	static int[][] tmpMap;
	static boolean isChange;

	
	// 1. 상하 반전
	static void operator1() {
		for (int i=0; i<N/2; i++) {
			for (int j=0; j<M; j++) {
				int temp = nums[i][j];
				nums[i][j] = nums[N-1-i][j];
				nums[N-1-i][j] = temp;
			}
		}
	}
	
	// 2. 좌우 반전
	static void operator2() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M/2; j++) {
				int temp = nums[i][j];
				nums[i][j] = nums[i][M-1-j];
				nums[i][M-1-j] = temp;
			}
		}
	}
	
	// 3. 오른쪽 90도 회전
	static void operator3() {
		tmpMap = new int[M][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				tmpMap[j][N-1-i] = nums[i][j]; 
			}
		}
		
		int tmp = N;
		N = M;
		M = tmp;
		
		nums = tmpMap;
	}
	
	// 4. 왼쪽 90도 회전
	static void operator4() {
		tmpMap = new int[M][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				tmpMap[M-1-j][i] = nums[i][j];
			}
		}

		int tmp = N;
		N = M;
		M = tmp;
		
		nums = tmpMap;
	}
	
	// 5. 그룹 오른쪽 90도 회전
	static void operator5() {
		
		// 기존 배열 > 회전 배열로
		tmpMap = new int[N][M];
		// 1번그룹 -> 2번그룹
		for (int i=0; i<N/2; i++) {
			for (int j=M/2; j<M; j++) {
				tmpMap[i][j] = nums[i][j-M/2];
			}
		}
		
		// 2번 그룹 ->  3번 그룹
		for (int i=N/2; i<N; i++) {
			for (int j=M/2; j<M; j++) {
				tmpMap[i][j] = nums[i-N/2][j];
			}
		}
		
		// 3번 그룹 -> 4번 그룹
		for (int i=N/2; i<N; i++) {
			for (int j=0; j<M/2; j++) {
				tmpMap[i][j] = nums[i][j+M/2];
			}
		}

		// 4번 그룹 -> 1번 그룹
		for (int i=0; i<N/2; i++) {
			for (int j=0; j<M/2; j++) {
				tmpMap[i][j] = nums[i+N/2][j];
			}
		}
		
		nums = tmpMap;
	}
	
	// 6. 그룹 왼쪽 90도 회전
	static void operator6() {
		
		// 기존 배열 > 회전 배열로
		tmpMap = new int[N][M];
		
		// 1번그룹 -> 4번그룹
		for (int i=N/2; i<N; i++) {
			for (int j=0; j<M/2; j++) {
				tmpMap[i][j] = nums[i-N/2][j];
			}
		}
		
		// 4번 그룹 ->  3번 그룹
		for (int i=N/2; i<N; i++) {
			for (int j=M/2; j<M; j++) {
				tmpMap[i][j] = nums[i][j-M/2];
			}
		}
		
		// 3번 그룹 -> 2번 그룹
		for (int i=0; i<N/2; i++) {
			for (int j=M/2; j<M; j++) {
				tmpMap[i][j] = nums[i+N/2][j];
			}
		}

		// 2번 그룹 -> 1번 그룹
		for (int i=0; i<N/2; i++) {
			for (int j=0; j<M/2; j++) {
				tmpMap[i][j] = nums[i][j+M/2];
			}
		}
		
		nums = tmpMap;
	}

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N][M];
		R = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				nums[i][j] = n;
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<R; i++) {
			int oper = Integer.parseInt(st.nextToken());
			if (oper == 1) operator1();
			else if (oper == 2) operator2();
			else if (oper == 3) operator3();
			else if (oper == 4)	operator4();
			else if (oper == 5) operator5();
			else operator6();
		}
		
		printArray();
		
	}
	
	
	static void printArray() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}

}