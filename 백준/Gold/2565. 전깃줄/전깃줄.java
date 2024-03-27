import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int a, b;
		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.a, o.a);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Node[] nd = new Node[N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nd[i] = new Node(a, b);
		}
		
		Arrays.sort(nd);
		
		int[] dp = new int[N];
		for (int i=0; i<N; i++) {
			dp[i] = 1;
			for (int j=i; j>=0; j--) {
				if (nd[j].b < nd[i].b) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		Arrays.sort(dp);
		
		System.out.println(N - dp[dp.length-1]);
		
	}

}