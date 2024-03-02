import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class MST implements Comparable<MST> {
        int from, to;
        int weight;

        public MST(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(MST o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static class Island {
        int row, col;

        public Island(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] minDist;
    static List<List<Island>> islands = new ArrayList<>();
    static int[] parents;
    static List<MST> mst = new ArrayList<>();

    static void makeIsland(int islandNum, int row, int col) { // bfs 돌면서 섬 번호 마킹

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        map[row][col] = islandNum;
        islands.get(islandNum).add(new Island(row, col));

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    if (map[dx][dy] == 1) {
                        q.offer(new int[]{dx, dy});
                        map[dx][dy] = islandNum;
                        islands.get(islandNum).add(new Island(dx, dy));
                    }
                }

            }
        }
    }

    static void findMinDist(int islandNum, int row, int col) {
//        System.out.println("findMis : " + islandNum + " " + row + " " + col);
        // 상
        int topD = 0;
        for (int i = row - 1; i >= 0; i--) {
            // 자신과 같은 섬일 경우 검사할 필요 x
            if (map[i][col] == islandNum) break;
            // 새로운 섬을 만났고, 거리가 2이상일 경우
            if (map[i][col] > 0) {
                if (topD >= 2) {
                    // 최소 거리값 갱신
                    minDist[islandNum][map[i][col]] = Math.min(minDist[islandNum][map[i][col]], topD);
                }
                break;
            }
            topD++;
        }

        // 하
        int downD = 0;
        for (int i = row + 1; i < N; i++) {
            if (map[i][col] == islandNum) break;

            if (map[i][col] > 0) { // 새로운 섬 만났을 때 거리가 2이상이면 최솟값 갱신 아니면 탈출
                if (downD >= 2) {
                    // 최소 거리값 갱신
                    minDist[islandNum][map[i][col]] = Math.min(minDist[islandNum][map[i][col]], downD);
                }
                break;
            }
            downD++;
        }

        // 좌
        int leftD = 0;
        for (int j = col - 1; j >= 0; j--) {
            if (map[row][j] == islandNum) break;

            if (map[row][j] > 0) {
                if (leftD >= 2) {
                    minDist[islandNum][map[row][j]] = Math.min(minDist[islandNum][map[row][j]], leftD);
                }
                break;
            }
            leftD++;
        }

        // 우
        int rightD = 0;
        for (int j = col + 1; j < M; j++) {
            if (map[row][j] == islandNum) break;

            if (map[row][j] > 0) {
                if (rightD >= 2) {
                    minDist[islandNum][map[row][j]] = Math.min(minDist[islandNum][map[row][j]], rightD);
                }
                break;
            }
            rightD++;
        }
    }



    // i섬에서 j섬으로 갈 수 있는지 확인
    static void canReach() {
        for (int i = 0; i < islands.size(); i++) { // i섬의 좌표 정보가 담김
            for (int j = 0; j < islands.get(i).size(); j++) { // i섬의 모든 좌표에 대해
                Island temp = islands.get(i).get(j);

                int row = temp.row;
                int col = temp.col;
                // 상 하 좌 우 쭉 탐색해서 섬 있는지 확인 후 거리값 갱신
                findMinDist(i, row, col);

            }
        }

    }

    static int find(int x) {
        if (x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parents[pb] = pa;
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        // island.get(1) : 1번섬이 위치한 좌표 정보
        for (int i = 0; i < 7; i++) {
            islands.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    makeIsland(islandNum++, i, j);
                }
            }
        }

        // (i, j) : i섬에서 j 섬으로 가는 최소 거리
        minDist = new int[islandNum][islandNum];
        for (int i = 0; i < minDist.length; i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }

        canReach();

        // union - find
        for (int i = 0; i < minDist.length; i++) {
            for (int j = 0; j < minDist.length; j++) {
                if (minDist[i][j] != Integer.MAX_VALUE) {
                    mst.add(new MST(i, j, minDist[i][j]));
                }
            }
        }
        // 가중치 기준 오름차순 정렬
        Collections.sort(mst);

        parents = new int[islandNum]; // 0, 1, 2, 3, 4
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < mst.size(); i++) {
            MST m = mst.get(i);
            // union 해야 하면 간선 추가
            if (union(m.from, m.to)) {
                ans += mst.get(i).weight;
            }
        }

        // 모든 노드들(섬)이 연결되었으면 모든 노드들의 부모는 하나로 동일 해야함! => 자신이 부모인 노드 하나만 존재
        int cnt = 0;
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == i) cnt++;
        }
        if (cnt>=2) ans = 0;

        // ans == Max 값이면 -1 출력
        System.out.println(ans != 0 ? ans : -1);

    }

    static void printMap(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}