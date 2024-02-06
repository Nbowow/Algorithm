import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc=1; tc<=10; tc++) {
			N = Integer.parseInt(br.readLine());
			
			boolean isValid = true;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String oper = st.nextToken();
				if (isValid) {
					try {
						// 숫자면 뒤에 아무런 토큰 없어야 함
						Integer.parseInt(oper);
						if (st.hasMoreTokens()) {
							isValid = false;
						}
					} catch (Exception e) {
						// 연산자면 뒤에 무조건 숫자 두개
						try {
							// 숫자 두개면 계속 진행
							Integer.parseInt(st.nextToken());
							Integer.parseInt(st.nextToken());
						} catch (Exception e2) {
							// 둘 중에 하나라도 연산자가 나온다면
							isValid = false;
						}
					}
				}
			}
			
			if (isValid) System.out.println("#" + tc + " " + 1);
			else System.out.println("#" + tc + " " + 0);
			
		}
		
	}

}