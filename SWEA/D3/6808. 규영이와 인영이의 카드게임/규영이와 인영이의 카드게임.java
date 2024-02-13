import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
	@author : 남보우
	문제 : [D3] 규영이와 인영이의 카드게임 - 6808번
	제출 : 2024년 2월 13일
	결과 : 통과
	소요 시간 : 60분
	아이디어 : 규영이와 인영이의 카드를 입력 받은 후, 순열로 모든 경우의수를 다 계산하였습니다. 
*/
public class Solution {
	
	static int win, lose;
	static List<Integer> gyuCard;
	static List<Integer> inCard;
	static boolean[] isVisited;
	static int[] perm;
	
	static void dfs(int depth) {
		
		if (depth == 9) {
			int gyuPoints = 0;
			int inPoints = 0;
			for (int i=0; i<9; i++) {
				// 규영이의 수가 더 클 경우
				if (gyuCard.get(i) > inCard.get(perm[i])) {
					gyuPoints += gyuCard.get(i) + inCard.get(perm[i]);
				}
				// 인영이의 수가 더 클 경우
				else inPoints += gyuCard.get(i) + inCard.get(perm[i]);
			}
			
			// 규영이의 포인트가 더 크면 규영이 승
			if (gyuPoints > inPoints) win++;
			// 그렇지 않으면 인영이 승
			else lose++;
		}
		
			
		// 순열로 모든 경우의수를 탐색
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
			isVisited = new boolean[9];
			perm = new int[9];
			gyuCard = new ArrayList<>();
			inCard = new ArrayList<>();
			// 입력받은 수 = 규영이의 카드
			for (int i=0; i<9; i++) {
				gyuCard.add(Integer.parseInt(st.nextToken()));
			}
			
			// 1~18까지의 수 중 입력받지 않은 수 인영이의 카드로 저장
			for (int i=1; i<=18; i++) {
				if (!gyuCard.contains(i)) {
					inCard.add(i);
				}
			}
			
			dfs(0);
			
			System.out.println("#" + tc + " " + win + " " + lose);
			
		}
	}
}