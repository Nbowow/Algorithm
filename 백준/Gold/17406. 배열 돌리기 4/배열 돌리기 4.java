import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
	작성자 : 남보우
	문제 : [G4] 배열 돌리기4 - 17406번
	제출 : 2024년 2월 7일
	결과 : 통과
	성능 요약 : 메모리 302836KB, 시간 856ms
	아이디어 :
		1. 원본 배열 original, 모든 연산이 완료된 배열 A, 각 연산을 해줄때 사용할 배열 temp 총 3개를 이용하여 문제풀이 하였습니다.
		2. 입력받은 연산은 순열을 이용하여 모든 경우의수를 탐색해 '배열A'의 최솟값을 찾았습니다.
		3. 배열돌리기 연산을 진행할때 하나의 배열을 추가로 잡아서 진행하는 것이 시간복잡도나 공간복잡도 면에서
			좋지 않은 것 같아 해당 배열에서 rotate 해주는 방법으로 다시 구현해보려고 합니다.
*/

public class Main {

	static int N, M, K;
	static int r, c, s;
	// 모든 연산이 완료된 배열 A
	static int[][] A;
	// 처음 입력받은 원본 배열
	static int[][] original;
	// 입력받은 연산(rotate)을 수행하기 위한 배열
	static int[][] temp;
	// 연산을 순열로 조합할때 필요한 boolean 배열
	static boolean[] visited;
	// 연산을 순열로 조합한 모든 경우의 수가 담긴 배열
	static int[] permutation;
	// 해야하는 연산 저장해놓은 배열
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
						rotate2(oper[i][0], oper[i][1], oper[i][2], oper[i][3]);
					}
				}
			}
			// 회전한 배열A의 값 출력
			ans.add(calA());
			return;
		}
		
		// 가능한 연산의 모든 조합 탐색(순열)
		for (int i=0; i<K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation[i] = count+1;
				backTracking(count + 1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate2(int srow, int scol, int lrow, int lcol) {
		// 사각형 갯수만큼 반복
		for (int n=0; n<Math.min(lrow-srow+1, lcol-scol+1)/2; n++) {
			int t = A[srow+n][scol+n];
			
			// 오른쪽 회전 (마지막 열에서 -1 만큼 빼준 만큼만 반복)
			for (int j=scol+n; j<=lcol-n-1; j++) {
				int tmp = A[srow+n][j+1];
				A[srow+n][j+1] = t;
				t = tmp;
			}
			
			// 아래쪽 회전
			for (int i=srow+n; i<=lrow-n-1; i++) {
				int tmp = A[i+1][lcol-n];
				A[i+1][lcol-n] = t;
				t = tmp;
			}
			
			// 왼쪽 회전
			for (int j=lcol-n; j>=scol+n+1; j--) {
				int tmp = A[lrow-n][j-1];
				A[lrow-n][j-1] = t;
				t = tmp;
			}
			
			// 위쪽 회전
			for (int i=lrow-n; i>=srow+n+1; i--) {
				int tmp = A[i-1][scol+n];
				A[i-1][scol+n] = t;
				t = tmp;
			}
			
//			System.out.println("======돌린후======");
//			printArray();
		}
		
	}
	
	static void rotate(int srow, int scol, int lrow, int lcol) {
		// 사각형 갯수만큼 반복
		for (int n=0; n<Math.min(lrow-srow+1, lcol-scol+1)/2; n++) {
			
			// temp에 ans복사해서 rotate 시켜줌
			copyArray();
			
			// 오른쪽 회전 (마지막 열에서 -1 만큼 빼준 만큼만 반복)
			for (int j=scol+n; j<=lcol-n-1; j++) {
				temp[srow+n][j+1] = A[srow+n][j];
			}
			
			// 아래쪽 회전
			for (int i=srow+n; i<=lrow-n-1; i++) {
				temp[i+1][lcol-n] = A[i][lcol-n];
			}
			
			// 왼쪽 회전
			for (int j=lcol-n; j>=scol+n+1; j--) {
				temp[lrow-n][j-1] = A[lrow-n][j];
			}
			
			// 위쪽 회전
			for (int i=lrow-n; i>=srow+n+1; i--) {
				temp[i-1][scol+n] = A[i][scol+n];
			}
			
			// 회전한 결과 값 배열 A에 저장
			A = temp;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
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
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			// 해야하는 연산 저장해놓은 배열
			oper[i] = new int[] {r-1-s, c-1-s, r-1+s, c-1+s};
		}
		
		backTracking(0);
		
		Collections.sort(ans);
		System.out.println(ans.get(0));
//		System.out.println(ans.toString());
	}
	
	static void printArray() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(A[i][j] + " ");
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