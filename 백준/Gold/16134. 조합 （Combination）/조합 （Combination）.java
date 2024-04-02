import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int P = 1000000007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		// 페르마의 소정리와 모듈러 연산을 이용해 풀어보자
		long[] dp = new long[N+1];
		dp[0] = 1;
		// 모듈러 연산
		for (int i=1; i<N+1; i++) {
			dp[i] = (dp[i-1] * i) % P;
		}
		
		// 페르마의 소정리
		// dp[R] * dp[N-R] 때문에 long타입으로 선언해야함
		long multipleNum = fermat((dp[R] * dp[N-R]) % P, P-2);
		
		// 모듈러 연산
		System.out.println((dp[N] * multipleNum) % P);
		
	}
	
	static long fermat(long num, long idx) {
		if (idx == 1) return num;
		
		if (idx % 2 == 0) { // 짝수
			long temp = fermat(num, idx / 2);
			return (temp * temp) % P;
		}
		
		else { // 홀수
			long temp = fermat(num, idx - 1);
			return (temp * num) % P;
		}
	}

}