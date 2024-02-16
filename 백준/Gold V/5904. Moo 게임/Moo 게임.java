import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static long[] S;
	static Character ans;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		S = new long[30];
		S[0] = 3;
		
		for (int i=1; i<30; i++) {
			S[i] = S[i-1] * 2 + (i+3);
		}
		
//		System.out.println(Arrays.toString(S));

		int startIndex = 0;
		for (int i=0; i<S.length-1; i++) {
			if (N<S[i]) {
				startIndex = i;
				break;
			}
		}
		
		boolean isChecked = false;
		for (int i=startIndex-1; i>=0; i--) {
			if (N > S[i]) {
				N -= S[i];
				if (N < i+4) {
					if (N == 1) ans = 'm';
					else ans = 'o';
					
					isChecked = true;
					break;
				}
				N -= (i+4);
			}
		}
		
		if (!isChecked) {
			if (N == 1) ans = 'm';
			else ans = 'o';
		}

		System.out.println(ans);
		
	}

}