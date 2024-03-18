import java.util.*;
import java.io.*;

class Solution {
    
    // 현재 위치 좌표 값 저장
    static class Miro {
        int row, col, count;
        String route;
        
        public Miro (int row, int col, int count, String route) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.route = route;
        }
    }
    
    // 하 : d, 왼 : l, 오 : r, 위 : u
    static int[][] dxdy = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    // 좌표
    static boolean[][][] isVisited;
    static int[][] map;
    static int[][] parents;
    static int N, M, X, Y, R, C, K;
    static boolean canReach;
    static String ansRoute;
    
    
    static void bfs() {
        
        Queue<Miro> q = new ArrayDeque<>();
        q.offer(new Miro(X, Y, 0, ""));
        isVisited[X][Y][0] = true;
        
        while (!q.isEmpty()) {
            Miro cur = q.poll();
            
            int x = cur.row;
            int y = cur.col;
            int c = cur.count;
            
            // 정답
            if (x == R && y == C && c == K) {
                canReach = true;
                ansRoute = cur.route;
                return;
            }
            
            if (c >= K) continue; // 불가능
            
            for (int i=0; i<4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];
                
                if (dx<0 || dx >=N || dy<0 || dy>=M) continue;
                
                
                if (!isVisited[dx][dy][c+1]) {
                    isVisited[dx][dy][c+1] = true;
                    
                    String temp = "";
                    if(i == 0) temp = "d";
                    else if (i == 1) temp = "l";
                    else if (i == 2) temp = "r";
                    else temp = "u";

                    q.offer(new Miro(dx, dy, c+1, cur.route + temp));
                }
                
            }
        }

        
        
        
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        X = x-1;
        Y = y-1;
        R = r-1;
        C = c-1;
        K = k;
        
        isVisited = new boolean[n][m][k+1];
        map = new int[n][m];
        
        // 시작 점
        map[X][Y] = 1;
        // 도착 점
        map[R][C] = 2;
        
        bfs();
        
        String answer = "";
        
        if (!canReach) answer = "impossible";
        else {
            answer = ansRoute;
        }
        
        
        return answer;
    }
}

