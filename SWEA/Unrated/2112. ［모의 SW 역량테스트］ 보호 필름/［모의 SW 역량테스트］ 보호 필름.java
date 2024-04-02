import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static class Film {
		int idx, color;
		public Film(int idx, int color) {
			this.idx = idx;
			this.color = color;
		}
	}

	static int ans;
	static int D, W, K;
	static int[][] film;
	static int count;
	static List<Film> comb;
	
	static void insert(int idx, List<Film> list) {
		
		if (idx == list.size()) {
			
			int[][] temp = copyFilm();
			
			for (int i=0; i<list.size(); i++) {
				for (int j=0; j<W; j++) {
					temp[list.get(i).idx][j] = list.get(i).color;
				}
			}
			
			// 성능검사
			if (check(temp)) {
				ans = list.size();
			}
			
			return;
		}
		
		// A약품 투입
		list.get(idx).color = 0;
		insert(idx + 1, list);
		
		// B약품 투입
		list.get(idx).color = 1;
		insert(idx + 1, list);

	}
	
	static void dfs(int idx, int depth) {
		if (ans != Integer.MAX_VALUE) return;
		
		if (depth == count) {
			// 답 가능한지 확인
			insert(0, comb);
			
			return;
		}
		
		// 조합 뽑기
		for (int i=idx; i<D; i++) {
			comb.add(new Film(i, -1));
			dfs(i + 1, depth + 1);
			comb.remove(comb.size() - 1);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			comb = new ArrayList<>();
			
			for (int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			if (check(film)) ans = 0;
			
			for (int i=0; i<D; i++) {
				if (ans != Integer.MAX_VALUE) break;
				count = i+1;
				dfs(0, 0);
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int[][] copyFilm() {
		int[][] temp = new int[D][W];
		for (int i=0; i<D; i++) temp[i] = film[i].clone();
		return temp;
	}
	
	static boolean check(int[][] temp) {
		// 열 위주로 검사
		for (int j=0; j<W; j++) {
			int count = 1;
			for (int i=1; i<D; i++) {
				if (temp[i][j] == temp[i-1][j]) count++;
				else count = 1;
				
				if (count == K) break;
			}
			
			if (count < K) return false;
		}
		
		return true;
	}
	
	

}