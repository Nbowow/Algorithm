import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static final int MOD = 1_000_000;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger n = new BigInteger(br.readLine());
		
		List<Long> rest = new ArrayList<>();
		rest.add(1L);
		rest.add(1L);
		
		long a = 1;
		long b = 1;
		while (true) {
			long temp = (a+b) % MOD;
			a = b;
			b = temp;
			rest.add(temp);
			
			// 피사노 주기
			if (a == 1 && b == 1) break;
			
			
		}
		rest.remove(rest.size()-1);
		rest.remove(rest.size()-1);
		
		// 1_000_000으로 나눈 나머지 순열의 길이 = 1_500_000
		int idx = n.mod(new BigInteger("1500000")).intValue();
		System.out.println(rest.get(idx-1));
		
	}

}