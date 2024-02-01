import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
	작성자 : 남보우
	문제 : [S2] 도영이가 만든 맛있는 음식 - 2961번
	제출 : 2024년 2월 1일
	결과 : 통과
	성능 요약 : 메모리 14212KB, 시간 120ms
	소요 시간 : 35분
	아이디어 : 비트마스킹과 완전탐색을 이용하여 풀었습니다.
			모든 경우의 수를 다 탐색하여 최적의 해를 구하였습니다.
*/
public class Main {
	static int N;
	static StringTokenizer st;
	static int[][] cook;
	static int[] isSelected;
	
	// ans 초기화
	static int ans = (int) 10e9;
//	static List<Integer> ans = new ArrayList<>();
	
	
	static void calTaste(int count) {
		if (count == N) {
			// 신맛(곱셈) 초기화 값 1
			int multemp = 1;
			// 쓴맛(덧셈) 초기화 값 0
			int plustemp = 0;
			// 선택된 수가 아예 없을 경우 계산을 막기 위한 flag
			int flag = 0;
			for (int i=0; i<N; i++) {
				// 선택된 수만 계산
				if (isSelected[i] == 1) {
					multemp *= cook[i][0];
					plustemp += cook[i][1];
					flag = 1;
				}
			}
			// 신맛과 쓴맛의 차이
			if (flag == 1) ans = Math.min(ans, multemp-plustemp>0?multemp-plustemp:plustemp-multemp);
//			if (flag == 1) ans.add(multemp-plustemp>0?multemp-plustemp:plustemp-multemp);
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
		// 신맛과 쓴맛의 차이 구하기
		calTaste(0);
		// 최솟값을 찾기 위한 정렬
//		Collections.sort(ans);
//		System.out.println(ans.get(0));
		System.out.println(ans);
	}

}