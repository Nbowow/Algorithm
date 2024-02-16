import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] S = new long[30];
		S[0] = 3;
		for (int i=1; i<S.length-1; i++) {
			S[i] = S[i-1] * 2 + (i+3);
		}
        
		int N = Integer.parseInt(br.readLine());

xx:		for (int startIndex=0; startIndex<S.length-1; startIndex++) {
			if (N<S[startIndex]) {
				for (int i=startIndex-1; i>=0; i--) {
					if (N > S[i]) {
						N -= S[i];
						if (N < i+4) break xx;
						N -= (i+4);
					}
				}
			}
		}
		System.out.println(N == 1 ? 'm' : 'o');
	}
}