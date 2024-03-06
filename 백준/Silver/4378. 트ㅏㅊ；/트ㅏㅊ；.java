import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		HashMap<Character, Character> map = new HashMap<>();
		// 1번째 줄
		map.put('1', '`'); map.put('2', '1'); map.put('3', '2'); map.put('4', '3');
		map.put('5', '4'); map.put('6', '5'); map.put('7', '6'); map.put('8', '7');
		map.put('9', '8'); map.put('0', '9'); map.put('-', '0'); map.put('=', '-');
		
		// 2번째 줄
		map.put('W', 'Q'); map.put('E', 'W'); map.put('R', 'E'); map.put('T', 'R');
		map.put('Y', 'T'); map.put('U', 'Y'); map.put('I', 'U'); map.put('O', 'I');
		map.put('P', 'O'); map.put('[', 'P'); map.put(']', '['); map.put('\\', ']');
		
		// 3번째 줄
		map.put('S', 'A'); map.put('D', 'S'); map.put('F', 'D'); map.put('G', 'F');
		map.put('H', 'G'); map.put('J', 'H'); map.put('K', 'J'); map.put('L', 'K');
		map.put(';', 'L'); map.put('\'', ';');
		
		// 4번째 줄
		map.put('X', 'Z'); map.put('C', 'X'); map.put('V', 'C'); map.put('B', 'V');
		map.put('N', 'B'); map.put('M', 'N'); map.put(',', 'M'); map.put('.', ',');
		map.put('/', '.');
		
		while ((str = br.readLine()) != null) {
			char[] temp = str.toCharArray();
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<temp.length; i++) {
				if (temp[i] == ' ') sb.append(temp[i]);
				else sb.append(map.get(temp[i]));
			}
			
			System.out.println(sb);
		}
		
	}

}