import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String T = br.readLine();
		String P = br.readLine();
		
		int count = 0;
		int[] table = new int[P.length()];
		
		int j=0;
		// 실패 함수 제작
		// index = 0 일때는 0이므로, index 1부터 검사
		for (int i=1; i<P.length(); i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = table[j-1];
			}
			if (P.charAt(i) == P.charAt(j)) {
				table[i] = ++j; 
			}
		}
		
		// T중간에 P가 위치하는 모든 위치 찾기
		StringBuilder sb = new StringBuilder();
		j=0;
		for (int i=0; i<T.length(); i++) {
			while (j>0 && T.charAt(i) != P.charAt(j)) {
				j = table[j-1];
			}
			
			if (T.charAt(i) == P.charAt(j)) {
				if (j == P.length()-1) {
					// 정답
					count++;
					sb.append((i+1 - (P.length() - 1)) + " ");
					j = table[j];
				}
				else j++;
			}
		}
		
		System.out.println(count);
		System.out.println(sb);
	}

}