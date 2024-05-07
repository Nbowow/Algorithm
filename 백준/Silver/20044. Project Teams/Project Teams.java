import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] w = new int[n*2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n*2; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(w);
		
		int ans = Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			ans = Math.min(ans, w[i] + w[n*2-1-i]);
		}
		
		System.out.println(ans);
	}

}