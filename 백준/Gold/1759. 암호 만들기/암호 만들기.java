import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static String moum = "aeiou";
	static int L, C, sumOfVowel;
	static char[] alpha;
	static int[] vowel = new int[5];
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder ans = new StringBuilder();
	
	static void unCheckVowel(int index) {
		if (alpha[index] == 'a') vowel[0]++;
		else if (alpha[index] == 'e') vowel[1]++;
		else if (alpha[index] == 'i') vowel[2]++;
		else if (alpha[index] == 'o') vowel[3]++;
		else if (alpha[index] == 'u') vowel[4]++;
	}
	
	static void checkVowel(int index) {
		if (alpha[index] == 'a') vowel[0]--;
		else if (alpha[index] == 'e') vowel[1]--;
		else if (alpha[index] == 'i') vowel[2]--;
		else if (alpha[index] == 'o') vowel[3]--;
		else if (alpha[index] == 'u') vowel[4]--;
	}
	
	static void backTracking(int count, int index) {
		if (count == L) {
			
			int sumTemp = 0;
			for (int i=0; i<5; i++) sumTemp += vowel[i];
			
			// 모음 한개 이상 사용
			int numberOfUsedVowel = sumOfVowel - sumTemp;
			int numberOfUsedCon = L - numberOfUsedVowel;
			
			if(numberOfUsedVowel >= 1 && numberOfUsedCon >= 2) {
				ans.append(sb + "\n");
			}
			
		}
		
		for (int i=index; i<alpha.length;  i++) {
			if (!isVisited[i]) {
				checkVowel(i);
				isVisited[i] = true;
				sb.append(alpha[i]);
				backTracking(count+1, i);
				unCheckVowel(i);
				isVisited[i] = false;
				sb.setLength(sb.length()-1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new char[C];
		isVisited = new boolean[C];
		
//		char[] charArray = br.readLine().replace(" ", "").toCharArray();
		
		String str = br.readLine();
		int index = 0;
		for (int i=0; i<str.length(); i++) {
			char input = str.charAt(i);
			if (input != ' ') {
				if (input == 'a') {vowel[0] = 1; sumOfVowel+=1;}
				else if (input == 'e') {vowel[1] = 1; sumOfVowel+=1;}
				else if (input == 'i') {vowel[2] = 1; sumOfVowel+=1;}
				else if (input == 'o') {vowel[3] = 1; sumOfVowel+=1;}
				else if (input == 'u') {vowel[4] = 1; sumOfVowel+=1;}
				
				alpha[index++] = str.charAt(i);
			}
		}
		
		Arrays.sort(alpha);
		
		backTracking(0, 0);
		
		System.out.println(ans);
	}
	
	static void printAlpha() {
		for (int i=0; i<alpha.length; i++) {
			System.out.print(alpha[i] + " ");
		}
	}

}