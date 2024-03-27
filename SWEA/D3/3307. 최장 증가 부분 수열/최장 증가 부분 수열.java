import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int binarySearch(int left, int right, int target) {
		
		int mid;
		
		while (left < right) {
			mid = (left + right) / 2; // 중간값 설정
			
			if (lis[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}
	
	static int[] lis;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];
			lis = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			lis[0] = nums[0];
			int idx = 0;
			for (int i=1; i<N; i++) { // 모든 수에 대해
				
				if (lis[idx] < nums[i]) {
					lis[++idx] = nums[i];
				}
				
				else { // 이분탐색으로 숫자가 들어갈 위치 찾음
					int pos = binarySearch(0, idx, nums[i]);
					lis[pos] = nums[i];
				}
			}
			int ans = 0;
			for (int i=0; i<N; i++) {
				ans = i;
				if (lis[i] == 0) break;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}