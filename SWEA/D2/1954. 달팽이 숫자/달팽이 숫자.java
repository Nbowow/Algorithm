/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int N, num;
	static int[][] snail;
	
	static void goRight(int row, int col) {
		if (num == Math.pow(N, 2)+1) return;
		for (int j=col; j<N; j++) {
			if (snail[row][j] != 0) {
				goDown(row+1, j-1);
				return;
			}
			if (j == N-1) {
				snail[row][j] = num++;
				goDown(row+1, j);
				return;
			}
			snail[row][j] = num++;
		}
	}
	
	static void goLeft(int row, int col) {
		if (num == Math.pow(N, 2)+1) return;
		for (int j=col; j>=0; j--) {
			if (snail[row][j] != 0) {
				goUp(row-1, j+1);
				return;
			}
			if (j == 0) {
				snail[row][j] = num++;
				goUp(row-1, j);
				return;
			}
			snail[row][j] = num++;
		}
	}

	static void goDown(int row, int col) {
		if (num == Math.pow(N, 2)+1) return;
		for (int i=row; i<N; i++) {
			if (snail[i][col] != 0) {
				goLeft(i-1, col-1);
				return;
			}
			if (i == N-1) {
				snail[i][col] = num++;
				goLeft(i, col-1);
				return;
			}
			snail[i][col] = num++;
		}
	}
	
	static void goUp(int row, int col) {
		if (num == Math.pow(N, 2)+1) return;
		for (int i=row; i>=0; i--) {
			if (snail[i][col] != 0) {
				goRight(i+1, col+1);
				return;
			}
			snail[i][col] = num++;
		}	
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			snail = new int[N][N];
			num  = 1;
			goRight(0, 0);
			
			System.out.println("#" + tc);
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					System.out.print(snail[i][j] +  " ");
				}
				System.out.println();
			}
		}
	}
}