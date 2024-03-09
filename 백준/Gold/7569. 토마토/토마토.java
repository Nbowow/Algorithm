import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	@author : 남보우
	문제 : [G5] 토마토 - 7569번
	제출 : 2024년 3월 9일
	결과 : 통과
	성능 요약 : 메모리 101812KB, 시간 576ms
	아이디어 : bfs
	    7576의 토마토문제의 3차원 버전입니다.
	    상, 하, 좌, 우 + 앞, 뒤 까지 고려해주면 쉽게 답을 구할 수 있습니다.
*/
public class Main {

    static class Tomato {
        int height, row, col;

        public Tomato(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, H, ans;
    static int[][][] map;
    static Queue<Tomato> q = new ArrayDeque<>();
    // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
    static int[][] dxdy = {{1, 0, 0}, {-1, 0, 0},
            {0, -1, 0}, {0, 1, 0},
            {0, 0, 1}, {0, 0, -1}};

    static boolean[][][] isVisited;

    static void bfs() {

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int n = 0; n < qSize; n++) {
                Tomato t = q.poll();

                int h = t.height;
                int x = t.row;
                int y = t.col;

                for (int i = 0; i < 6; i++) {
                    int dh = h + dxdy[i][0];
                    int dx = x + dxdy[i][1];
                    int dy = y + dxdy[i][2];

                    if (dh >= 0 && dh < H && dx >= 0 && dx < N && dy >= 0 && dy < M) {
                        if (!isVisited[dh][dx][dy]) {
                            isVisited[dh][dx][dy] = true;

                            if (map[dh][dx][dy] == 0) {
                                q.offer(new Tomato(dh, dx, dy));
                                map[dh][dx][dy] = 1;
                            }
                        }
                    }
                }

            }
            ans += 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        isVisited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        q.offer(new Tomato(i, j, k));
                        isVisited[i][j][k] = true;
                    }
                }
            }
        }

        if (isAllRipen()) System.out.println(0);
        else {
            bfs();

            if (isAllRipen()) System.out.println(ans-1);
            else System.out.println(-1);
        }

    }

    static boolean isAllRipen() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 0) return false;
                }
            }
        }

        return true;
    }

    static void printMap() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(map[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}