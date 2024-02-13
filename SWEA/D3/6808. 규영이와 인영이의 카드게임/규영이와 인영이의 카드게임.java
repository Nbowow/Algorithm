import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
	@author : 남보우
	문제 : [D3] 햄버거 다이어트 -5215번
	제출 : 2024년 2월 8일
	결과 : 통과
	소요 시간 : 10분
	아이디어 : 과일들을 정렬한 후 
*/
public class Solution {
	
	static int win, lose;
	static List<Integer> gyuCard;
	static List<Integer> inCard;
	static int totalScore;
	static boolean[] isVisited;
	static int[] perm;
	
	static void dfs(int depth) {
		
		if (depth == 9) {
			int gyuPoints = 0;
			int inPoints = 0;
			for (int i=0; i<9; i++) {
				// 규영이가 더 수가 클경우
				if (gyuCard.get(i) > inCard.get(perm[i])) {
					gyuPoints += gyuCard.get(i) + inCard.get(perm[i]);
				}
				else inPoints += gyuCard.get(i) + inCard.get(perm[i]);
			}
			
			if (gyuPoints > inPoints) win++;
			else lose++;
		}
		
			
		for (int i=0; i<9; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				perm[depth] = i;
				dfs(depth + 1);
				isVisited[i] = false;
			}
		}
			
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			win = 0;
			lose = 0;
			totalScore = 0;
			isVisited = new boolean[9];
			perm = new int[9];
			gyuCard = new ArrayList<>();
			inCard = new ArrayList<>();
			for (int i=0; i<9; i++) {
				gyuCard.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i=1; i<=18; i++) {
				if (!gyuCard.contains(i)) {
					inCard.add(i);
				}
			}
			
//			printArray();
			
			
			dfs(0);
			
			System.out.println("#" + tc + " " + win + " " + lose);
			
			
		}
	}
	
	static void printArray() {
		for (int i=0; i<9; i++) {
			System.out.print(gyuCard.get(i) + " ");
		}
		System.out.println();
		
		for (int i=0; i<9; i++) {
			System.out.print(inCard.get(i) + " ");
		}
	}

	

}