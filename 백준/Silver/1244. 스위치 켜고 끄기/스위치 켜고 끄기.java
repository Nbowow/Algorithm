import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer st;
	static int[] switchs;
	static int[][] students;
	static StringBuilder sb = new StringBuilder();
	
	static void checkSwtich(int mid) {
		// 스위치 바꿈
		switchs[mid] = switchs[mid] == 1 ? 0 : 1;
		
		int bf = mid;
		int af = mid;
		while(true) {
			bf--;
			af++;
			if (bf > 0 && af < switchs.length) {
				if (switchs[bf] == switchs[af]) {
					// 스위치 바꿈
					switchs[bf] = switchs[bf] == 1 ? 0 : 1;
					
					// 스위치 바꿈
					switchs[af] = switchs[af] == 1 ? 0 : 1;
					continue;
				}
				break;
			} else break;
		}
	}
	
	private static void switchMan(int idx) {
		for (int j=idx; j < switchs.length; j+=idx) {
			switchs[j] = switchs[j] == 1 ? 0 : 1;
		}
	}
	private static void print() {
		for(int i = 1; i < switchs.length; i++) {
			System.out.print(switchs[i] + " " );
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int numSwitch = Integer.parseInt(br.readLine());
		switchs = new int[numSwitch+1];
		st = new StringTokenizer(br.readLine());
		// 인덱스 1번부터 switch 저장
		for (int i=1; i<=numSwitch; i++) {
			switchs[i] = Integer.parseInt(st.nextToken());
		}
		
		int numStudent = Integer.parseInt(br.readLine());
		students = new int[numStudent][2];
		for (int i=0; i<numStudent; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == 1) {
				switchMan(b);
			}else {
				checkSwtich(b);
			}
		}
		
		for(int i = 1 ; i <= numSwitch; i++) {
			System.out.print(switchs[i] + " ");
			if(i % 20 == 0) {
				System.out.println();
			}
		}

	}

}