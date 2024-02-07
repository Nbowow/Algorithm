import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int r, c, s;
	static int[][] A;
	static int[][] original;
	static int[][] temp;
	static boolean[] visited;
	static int[] permutation;
	static int[][] oper;
	static List<Integer> ans = new ArrayList<>();
	
	
	static void backTracking(int count) {
		// 모든 연산이 배열에 담겼을 때
		if (count == K) {
			// 원본 배열 arr배열에 담아줌
			copyOriginal();
						
			// 배열에 담긴 순서대로 연산 하여 배열A의 값 계산 후, 최솟값 갱신
			int n=0;
			while (n++<K) {
				for (int i=0; i<permutation.length; i++) {
					if (n == permutation[i]) {
						rotate(oper[i][0], oper[i][1], oper[i][2], oper[i][3]);
					}
				}
			}
			// 회전한 배열A의 값 출력
			ans.add(calA());
			return;
		}
		
		// 가능한 연산의 모든 조합 탐색
		for (int i=0; i<K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation[i] = count+1;
				backTracking(count + 1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate(int srow, int scol, int lrow, int lcol) {
		// 사각형 갯수만큼 반복
		for (int n=0; n<Math.min(lrow-srow+1, lcol-scol+1)/2; n++) {
			
			// temp에 ans복사해서 rotate 시켜줌
			copyArray();
			
			// 오른쪽 (마지막 열에서 -1 만큼 빼준 만큼만 반복)
			for (int j=scol+n; j<=lcol-n-1; j++) {
				temp[srow+n][j+1] = A[srow+n][j];
			}
			
			// 아래쪽
			for (int i=srow+n; i<=lrow-n-1; i++) {
				temp[i+1][lcol-n] = A[i][lcol-n];
			}
			
			// 왼쪽
			for (int j=lcol-n; j>=scol+n+1; j--) {
				temp[lrow-n][j-1] = A[lrow-n][j];
			}
			
			// 위쪽
			for (int i=lrow-n; i>=srow+n+1; i--) {
				temp[i-1][scol+n] = A[i][scol+n];
			}
			
			A = temp;
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		original = new int[N][M];

		K = Integer.parseInt(st.nextToken());
		visited = new boolean[K];
		permutation = new int[K];
		oper = new int[K][4];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				// 입력받는 원본 배열
				original[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 조합 (백트래킹)으로 돌려야함
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			oper[i] = new int[] {r-1-s, c-1-s, r-1+s, c-1+s};
		}
		
		backTracking(0);
		
		Collections.sort(ans);
		System.out.println(ans.get(0));
	}
	
	static void printArr(int[][] array) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// temp배열 A배열 참고해서 변경
	static void copyArray() {
		temp = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				temp[i][j] = A[i][j];
			}
		}
	}
	
	// 원본배열 A배열에 저장
	static void copyOriginal() {
		A = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				A[i][j] = original[i][j];
			}
		}
	}
	
	// 배열 A의 최솟값
	static int calA() {
		int minA = (int) 10e8;
		for (int i=0; i<N; i++) {
			int temp = 0;
			for (int j=0; j<M; j++) {
				temp += A[i][j];
			}
			minA = Math.min(minA, temp);
		}
		
		return minA;
	}

}