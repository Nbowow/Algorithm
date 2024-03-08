import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		int ans = 0;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (y == 0) { // 높이가 0일경우 그 전에 있던 스택 다 빼줌
				while (!stack.isEmpty()) {
					stack.pop();
					ans += 1;
				}
				continue;
			}
			
			if (stack.isEmpty()) stack.push(y);
			
			else { // 스택에 이미 수 들어있을 경우
				
				if (stack.peek() < y) stack.push(y); // 스택에 들어있는 수보다 클 경우
				else { // 스택에 들어있는 수보다 작을 경우
					while(!stack.isEmpty() && stack.peek() > y) { // 스택에 들어있는 수보다 크거나 같아질 때까지 스택 pop
						stack.pop();
						ans += 1;
					}
					
					if (stack.isEmpty() || stack.peek() < y) stack.push(y); // 스택에 들어있는 수보다 클 경우 스택에 넣어줌
					else if (stack.peek() == y) continue; // 스택에 들어있는 수와 같을 경우
				}
				
			}
		}
	
		// 마지막 스택에 남아있는 수 빼줌
		while (!stack.isEmpty()) {
			stack.pop();
			ans += 1;
		}
		
		System.out.println(ans);
		
		
	}

}