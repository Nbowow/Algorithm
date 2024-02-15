import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int H, W, N;
	static char[][] fields;
	
	static void shoot() {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				// 전차가 위 보고 있을 때
				if (fields[i][j] == '^') {
					int index = i;
					while (--index>=0) {
						if (fields[index][j] == '*') {
							fields[index][j] = '.';
							break;
						} else if (fields[index][j] == '#') break;
					}
				}
				
				// 전차가 아래 보고 있을 때
				if (fields[i][j] == 'v') {
					int index = i;
					while (++index<H) {
						if (fields[index][j] == '*') {
							fields[index][j] = '.';
							break;
						} else if (fields[index][j] == '#') break;
					}
				}
				
				// 전차가 왼쪽 보고 있을 때
				if (fields[i][j] == '<') {
					int index = j;
					while (--index>=0) {
						if (fields[i][index] == '*') {
							fields[i][index] = '.';
							break;
						} else if (fields[i][index] == '#') break;
					}
				}
				
				// 전차가 오른쪽 보고 있을 때
				if (fields[i][j] == '>') {
					int index = j;
					while (++index<W) {
						if (fields[i][index] == '*') {
							fields[i][index] = '.';
							break;
						} else if (fields[i][index] == '#') break;
					}
				}
			}
		}
	}
	
	static void move(int del) {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (fields[i][j] == '^' || fields[i][j] == 'v' || fields[i][j] == '<' || fields[i][j] == '>') {
					// 상(U)
					if (del == 1) {
						fields[i][j] = '^';
						if (i-1>=0 && fields[i-1][j] == '.') {
							swap(i-1, j, i, j);
						}
						return;
					}
					
					// 하(D)
					else if (del == 2) {
						fields[i][j] = 'v';
						if (i+1<H && fields[i+1][j] == '.') {
							swap(i+1, j, i, j);
						}
						return;
					}
					
					
					// 좌(L)
					else if (del == 3) {
						fields[i][j] = '<';
						if (j-1>=0 && fields[i][j-1] == '.') {
							swap(i, j-1, i, j);
						}
						return;
					}
					
					
					// 우(R)
					else if (del == 4) {
						fields[i][j] = '>';
						if (j+1<W && fields[i][j+1] == '.') {
							swap(i, j+1, i, j);
						}
						return;
					}
				}
			}
		}
	}
	
	static void doActions(String str) {
		
		for (int i=0; i<str.length(); i++) {
			switch (str.charAt(i)) {
				case 'U': {
					move(1);
					continue;
				}
				case 'D' : {
					move(2);
					continue;
				}
				case 'L' : {
					move(3);
					continue;
				}
				case 'R' : {
					move(4);
					continue;
				}
				case 'S' : {
					shoot();
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			fields = new char[H][W];
			
			for (int i=0; i<H; i++) {
				fields[i] = br.readLine().toCharArray();
			}
			
			N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			doActions(input);
			
			System.out.print("#" + tc + " ");
			printArray();
			
		}
	}
	
	static void printArray() {
		for (int i=0; i<H; i++) {
			for (int j=0 ;j<W; j++) {
				System.out.print(fields[i][j]);
			}
			System.out.println();
		}
	}
	
	static void swap(int fr, int fc, int lr, int lc) {
		char temp = fields[fr][fc];
		fields[fr][fc] = fields[lr][lc];
		fields[lr][lc] = temp;
	}

}