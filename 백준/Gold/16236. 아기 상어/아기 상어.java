import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Fish{
        int row;
        int col;
        // 아기상어와의 거리
        int dist;

        public Fish(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

    }

    static int N;
    static int shark_size, shark_row, shark_col;
    // 아기상어가 물고기 먹은 갯수
    static int eatNum;
    static int ans;
    static int[][] map;
    // 상 좌 우 하 (순)
    static int[][] dxdy = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static PriorityQueue<Fish> pq;
    static Queue<Fish> q;


    // 먹어야 하는 물고기 저장해두기
    static void findFish() {

        boolean[][] distVisited = new boolean[N][N];

        q = new ArrayDeque<>();
        q.offer(new Fish(shark_row, shark_col, 0));
        distVisited[shark_row][shark_col] = true;

        while (!q.isEmpty()) {
            Fish temp = q.poll();
            int x = temp.row;
            int y = temp.col;
            int dist = temp.dist;

            for (int i=0; i<4; i++) {
                int dx = x + dxdy[i][0];
                int dy = y + dxdy[i][1];

                if (dx>=0 && dx < N && dy>=0 && dy<N) {
                   if (!distVisited[dx][dy] && map[dx][dy] <= shark_size) {
                        distVisited[dx][dy] = true;
                        
                        if (map[dx][dy] != 0 && map[dx][dy] < shark_size) {
                            // 물고기
                            pq.offer(new Fish(dx, dy, dist + 1));
                        }
                        // 지나가야 함
                        q.offer(new Fish(dx, dy, dist + 1));
                   }
                }
            }
        }
    }

    static void goEat() {

        while(true) {
            q = new ArrayDeque<>();
            pq = new PriorityQueue<>(new Comparator<Fish>() {
                @Override
                public int compare(Fish o1, Fish o2) {
                    if (o1.dist == o2.dist) {
                        // row가 동일할경우 col값이 작은 좌표가 우선순위 더 높음
                        if (o1.row == o2.row) return Integer.compare(o1.col, o2.col);

                        // 아기상어와의 거리가 동일할 경우 row값이 작은 좌표가 우선순위 더 높음
                        return Integer.compare(o1.row, o2.row);
                    }
                    // 아기상어와의 거리 기준 오름차순
                    return Integer.compare(o1.dist, o2.dist);
                }
            });
            
            findFish();

            // 더이상 먹을 물고기가 없을 시 끝
            if (pq.isEmpty()) return;
            
            // 현재 먹은 물고기
            Fish eat = pq.poll();
            ans += eat.dist;

            // 상어가 원래 위치한 곳 빈칸으로 조정
            map[shark_row][shark_col] = 0;

            // 상어 위치 조정
            map[eat.row][eat.col] = 9;
            shark_row = eat.row;
            shark_col = eat.col;


            // 먹은 물고기 갯수 +1
            if (++eatNum == shark_size) {
                // 먹은 물고기 갯수 초기화
                eatNum = 0;
                // 상어 크기 +1
                shark_size++;
            }

        }
        
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 상어 초기 위치
                if (map[i][j] == 9) {
                    shark_row = i;
                    shark_col = j;
                }
            }
        }

        // 상어 초기 사이즈 값
        shark_size = 2;

        goEat();

        System.out.println(ans);

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