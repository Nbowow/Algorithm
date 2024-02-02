import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1; tc<=10; tc++) {
			q = new ArrayDeque<>();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
xx:			while(true) {
				for (int i=1; i<=5; i++) {
					if (q.peek()-i <= 0) {
						q.poll();
						q.offer(0);
						break xx;
					}
					q.offer(q.poll()-i);
				}
			}
			System.out.print("#" + tc + " ");
			for (int num : q) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

}