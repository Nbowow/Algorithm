import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int N;
	static int[] nums;
	static List<Integer> ans = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	static void isPrime(int num, int count) {
		int flag = 0;
		// 소수 판별
		for (int i=2; i<= Math.sqrt(num); i++) {
			if (num % i == 0) {
				flag = 1;
				break;
			}
		}
		// 소수
		if (flag == 0) {
			if (count == N) ans.add(num);
			else {
				for (int i=1; i<=9; i++) {
					String str = Integer.toString(num) + Integer.toString(i);
					isPrime(Integer.parseInt(str), count+1);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		
		for (int i=2; i<=9; i++) {
			isPrime(i, 1);
		}
		
		for (int prime : ans) {
			System.out.println(prime);
		}
	}
}