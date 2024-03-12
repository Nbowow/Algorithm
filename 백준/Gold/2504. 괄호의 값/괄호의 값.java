import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] bracket = br.readLine().toCharArray();
		Stack<Character> st = new Stack<>() ;
		
		int ans  = 0;
		int value = 1;
		for (int i=0; i<bracket.length; i++) {
			if (bracket[i] == '(') {
				st.push(bracket[i]);
				value *= 2;
			}
			
			else if (bracket[i] == '[') {
				st.push(bracket[i]);
				value *= 3;
			}
			
			else if (bracket[i] == ')') {
				if (st.isEmpty() || st.peek() != '(') {
					System.out.println(0);
					System.exit(0);
				}
				
				else if (bracket[i-1] == '(') {
					ans += value;
				}
				value /= 2;
				st.pop();
			}
			
			else if (bracket[i] == ']') {
				if (st.isEmpty() || st.peek() != '[') {
					System.out.println(0);
					System.exit(0);
				}
				
				else if (bracket[i-1] == '[') {
					ans += value;
				}
				value /= 3;
				st.pop();
			}
		}
		
		if (st.isEmpty()) System.out.println(ans);
		else System.out.println(0);
	}

}