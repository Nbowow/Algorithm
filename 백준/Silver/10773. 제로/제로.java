import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static Stack<Integer> stackInt = new Stack<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		for (int i=0; i<K; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				stackInt.pop();
			}
			else stackInt.push(n);
		}
		
		int ans = 0;
		while (!stackInt.isEmpty()) {
			ans += stackInt.pop();
		}
		
		System.out.println(ans);
	}

}