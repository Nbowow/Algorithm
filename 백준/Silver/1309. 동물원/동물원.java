import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[][] animal;
	static int[] ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		animal = new int[N][3];
		ans = new int[N];
		// 0행 0열에 동물 위치
		animal[0][0] = 1;
		// 0행 1열에 동물 위치
		animal[0][1] = 1;
		// 0행에 동물 X
		animal[0][2] = 1;
		ans[0] = 3;
		for (int i=1; i<N; i++) {
			animal[i][0] = (animal[i-1][1] + animal[i-1][2]) % 9901;
			animal[i][1] = (animal[i-1][0] + animal[i-1][2]) % 9901;
			animal[i][2] = (animal[i-1][0] + animal[i-1][1] + animal[i-1][2]) % 9901;
			ans[i] = (animal[i][0] + animal[i][1] + animal[i][2]) % 9901;
		}
		
		System.out.println(ans[N-1]);
		
	}

}