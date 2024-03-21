import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Integer.*;
public class Main {
	

	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			String [] input = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				pq.add(parseInt(input[j]));
				if(i!=0) pq.poll();

			}
		}
		System.out.println(pq.peek());

		
	}
}