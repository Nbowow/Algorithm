import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static Map<String, Integer> pokemon = new HashMap<String, Integer>();
	static Map<Integer, String> pokemon2 = new HashMap<Integer, String>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int i=1; i<=N; i++) {
			String str = br.readLine();
			pokemon.put(str, i);
			pokemon2.put(i, str);
		}
		
		for (int i=0; i<M; i++) {
			String str = br.readLine();
			try {
				// 숫자 입력
				sb.append(pokemon2.get(Integer.parseInt(str)) + "\n");
			} catch (NumberFormatException e) {
				// 문자 입력
				sb.append(pokemon.get(str) + "\n");
			}
		}
		System.out.print(sb);
		
	}

}