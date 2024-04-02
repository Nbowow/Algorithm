import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
	@author : 남보우
	문제 : [B2] 피보나치 수 - 2747번
	제출 : 2024년 4월 2일
	결과 : 통과
	성능 요약 : 메모리 11472KB, 시간 80ms
	아이디어 : dp를 이용해 풀었습니다.
*/

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[n+1];
		dp[1] = 1;
		for (int i=2; i<n+1; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.println(dp[n]);
		
	}

}