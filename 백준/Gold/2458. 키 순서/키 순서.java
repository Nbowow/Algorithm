import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] nums;
	static List<List<Integer>> nodes = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nums = new int[N+1];
		for (int i=0; i<N+1; i++) {
			nodes.add(new ArrayList<>());
		}
		
		// 비교횟수
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// a에서 b로 가는 경로
			nodes.get(a).add(b);
		}
		
		for (int i=1; i<nodes.size(); i++) {
			Queue<Integer> q = new ArrayDeque<>();
			boolean[] isVisited = new boolean[N+1];
			isVisited[i] = true;
			q.offer(i);
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (int j=0; j<nodes.get(cur).size(); j++) {
					int next = nodes.get(cur).get(j);
					if (!isVisited[next]) {
						isVisited[next] = true;
						nums[next] += 1;
						nums[i]++;
						q.offer(next);
					}
				}
			}
			
		}
		
		int ans = 0;
		for (int i=1; i<N+1; i++) {
			if (nums[i] == N-1) ans++;
		}
		System.out.println(ans);
	}

}