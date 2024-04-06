import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[][][][] effect;
    static char[][][][] color;
    static List<Integer> itemPer = new ArrayList<>();
    static boolean[] isVisited;
    static List<Integer> rotatePer = new ArrayList<>();
    static List<Integer> putPer = new ArrayList<>();
    static Map<Integer, int[]> map = new HashMap<>();
    static int ans;

    // 뽑은 아이템, 회전 수, 넣는 위치를 뽑은 뒤 폭탄 품질 구하기
    static void calculate() {

        // 초기 배열
        int[][] effectGama = new int[5][5];
        char[][] colorGama = new char[5][5];

        // item : {0, 1, 2}
        // rotate : {1, 2, 0} (0 : 0도, 1 : 90도, 2 : 180도, 3: 270도)
        // put : {0, 1, 1} (0 : (0,0), 1 : (0, 1), 2 : (1,0), 3: (1,1))
        // 첫번째 아이템 180도 회전해서 (0, 0)에 위치

        // 각 배열에 해당하는 동작 수행
        for (int i = 0; i < 3; i++) {
            int itemIdx = itemPer.get(i);
            int rotateIdx = rotatePer.get(i);
            int putIdx = putPer.get(i);

            int[] put = map.get(putIdx);
            int x = put[0];
            int y = put[1];

            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    // 품질
                    effectGama[a+x][b+y] += effect[a][b][itemIdx][rotateIdx];
                    if (effectGama[a+x][b+y] < 0) effectGama[a+x][b+y] = 0;
                    else if (effectGama[a+x][b+y] > 9) effectGama[a+x][b+y] = 9;

                    // 색상 (흰색이 아닐때만 색칠)
                    if (color[a][b][itemIdx][rotateIdx] != 'W') colorGama[a+x][b+y] = color[a][b][itemIdx][rotateIdx];
                }

            }

        }

        // 완성된 가마에 대해 품질 구하기
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (colorGama[i][j] == 'R') count += (7 * effectGama[i][j]);
                else if (colorGama[i][j] == 'B') count += (5 * effectGama[i][j]);
                else if (colorGama[i][j] == 'G') count += (3 * effectGama[i][j]);
                else if (colorGama[i][j] == 'Y') count += (2 * effectGama[i][j]);
            }
        }
        ans = Math.max(ans, count);
    }

    static void putPermutation(int depth) {

        if (depth == 3) { // 순열 뽑음
            calculate();
            return;
        }

        // 넣는 위치 4곳 중 하나 뽑기
        for (int i = 0; i < 4; i++) {
            putPer.add(i);
            putPermutation(depth + 1);
            putPer.remove(putPer.size() - 1);
        }
    }

    static void rotatePermutation(int depth) {

        if (depth == 3) { // 순열 뽑음
            // 가마에 넣는 순열
            putPermutation(0);
            return;
        }

        // 4방향 중 하나 뽑기
        for (int i = 0; i < 4; i++) {
            rotatePer.add(i);
            rotatePermutation(depth + 1);
            rotatePer.remove(rotatePer.size() - 1);
        }
    }

    static void itemPermutation(int depth) {
        if (depth == 3) { // 재료 순열 뽑음 (단, 중복 X)
            // 회전 순열
            rotatePermutation(0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                itemPer.add(i);
                itemPermutation(depth + 1);
                isVisited[i] = false;
                itemPer.remove(itemPer.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        // (r, c, n) : row, col, n번째, 회전
        effect = new int[4][4][n][4];
        color = new char[4][4][n][4];
        for (int c = 0; c < n; c++) {
            // 효능
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    effect[i][j][c][0] = Integer.parseInt(st.nextToken());
                }
            }

            // 원소
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    color[i][j][c][0] = st.nextToken().charAt(0);
                }
            }
        }

        // 배열 돌리기
        for (int c = 0; c < n; c++) { // 모든 재료에 대해
            for (int rotate = 1; rotate < 4; rotate++) { // 90도, 180도, 270도 회전
                // 효능
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        effect[i][j][c][rotate] = effect[4-(1+j)][i][c][rotate-1];
                    }
                }

                // 원소
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        color[i][j][c][rotate] = color[4-(1+j)][i][c][rotate-1];
                    }
                }
            }
        }

        // 재료 놓는 위치
        map.put(0, new int[]{0, 0});
        map.put(1, new int[]{0, 1});
        map.put(2, new int[]{1, 0});
        map.put(3, new int[]{1, 1});

        isVisited = new boolean[n];
        // 재료 순열
        itemPermutation(0);

        System.out.println(ans);

    }

}