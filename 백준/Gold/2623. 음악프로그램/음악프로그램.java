import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] counts;
	static List<List<Integer>> singers = new ArrayList<>();
	static Queue<Integer> ans = new ArrayDeque<>();
	
	static void topology_sort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		// 위상이 0인 노드들(자신에게 들어오는 간선이 없는 노드들)에 대해 큐에 넣어줌
		for (int i=1; i<counts.length; i++) {
			if (counts[i] == 0) q.offer(i);
		}
		
		while (!q.isEmpty()) {
			int index = q.poll();
			
			// 답 큐에 넣기
			ans.offer(index);
			
			for (int i=0; i<singers.get(index).size(); i++) {
				int temp = singers.get(index).get(i);
				counts[temp]--;
				
				if (counts[temp] == 0) q.offer(temp);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i=0; i<N+1; i++) {
			singers.add(new ArrayList<>());
		}
		counts = new int[N+1];
		
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int numberOfSingers = Integer.parseInt(st.nextToken());
			
			int[] tempSinger = new int[numberOfSingers];
			for (int j=0; j<tempSinger.length; j++) {
				tempSinger[j] = Integer.parseInt(st.nextToken());
			}
			
			// 싱어 두명씩 마다
			for (int j=0; j<tempSinger.length-1; j++) {
				singers.get(tempSinger[j]).add(tempSinger[j+1]);
				counts[tempSinger[j+1]]++;
			}
		}
		
		topology_sort();
		
		// 위상 정렬 다 했는데 처리되지 못한 간선이 남아 있을 경우 -> 순서 정하기 불가능
		boolean isValid = true; 
		for (int i=0; i<counts.length; i++) {
			if (counts[i] != 0) isValid = false;
		}
		
		if (isValid) {
			StringBuilder sb = new StringBuilder();
			while (!ans.isEmpty()) {
				sb.append(ans.poll() + "\n");
			}
			System.out.println(sb);
		}
		else System.out.println(0);
	}

}