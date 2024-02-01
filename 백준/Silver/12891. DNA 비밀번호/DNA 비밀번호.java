import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int S, P, ans;
	static Character c;
	static String dna;
	static int[] acgt = new int[4];
	static int[] isSelected = new int[4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		isSelected = new int[S];
		P = Integer.parseInt(st.nextToken());
		
		dna = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			acgt[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] temp = Arrays.copyOf(acgt, 4);
		for (int i=0; i<P; i++) {
			if (dna.charAt(i) == 'A') {
				temp[0]--;
			} else if (dna.charAt(i) == 'C'){
				temp[1]--;
			} else if (dna.charAt(i) == 'G') {
				temp[2]--;
			} else {
				temp[3]--;
			}
		}
		int flag = 0;
		for (int i=0; i<4; i++) {
			if (temp[i] > 0) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) ans += 1;
		
		for (int i=P; i<S; i++) {
			if (dna.charAt(i) == 'A') {
				temp[0]--;
			} else if (dna.charAt(i) == 'C'){
				temp[1]--;
			} else if (dna.charAt(i) == 'G') {
				temp[2]--;
			} else {
				temp[3]--;
			}
			
			int numTemp = i-P;
			if (dna.charAt(numTemp) == 'A') {
				temp[0]++;
			} else if (dna.charAt(numTemp) == 'C'){
				temp[1]++;
			} else if (dna.charAt(numTemp) == 'G') {
				temp[2]++;
			} else {
				temp[3]++;
			}
			
			flag = 0;
			for (int j=0; j<4; j++) {
				if (temp[j] > 0) {
					flag = 1; 
					break;
				}
			}
			if (flag == 0) ans += 1;
		}
		System.out.println(ans);
	}

}