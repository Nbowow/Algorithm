import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Character> binaryNums = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n*5; i++) {
			String num = Integer.toBinaryString(i);
			for (int j=0; j<num.length(); j++) {
				binaryNums.add(num.charAt(j));
			}
		}
		
		int temp = 0;
		for (int i=0; i<binaryNums.size(); i++) {
			if (i == temp*n+k-1) {
				System.out.print(binaryNums.get(i) + " ");
				if (temp < 4) temp++;
				else break;
			}
		}

	}

}