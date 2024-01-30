import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	
	static Stack<Integer> data = new Stack<>();
	static Stack<Integer> nums = new Stack<>();
	static List<String> ans = new ArrayList<>();


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i=n; i>0; i--) {
			nums.push(i);
		}
		
		
		int flag = 0;
xx:		for (int i=0; i<n; i++) {
			int inputNum = Integer.parseInt(br.readLine());
			while(true) {
				if (data.isEmpty() || data.peek() < inputNum) {
					data.push(nums.pop());
					ans.add("+");
				} else if (data.peek() == inputNum) {
					data.pop();
					ans.add("-");
					break;
				} else {
					flag = 1;
					break xx;
				}
			}
		}
		if (flag == 1) {
			System.out.println("NO");
		} else {
			for (String s : ans) {
				System.out.println(s);
			}
		}
	}
}