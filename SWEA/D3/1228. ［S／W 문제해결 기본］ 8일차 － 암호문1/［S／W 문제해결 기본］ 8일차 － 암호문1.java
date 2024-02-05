import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, K, x, y;
	static Stack<String> originalPw = new Stack<>();;
	static Stack<String> tempStack = new Stack<>();
	static Stack<String> temp = new Stack<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1; tc<=10; tc++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				originalPw.push(st.nextToken() + " ");
			}
			
			K = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				if (st.nextToken().equals("I")) {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					
					// originalPw 값 따로 저장
					for (int j=originalPw.size(); j>x; j--) {
						tempStack.push(originalPw.pop());
					}
					
					for (int j=0; j<y; j++) {
						originalPw.push(st.nextToken() + " ");
					}
					
					while (!tempStack.isEmpty()) {
						originalPw.push(tempStack.pop());
					}
				}
			}
				
			
			while (!originalPw.isEmpty()) {
				tempStack.push(originalPw.pop());
			}
			
			System.out.print("#" + tc + " ");
			for (int i=0; i<10; i++) {
				System.out.print(tempStack.pop());
			}
			System.out.println();
		}
		
		
	}

}