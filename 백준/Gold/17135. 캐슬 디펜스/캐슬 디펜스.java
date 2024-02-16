import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D, killScore;
    static int[][] map;
    static int[][] originalMap;
    static boolean[][] isKilled;
    static int[] kill;
    static List<Integer> archer = new ArrayList<>();
    static boolean[] isVisited;
    static int maxKill;
    static Set<int[]> set = new HashSet<>();
    static Queue<int[]> q;
    static int[][] check;
    // 좌, 상, 우
    static int[][] dxdy = {{0, -1}, {-1, 0}, {0, 1}};
    static boolean[][] isVisited2;

    static void defense(int row, int col, int depth) {

        // 위에 바로 적 존재
        if (!isKilled[row][col] && map[row][col] == 1) {
            set.add(new int[]{row, col});
            return;
        }

        q.offer(new int[] {row, col, depth});
        isVisited2[row][col] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if (temp[2] < D) {
                for (int i = 0; i < 3; i++) {
                    int dx = temp[0] + dxdy[i][0];
                    int dy = temp[1] + dxdy[i][1];

                    if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
                        if (!isVisited2[dx][dy]) {
                            isVisited2[dx][dy] = true;
//                            System.out.println("dxdy : " + dx + " " + dy);
                            q.offer(new int[]{dx, dy, temp[2] + 1});

                            // 적 찾음
                            if (!isKilled[dx][dy] && map[dx][dy] == 1) {
                                set.add(new int[]{dx, dy});
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    static void siteArcher(int index, int depth) {
        // 궁수가 위치할 수 있는 모든 경우의 수에 대해
        if (depth == 3) {
            getOriginalMap();
            // 실제로 죽인 적 수
            killScore = 0;
            // 죽였음을 표시
            isKilled = new boolean[N][M];

            // defense 시작 (적이 다 내려올 때 까지 N번 반복)
            int count = N;
            while(count-->0) {
                // 적 탐색에 성공했는지 확인하기 위한 배열
                kill = new int[3];
                set = new HashSet<>();
                check = new int[N][M];

                for (int i=0; i<archer.size(); i++) {
                    isVisited2 = new boolean[N][M];
                    q = new ArrayDeque<>();
                    defense(N-1, archer.get(i), 1);
                }

                Iterator<int[]> setIter = set.iterator();

                while(setIter.hasNext()) {
                    int[] temp = setIter.next();
                    check[temp[0]][temp[1]] = 1;
//                    System.out.println(temp[0] + " " + temp[1]);
                    isKilled[temp[0]][temp[1]] = true;
                }

                for (int i=0; i<N; i++) {
                    for (int j=0; j<M; j++) {
                        killScore += check[i][j];
                    }
                }

//                printMap();
//                printKill();
                moveEnemy();


            }

//            System.out.println("killscore : " + killScore);
            maxKill = Math.max(maxKill, killScore);
        }

        for (int i=index; i<M; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                archer.add(i);
                siteArcher(i+1, depth + 1);
                isVisited[i] = false;
                archer.remove(archer.size()-1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        originalMap = new int[N][M];
        isVisited = new boolean[M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        storeOriginalMap();

        siteArcher(0, 0);

        System.out.println(maxKill);

    }

    static void printMap() {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void printOriginalMap() {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(originalMap[i]));
        }
    }

    static void storeOriginalMap() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                originalMap[i][j] = map[i][j];
            }
        }
    }

    static void getOriginalMap() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j] = originalMap[i][j];
            }
        }
    }

    static void printKill() {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(isKilled[i]));
        }
    }

    // 적 한칸씩 다가옴
    static void moveEnemy() {

        for (int i=N-1; i>=1; i--) {
            for (int j=0; j<M; j++) {
                map[i][j] = map[i-1][j];
                isKilled[i][j] = isKilled[i-1][j];
            }
        }

        for (int j=0; j<M; j++) {
            map[0][j] = 0;
            isKilled[0][j] = false;
        }
    }


}