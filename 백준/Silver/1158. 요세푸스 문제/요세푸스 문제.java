import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		sb.append("<");
		while(q.size()>1) {
			for (int i=0; i<K-1; i++) {
				q.offer(q.poll());
			}
			sb.append(q.poll() + ", ");
		}
		
		sb.append(q.poll() + ">");
		
		System.out.println(sb);
		
	}

}