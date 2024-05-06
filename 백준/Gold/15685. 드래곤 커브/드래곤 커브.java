import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Dragon {
        int row, col, dir, ge;

        public Dragon(int row, int col, int dir, int ge) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.ge = ge;
        }
    }

    static int N, S, ans;
    static int[][] map;
    static Dragon[] dragons;
    static int[][] dxdy = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 우 상 좌 하
    static List<Integer>[] curveList;


    static void Game() {
        // 모든 드래곤에 대해 커브
        for (int c = 0; c < N; c++) {
            Dragon dragon = dragons[c];

            // 세대 수 만큼 반복
            for (int i = 0; i <= dragon.ge; i++) {
                if (curveList[c].size() == 0) {
                    curveList[c].add(dragon.dir);
                }
                else {
                    for (int j = curveList[c].size() - 1; j >= 0; j--) {
                        int dir = curveList[c].get(j);
                        curveList[c].add((dir + 1) % 4);
                    }
                }

            }

            // map에 반영
            int x = dragon.row;
            int y = dragon.col;
            map[x][y] = 1;
            for (int i = 0; i < curveList[c].size(); i++) {
                int nextLoc = curveList[c].get(i);

                x += dxdy[nextLoc][0];
                y += dxdy[nextLoc][1];
                map[x][y] = 1;
            }

        }

        // 정사각형의 네 꼭짓점의 모두 드래곤 커브의 일부인 것의 갯수 구하기
        for (int i = 0; i < S-1; i++) {
            for (int j = 0; j < S-1; j++) {
                if (map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1) {
                    ans++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dragons = new Dragon[N];
        curveList = new List[N];
        for (int i = 0; i < N; i++) {
            curveList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int ge = Integer.parseInt(st.nextToken());

            dragons[i] = new Dragon(x, y, dir, ge);
        }

        S = 105; // 맵 크기 설정
        map = new int[S][S];

        Game();
        System.out.println(ans);
    }

    // debug
    static void printMap(int[][] map) {
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}