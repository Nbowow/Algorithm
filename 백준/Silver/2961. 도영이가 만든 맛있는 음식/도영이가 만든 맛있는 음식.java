import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static StringTokenizer st;
	static int[][] cook;
	static int[] isSelected;
	static List<Integer> ans = new ArrayList<>();
	
	
	static void calTaste(int count) {
		if (count == N) {
			int multemp = 1;
			int plustemp = 0;
			int flag = 0;
			for (int i=0; i<N; i++) {
				// 선택해서 계산
				if (isSelected[i] == 1) {
					multemp *= cook[i][0];
					plustemp += cook[i][1];
					flag = 1;
				}
			}
			if (flag == 1) ans.add(multemp-plustemp>0?multemp-plustemp:plustemp-multemp);
			return;
		}
		
		isSelected[count] = 1;
		calTaste(count + 1);
		isSelected[count] = 0;
		calTaste(count + 1);
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cook = new int[N][2];
		isSelected = new int[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<2; j++) {
				cook[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		calTaste(0);
		Collections.sort(ans);
		System.out.println(ans.get(0));
	}

}