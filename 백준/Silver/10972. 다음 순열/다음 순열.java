import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, bindex, aindex;
	static int[] ans;
	static String inputString, result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		ans = new int[N];
		for (int i=0; i<N; i++) {
			ans[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isSwap = false;
		for (int i=ans.length-1; i>0; i--) {
			// 오르막길 끝
			if (ans[i-1] < ans[i]) {
				isSwap = true;
				// i가 맨 마지막 index일 경우
//				if (i == ans.length-1) {
//					swap(i-1, i);
//					break;
//				}
				
				bindex = i-1;
				
				int maxNum = -1;
				// bindex의 위치한 값보다 큰 값중 가장 작은 값 찾음
				for (int j=ans.length-1; j>=i; j--) {
					if (ans[bindex] < ans[j]) {
						maxNum = ans[j];
						aindex = j;
						break;
					}
				}
				
				// 두 수를 스왑
				swap(bindex, aindex);
				
				
				// bindex이후 수들을 오름차순 정렬
				for (int j=0; j<(N-bindex)/2; j++) {
					swap(i + j, N-1-j);
					
				}
				
				break;
			}
		}
		
		if (!isSwap) System.out.println(-1);
		else {
			for (int i=0; i<N; i++) {
				System.out.print(ans[i] + " ");
			}
		}

	}
	
	static void swap(int i, int j) {
		int temp = ans[i];
		ans[i] = ans[j];
		ans[j] = temp;
	
	}

}