import java.util.*;
import java.io.*;

public class Solution {
	
	static final int MAX_DIST = 1000 * 1000;
    static int N;
    static int[][] map;
   
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc=1; tc<=T; tc++) {
         	StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                	if (map[i][j] == 0 && i!=j) map[i][j] = MAX_DIST;
                }
            }
            
            for (int k=0; k<N; k++) { // 경로
            	for (int i=0; i<N; i++) { // 출발지점
            		for (int j=0; j<N; j++) { // 도착지점
            			map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
            		}
            	}
            }
            
            int ans = Integer.MAX_VALUE;
            for (int i=0; i<N; i++) {
            	int sum = 0;
            	for (int j=0; j<N; j++) {
            		sum += map[i][j];
            	}
            	ans = Math.min(ans, sum);
            }
            
            System.out.println("#" + tc + " " + ans);
            
        }
	}
    
    static void printMap() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
         		   System.out.print(map[i][j] + " ");
            }
        	System.out.println();
        }
    }
}