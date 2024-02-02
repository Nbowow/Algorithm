import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int ans;
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			q.offer(i+1);
		}
		
		while (true) {
			ans = q.poll();
			if (q.isEmpty()) break;
			q.offer(q.poll());
		}
		System.out.println(ans);
	}
}