import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, max, totalCnt, min;
	static int[][] map;
	static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<int[]> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = Integer.MIN_VALUE;
			totalCnt = 0;
			min = Integer.MAX_VALUE;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 비 가장자리코어 리스트에 담기
					if (i>0 && i< N-1 && j>0 && j<N-1 && map[i][j] == 1) {
						list.add(new int[] {i, j});
						totalCnt++;
					}
				}
			}
			
			go(0, 0, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	static void go(int index, int cCnt, int lCnt) { // 현재 코어로 전선처리 시도, cCnt : 코어 갯수, lCnt : 전선 길이의 합
		
		if (cCnt + (totalCnt-index) < max) return; //가지치기
		
		if (index == totalCnt) {
			if (max < cCnt) {
				max = cCnt;
				min = lCnt;
			} else if (max == cCnt) {
				min = Math.min(min, lCnt);
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		
		// 4방향으로 시도
		for (int i=0; i<4; i++) {
			if (isAvailable(r, c, i)) {
				int len = setStatus(r, c, i, 2); // 전선 놓기
				go(index + 1, cCnt + 1, lCnt + len); // 다음 코어 가기
				setStatus(r, c, i, 0); // 전선 지우기
			}
		}
		
		// 전선 놓지 않기
		go(index+1, cCnt, lCnt);
		
	}
	
	static boolean isAvailable(int r, int c, int d) { // r, c 위치에서 d방향으로 전선놓기가 가능한지 체크
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dxdy[d][0];
			nc += dxdy[d][1];
			
			if (nr<0 || nr>=N || nc<0 || nc>=N) return true;
			
			// 코어거나 전선을 만나면 연결 실패
			if (map[nr][nc] > 0) return false;
		}
	}
	
	static int setStatus (int r, int c, int d, int s) { // r, c 위치(코어위치)에서 d방향으로 s(2:전선, 0:빈칸)로 상태 set
		int nr = r;
		int nc = c;
		int cnt = 0;
		
		while(true) {
			nr += dxdy[d][0];
			nc += dxdy[d][1];
			
			if (nr<0 || nr>=N || nc<0 || nc>=N) break;
			
			map[nr][nc] = s;
			cnt++; // 처리한 빈칸의 개수
		}
		return cnt;
	}
	

}