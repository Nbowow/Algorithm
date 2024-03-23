import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Virus {
        int row, col;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] tempMap;
    static List<Virus> v = new ArrayList<>();
    static List<Virus> comb = new ArrayList<>();
    static boolean[] dfsVisited;
    static boolean[][] bfsVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs() {
        Queue<Virus> q = new ArrayDeque<>();
        bfsVisited = new boolean[N][N];
        tempMap = copyMap();

        for (int i = 0; i < comb.size(); i++) {
            q.offer(comb.get(i));
            bfsVisited[comb.get(i).row][comb.get(i).col] = true;
        }

        int time = 0;

        while (!q.isEmpty()) {
            int qSize = q.size();

            // 1초가 지날 때마다 q에 있는 모든 바이러스 퍼져야함
            for (int s = 0; s < qSize; s++) {
                Virus cur = q.poll();
                int x = cur.row;
                int y = cur.col;

                for (int i = 0; i < 4; i++) {
                    int dx = x + dxdy[i][0];
                    int dy = y + dxdy[i][1];

                    if (dx<0 || dx>=N || dy<0 || dy>=N || bfsVisited[dx][dy]) continue;

                    bfsVisited[dx][dy] = true;
                    if (tempMap[dx][dy] == 0) {
                        tempMap[dx][dy] = 3;
                        q.offer(new Virus(dx, dy));
                    }
                    // 이미 바이러스가 있는 지역일 경우 해당 구역으로부터 사방에 빈칸이 있을 경우 큐에넣고
                    // 그렇지 않을 경우 넣지 않음
                    else if (tempMap[dx][dy] == 2) {
                        go:
                        for (int j = 0; j < 4; j++) {
                            int nx = dx;
                            int ny = dy;
                            while (true) {
                                nx += dxdy[j][0];
                                ny += dxdy[j][1];

                                if (nx<0 || nx>=N || ny<0 || ny>=N || bfsVisited[nx][ny]) break;

                                if (tempMap[nx][ny] == 0) {
                                    tempMap[dx][dy] = 3;
                                    q.offer(new Virus(dx, dy));
                                    break go;
                                }
                            }

                        }
                    }
                 }
            }
            time++;
        }
        // 모든 바이러스가 퍼졌는지 확인
        if (!check()) return;

        time--; // 마지막에 큐에 담기는 바이러스는 퍼지지 못하지만 시간이 흐름
        ans = Math.min(ans, time);

    }

    static void dfs(int count, int idx) {

        // M개의 조합 뽑은 후 bfs(바이러스 퍼뜨림)
        if (count == M) {
            bfs();
        }

        for (int i = idx; i < v.size(); i++) {
            if (!dfsVisited[i]) {
                dfsVisited[i] = true;
                comb.add(v.get(i));
                dfs(count + 1, i);
                dfsVisited[i] = false;
                comb.remove(comb.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        int empty = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    v.add(new Virus(i, j));
                }
                if (map[i][j] == 0) empty++;
            }
        }

        if (empty == 0) System.out.println(0);
        else {
            dfsVisited = new boolean[v.size()];
            // 바이러스 조합 뽑기
            dfs(0, 0);

            if (ans != Integer.MAX_VALUE) System.out.println(ans);
            else System.out.println(-1);
        }

    }

    static int[][] copyMap() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        return temp;
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 한번도 못간 곳이 있다면 바이러스가 다 퍼지지 못함
                if (tempMap[i][j] == 0) return false;
            }
        }
        return true;
    }
}