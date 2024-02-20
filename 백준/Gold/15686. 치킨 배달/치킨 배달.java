import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Comb {
        int row;
        int col;
        int dist;

        public Comb(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Comb(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static int N, M;
    static int chickenDist = (int)10e6;
    static int[][] map;
    static boolean[][] isVisited;
    static List<Comb> comb = new ArrayList<>();
    static boolean[][] dfsVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] tempMap;


    static void dfs(int depth, int start) {

        // 치킨집 중 M개를 뽑는 조합 완성 됐을 경우
        if (depth == M) {
            int temp = 0;

            tempMap = new int[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (map[i][j] == 2) tempMap[i][j] = 0;
                    else tempMap[i][j] = map[i][j];
                }
            }

            for (int i=0; i<comb.size(); i++) {
                Comb tcomb = comb.get(i);
                tempMap[tcomb.row][tcomb.col] = 2;
            }

            // 모든 집 치킨거리 더함
            sumdist: for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (tempMap[i][j] == 1) {
                        dfsVisited = new boolean[N][N];
                        temp += bfs(i, j);
                        if (temp > chickenDist) break sumdist;
                    }
                }
            }

            chickenDist = Math.min(chickenDist, temp);
        }

        for (int i=start; i<N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (!isVisited[x][y] && map[x][y] == 2) {
                isVisited[x][y] = true;
                comb.add(new Comb(x, y));
                dfs(depth+1, i);
                isVisited[x][y] = false;
                comb.remove(comb.size()-1);

            }
        }
    }

    static int bfs(int row, int col) {

        Queue<Comb> q = new ArrayDeque<>();
        q.offer(new Comb(row, col, 0));
        dfsVisited[row][col] = true;

        while (!q.isEmpty()) {
            Comb temp = q.poll();
            int trow = temp.row;
            int tcol = temp.col;
            int tdist = temp.dist;

            if (tdist > chickenDist) return tdist;

            for (int i=0; i<4; i++) {
                int dx = trow + dxdy[i][0];
                int dy = tcol + dxdy[i][1];

                if (dx>=0 && dx<N && dy>=0 && dy<N) {
                    if (!dfsVisited[dx][dy] && tempMap[dx][dy] != 2) {
                        dfsVisited[dx][dy] = true;
                        q.offer(new Comb(dx, dy, tdist+1));
                    }

                    else if (tempMap[dx][dy] == 2) {
                        return tdist+1;
                    }
                }

            }
        }

        return 0;

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        isVisited = new boolean[N][N];

        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 치킨집 조합 먼저
        dfs(0, 0);
        System.out.println(chickenDist);
    }

    static void printTemp() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tempMap[i][j] + " ");
            }
            System.out.println();
        }
    }

}