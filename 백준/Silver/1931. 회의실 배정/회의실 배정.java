import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] confer;
	static List<int[]> ans = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		confer = new int[N][2];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			confer[i][0] = Integer.parseInt(st.nextToken());
			confer[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(confer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
		
		ans.add(confer[0]);
		for (int i=1; i<N; i++) {
			if (ans.get(ans.size()-1)[1] <= confer[i][0]) {
				ans.add(confer[i]);
			}
		}
		
		System.out.println(ans.size());
		
	}

}