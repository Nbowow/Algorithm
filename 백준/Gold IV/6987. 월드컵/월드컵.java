import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean isValid;
	static int[][] teams;
	static int[] t1, t2;
	static List<Integer> matchTeam = new ArrayList<>();
	
	static void backTracking(int matches, int round) {
		// 이미 검증됐으므로 더이상 bt돌필요 없음
		if (isValid) return;
		
		// 15경기 모두 치뤘으면 가능한 경기 결과
		if (matches == 15) {
			isValid = true;
			return;
		}
		
		int team1 = t1[round];
		int team2 = t2[round];
		// 첫번째 팀이 승리
		if (teams[team1][0] > 0 && teams[team2][2] > 0) {
			teams[team1][0]--;
			teams[team2][2]--;
			backTracking(matches + 1, round + 1);
			teams[team1][0]++;
			teams[team2][2]++;
		}
		
		// 무승부
		if (teams[team1][1] > 0 && teams[team2][1] > 0) {
			teams[team1][1]--;
			teams[team2][1]--;
			backTracking(matches + 1, round + 1);
			teams[team1][1]++;
			teams[team2][1]++;
		}
		
		
		// 두번째 팀이 승리
		if (teams[team1][2] > 0 && teams[team2][0] > 0) {
			teams[team1][2]--;
			teams[team2][0]--;
			backTracking(matches + 1, round + 1);
			teams[team1][2]++;
			teams[team2][0]++;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 가능한 모든 경기 수 저장
		t1 = new int[15];
		t2 = new int[15];
		
		int idx = 0;
		for (int i=0; i<5; i++) {
			for (int j=i+1; j<6; j++) {
				t1[idx] = i;
				t2[idx] = j;
				idx++;
			}
		}
		
		for (int tc=0; tc<4; tc++) {
			st = new StringTokenizer(br.readLine());
			isValid = false;
			
			// 첫번째 인덱스 : team , 두번째 인덱스 : (0 : 승리, 1 : 무승부, 2 : 패배)
			teams = new int[6][3];
			matchTeam = new ArrayList<>();
			for (int i=0; i<6; i++) {
				for (int j=0; j<3; j++) {
					teams[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 팀의 승+무+패 = 5인지 확인
			boolean isFiveMatches = true;
			for (int i=0; i<6; i++) {
				int totalMatch = teams[i][0] + teams[i][1] + teams[i][2];
				if (totalMatch!=5) isFiveMatches = false;
			}
			
			if (isFiveMatches)	backTracking(0, 0);
			sb.append(isValid ? 1 + " " : 0 + " ");
		}
		System.out.println(sb);
		
	}

}