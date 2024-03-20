import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Integer[] A = new Integer[N];
		Integer[] B = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) B[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(A, Collections.reverseOrder());
		Arrays.sort(B);
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			ans += A[i] * B[i];
		}
		
		System.out.println(ans);
	}

}