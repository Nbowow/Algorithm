import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import static java.lang.Math.pow;

public class Main {
	
	static class Node {
		int index;
		int height;
		
		public Node(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}
	
	static int N, Q;
	static int[] H;
	static long[][] dp;
	static Stack<Node> s1 = new Stack<>(); 
	static Stack<Node> s2 = new Stack<>();
	
	static void rightDp() {
		// 초기 값 설정
		s2.push(new Node(N-1, H[N-1]));
		
		for (int i=N-2; i>=0; i--) {
			
			while (!s2.isEmpty() && s2.peek().height < H[i]) s2.pop();
				
			if (s2.isEmpty()) {
				s2.push(new Node(i, H[i]));
				dp[i][1] = 0;
			}
			else if (s2.peek().height >= H[i]) {
				Node node = s2.peek();
				if (s2.peek().height == H[i]) dp[i][1] = dp[s2.peek().index][1] + (long)pow(node.index - i, 2);
				else dp[i][1] = dp[node.index][1] + ((long)pow(node.index - i, 2) + (long)pow(node.height - H[i], 2));
				s2.push(new Node(i, H[i]));
			}
		}
	}
	
	static void leftDp() {
		// 초기 값 설정
		s1.push(new Node(0, H[0]));
		
		for (int i=1; i<N; i ++) {
			// 자신보다 더 큰 높이를 가진 전봇대가 나올때까지 찾음
			while (!s1.isEmpty() && s1.peek().height < H[i]) s1.pop();
			
			if (s1.isEmpty()) {
				s1.push(new Node(i, H[i]));
				dp[i][0] = 0;
			}
			
			// 높이 계산해서 dp 넣어줌
			else if (s1.peek().height >= H[i]) {
				Node node = s1.peek();
				// 높이 같을 경우
				if (s1.peek().height == H[i]) dp[i][0] = dp[s1.peek().index][0] + (long)pow(node.index - i, 2);
				else dp[i][0] = dp[node.index][0] + ((long)pow(node.index - i, 2) + (long)pow(node.height - H[i], 2));
				s1.push(new Node(i, H[i]));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        H = new int[N];
        dp = new long[N][2];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        leftDp();
        rightDp();
        
//        for (long[] num : dp) {
//        	System.out.println(Arrays.toString(num));
//        }
        
        Q = Integer.parseInt(br.readLine());
        for (int i=0; i<Q; i++) {
        	int temp = Integer.parseInt(br.readLine());
        	ans.append((dp[temp-1][0] + dp[temp-1][1]) + "\n");
        }
//        ans.setLength(ans.length() - 1);
        System.out.println(ans);
	}

}