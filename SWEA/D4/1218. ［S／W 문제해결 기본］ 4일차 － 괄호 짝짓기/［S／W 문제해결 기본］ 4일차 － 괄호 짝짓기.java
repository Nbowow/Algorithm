import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static Stack<Character> brace = new Stack<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc=1; tc<=10; tc++) {
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int flag = 1;
			for (int i=0; i<str.length(); i++) {
				if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '<') {
					brace.push(str.charAt(i));
				}
				else {
					if (str.charAt(i) == ')') {
						if( brace.peek() == '(') brace.pop();
						else {
							flag = 0;
							break;
						}
					}
					if (str.charAt(i) == '}') {
						if( brace.peek() == '{') brace.pop();
						else {
							flag = 0;
							break;
						}
					}
					if (str.charAt(i) == ']') {
						if( brace.peek() == '[') brace.pop();
						else {
							flag = 0;
							break;
						}
					}
					if (str.charAt(i) == '>') {
						if( brace.peek() == '<') brace.pop();
						else {
							flag = 0;
							break;
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + flag);
		}
	}

}