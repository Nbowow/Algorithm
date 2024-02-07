import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	
	static int N;
	static PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>() {
		// 절댓값이 같을경우 비교방법을 재정의 해주기 위함
		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[0] ==  o2[0]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		}
	});

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			int inputNum = Integer.parseInt(br.readLine());
			if (inputNum == 0) {
				// pq가 비어있으면 0출력
				if (pq.isEmpty()) sb.append(0 + "\n");
				else {
					int[] temp = pq.poll();
					// 양수이면 양의 값이, 음수이면 음의 값을 출력하기 위함
					sb.append(temp[0]*temp[1] + "\n");
				}
				continue;
			}
			
			// 입력받는 수가 양수이면 1 음수이면 -1을 배열 두번째 인덱스에 입력
			if (inputNum > 0) {
				pq.offer(new int[] {inputNum, 1});
			} else pq.offer(new int[] {Math.abs(inputNum), -1});
			
		}
		System.out.println(sb);
	}
}