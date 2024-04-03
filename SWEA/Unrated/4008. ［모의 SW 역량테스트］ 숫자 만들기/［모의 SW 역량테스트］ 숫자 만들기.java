import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, maxSum, minSum;
	static HashMap<Integer, Character> map;
	static int[] operator;
	static int[] nums;
	static List<Integer> per;
	static Queue<Integer> q;
	
	static void calculate() {
		
		// 모든 숫자에 대해 연산자 하나씩 빼내면서 계산
		int count = nums[0];
		for (int i=1; i<N; i++) {
			Character oper = map.get(q.poll());

			switch (oper) {
			case '+': {
				count += nums[i];
				break;
			}
			case '-': {
				count -= nums[i];
				break;
			}
			case '*': {
				count *= nums[i];
				break;
			}
			case '/': {
				count /= nums[i];
				break;
			}
			}
		}
		
		minSum = Math.min(minSum, count);
		maxSum = Math.max(maxSum, count);
	}
	
	static void makePermutation(int depth) {
		
		if (depth == N-1) {
			q = new ArrayDeque<>();
			for (int i=0; i<N-1; i++) q.offer(per.get(i));
			
			calculate();
			return;
		}
		
		
		for (int i=0; i<4; i++) {
			if (operator[i] == 0) continue;
			
			operator[i]--;
			per.add(i);
			makePermutation(depth + 1);
			operator[i]++;
			per.remove(per.size()-1);
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		map.put(0, '+');
		map.put(1, '-');
		map.put(2, '*');
		map.put(3, '/');
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			operator = new int[4];
			
			per = new ArrayList<>();
			nums = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i=0; i<4; i++) operator[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			maxSum = Integer.MIN_VALUE;
			minSum = Integer.MAX_VALUE;
			makePermutation(0);
			
			System.out.println("#" + tc + " " + (maxSum - minSum));
		}
		
	}
	
}