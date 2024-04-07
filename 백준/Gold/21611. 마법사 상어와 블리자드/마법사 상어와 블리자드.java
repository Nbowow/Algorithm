import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Magic {
        int dir, power;

        public Magic(int dir, int power) {
            this.dir = dir;
            this.power = power;
        }
    }
    static class Shark {
        int row, col;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, ans;
    static boolean isClear, isExplosion;
    static int[][] map;
    static Shark shark;
    static Magic[] magic;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
    static int[][] mapNumber;
    static List<int[]> mapPosition = new ArrayList<>();
    static List<Integer> nextMap;

    static void changeMarble() {
        nextMap = new ArrayList<>();

        // A, B 구해서 저장
        int count = 1;
        for (int i = 1; i < mapPosition.size(); i++) {
            int[] position = mapPosition.get(i);
            int x = position[0];
            int y = position[1];

            // 전의 구슬 번호와 같을 때
            if (map[x][y] == map[mapPosition.get(i-1)[0]][mapPosition.get(i-1)[1]]) count++;
            else { // 다를 때
                // A 같은 그룹의 갯수
                nextMap.add(count);

                // B 그룹을 이루고 있는 구슬 번호
                nextMap.add(map[mapPosition.get(i-1)[0]][mapPosition.get(i-1)[1]]);

                // 갯수 초기화
                count = 1;
            }

            if (map[x][y] == 0) break;
        }

        // 저장된 A, B 리스트 다시 map에 반영
        int idx = 0;
        for (int i = 0; i < mapPosition.size(); i++) {
            // **인덱스 에러난 이유 2** 모두 다 터졌을 수도 있으므로 미리 idx 확인해줘야함!
            if (idx == nextMap.size()) break;
            map[mapPosition.get(i)[0]][mapPosition.get(i)[1]] = nextMap.get(idx++);
        }

    }

    static void explosion() {
        int count = 1;
        for (int i = 1; i < mapPosition.size(); i++) {
            int[] position = mapPosition.get(i);
            int x = position[0];
            int y = position[1];

            // 전의 구슬 번호와 같을 때
            if (map[x][y] == map[mapPosition.get(i-1)[0]][mapPosition.get(i-1)[1]]) count++;
            else { // 다를 때
                if (count >= 4) { // 4이상 연속했으면 모두 폭발
                    // 폭발시 점수 획득
                    isExplosion = true;
                    for (int j = i - 1; j > i - 1 - count; j--) {
                        ans += map[mapPosition.get(j)[0]][mapPosition.get(j)[1]];
                        map[mapPosition.get(j)[0]][mapPosition.get(j)[1]] = 0;
                    }
                }

                count = 1;
            }

            if (map[x][y] == 0) break;
        }
    }

    static void moveMarble() {

        for (int i = 0; i < mapPosition.size(); i++) {
            int[] position = mapPosition.get(i);
            int x = position[0];
            int y = position[1];
            
            if (map[x][y] == 0) {
                for (int j = i + 1; j < mapPosition.size(); j++) {
                    int[] temp = mapPosition.get(j);
                    int tx = temp[0];
                    int ty = temp[1];
                    // 빈칸 채우기
                    if (map[tx][ty] != 0) {
                        map[x][y] = map[tx][ty];
                        map[tx][ty] = 0;
                        break;
                    }
                }
            }
        }

    }

    static void magic(int idx) {

        Magic curMagic = magic[idx];
        int dir = curMagic.dir;
        int power = curMagic.power;

        int dx = shark.row;
        int dy = shark.col;
        for (int p = 0; p < power; p++) {
            dx += dxdy[dir][0];
            dy += dxdy[dir][1];

            map[dx][dy] = 0;
        }
    }

    static void Blizzard() {
        // 모든 마법 사용
        for (int idx = 0; idx < M; idx++) {
            // 1. 마법 사용
            magic(idx);

            // 2. 구슬 이동
            moveMarble();

            while (true) {
                isExplosion = false;
                // 3. 구슬 폭발
                explosion();
                if (!isExplosion) break;

                // 4. 구슬 이동
                moveMarble();
            }

            // 5. 구슬 변화
            changeMarble();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map input
        isClear = true;
        map = new int[N][N];
        shark = new Shark(N / 2, N / 2);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) isClear = false;
            }
        }

        // magic input
        magic = new Magic[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int power = Integer.parseInt(st.nextToken());

            magic[i] = new Magic(dir, power);
        }

        // **인덱스 에러난 이유 1**
        // 구슬이 하나도 없을 경우 (edge case)
        if (isClear) {
            System.out.println(0);
            System.exit(0);
        }

        // map numbering
        mapNumber = new int[N][N];
        int dx = shark.row;
        int dy = shark.col;
        int idx = 0;
        int size = 0;
        total:
        while (true) {
            // 가는 거리 + 1
            size++;
            // 좌
            for (int i = 0; i < size; i++) {
                dx += dxdy[2][0];
                dy += dxdy[2][1];

                mapNumber[dx][dy] = ++idx;
                mapPosition.add(new int[]{dx, dy});

                // 마지막 번호
                if (dx == 0 && dy == 0) break total;
            }

            // 하
            for (int i = 0; i < size; i++) {
                dx += dxdy[1][0];
                dy += dxdy[1][1];

                mapNumber[dx][dy] = ++idx;
                mapPosition.add(new int[]{dx, dy});
            }

            // 가는 거리 + 1
            size++;
            // 우
            for (int i = 0; i < size; i++) {
                dx += dxdy[3][0];
                dy += dxdy[3][1];

                mapNumber[dx][dy] = ++idx;
                mapPosition.add(new int[]{dx, dy});
            }

            // 상
            for (int i = 0; i < size; i++) {
                dx += dxdy[0][0];
                dy += dxdy[0][1];

                mapNumber[dx][dy] = ++idx;
                mapPosition.add(new int[]{dx, dy});
            }

        }
        Blizzard();
        System.out.println(ans);
    }

    // debug
    static void printMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}