import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int fibo(int N) {
		if (N == 0) return 0;
		else if (N == 1) return 1;
		
		return fibo(N-1) + fibo(N-2);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(fibo(n));
		
	}
}