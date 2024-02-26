import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class task {
		int duration;
		int score;
		
		public task(int duration, int score) {
			this.duration = duration;
			this.score = score;
		}
	}

	static int N, ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Stack<task> stack = new Stack<>();
		
		N = Integer.parseInt(br.readLine());
		
		// N개의 커맨드 겸 시간
		for (int t=1; t<=N; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			
			// 새로운 업무 부여
			if (x == 1) {
				int score = Integer.parseInt(st.nextToken());
				int du = Integer.parseInt(st.nextToken());
				
				// 한번의 일로 끝나지 않을 경우
				if (du > 1) stack.push(new task(du-1, score));
				// 1분만에 끝나는 일일 경우
				else ans+=score;
			}
			
			//새로운 업무 부여x
			else if (x == 0) {
				// 해야할 업무가 없을 경우
				if (stack.isEmpty()) continue;
				
				task temp = stack.pop();
				
				// 아직 일처리가 끝나지 않았을 경우
				if (temp.duration - 1 > 0) stack.push(new task(temp.duration-1, temp.score));
				// 일처리가 끝났을 경우
				else ans+=temp.score;
			}
		}
		
		System.out.println(ans);
	}

}