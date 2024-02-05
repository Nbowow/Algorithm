import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] ans;
	static Stack<Integer> s1 = new Stack<>();
	static Stack<Integer> s2 = new Stack<>();
	static Map<Integer, Integer> valIndexMap = new HashMap<>();
	
	static void lazerBeam() {
xx:		while (!s1.isEmpty()) {
			if (s2.isEmpty()) {
				s2.push(s1.pop());
				
				while (!s2.isEmpty()) {
					// 자신보다 앞 기둥중에 자신보다 큰 기둥이 없을 경우
					if (s1.isEmpty()) {
						while (!s2.isEmpty()) {
							ans[valIndexMap.get(s2.pop())] = 0;
						}
						break xx;
					}
					
					// 나보다 앞이 기둥이 나보다 작을 경우
					if (s2.peek() > s1.peek()) {
						s2.push(s1.pop());
					}
					
					// 나보다 앞의 기둥이 나보다 클 경우
					else {
						// 자신의 기둥을 수신하는 기둥이 자신과 얼마나 떨어져있는지 카운팅해서 수를 넣어줌
						int num = s2.pop();
						ans[valIndexMap.get(num)] = valIndexMap.get(num) - valIndexMap.get(s1.peek()); 
					}
				}
			}
		}
	
	}
	
	public static void main(String[] args) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			s1.push(num);
			valIndexMap.put(num, i);
		}
		
		lazerBeam();

		// 자신의 빔을 수신하는 기둥 입력
		for (int i=1; i<=N; i++) {
			if (ans[i] == 0) continue;
			ans[i] = i-ans[i];
		}
		
		// 답 도출
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(ans[i]+ " ");
		}
		
		System.out.println(sb);
	}

}