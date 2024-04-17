import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] parents;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int pa = parents[a];
        int pb = parents[b];

        if (pa == pb) return false;

        parents[pa] = find(pb);
        return true;
    }
    static void dfs(int row, int col) {
        isVisited[row][col] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 해당하는 방향으로 이동
            int dx = x + dxdy[map[x][y]][0];
            int dy = y + dxdy[map[x][y]][1];

            if (!isIn(dx, dy)) break;

            // union-find
            union(x*M + y, dx*M + dy);
            if (isVisited[dx][dy]) break;

            isVisited[dx][dy] = true;
            q.offer(new int[]{dx, dy});

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];
        parents = new int[N * M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                parents[i*M + j] = i*M + j;
                switch (str.charAt(j)) {
                    case 'U' : map[i][j] = 0;
                        break;
                    case 'D' : map[i][j] = 1;
                        break;
                    case 'L' : map[i][j] = 2;
                        break;
                    case 'R' : map[i][j] = 3;
                        break;
                }
            }
        }

        // union-find 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j]) {
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < parents.length; i++) {
            find(i);
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(parents[i*M + j] + " ");
//            }
//            System.out.println();
//        }

        HashSet<Integer> ans = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans.add(parents[i * M + j]);
            }
        }

        System.out.println(ans.size());
    }
    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}