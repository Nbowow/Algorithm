import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean ans;
    static String[][] map;
    static String[][] temp;
    static boolean[][] isVisited;
    static List<int[]> object = new ArrayList<>();

    static boolean canSee(int row, int col) {
//        System.out.println("row = " + row + " col : " + col);
        // 상
        for (int i = row; i >= 0; i--) {
            if (temp[i][col].equals("O")) break;
            else if (temp[i][col].equals("S")) {
                return false;
            }
        }

        // 하
        for (int i = row; i < N; i++) {
            if (temp[i][col].equals("O")) break;
            else if (temp[i][col].equals("S")) {
                return false;
            }
        }

        // 좌
        for (int j = col; j >= 0; j--) {
            if (temp[row][j].equals("O")) break;
            else if (temp[row][j].equals("S")) {
                return false;
            }
        }

        // 우
        for (int j = col; j < N; j++) {
            if (temp[row][j].equals("O")) break;
            else if (temp[row][j].equals("S")) {
                return false;
            }
        }


        return true;
    }

    static void makeMap() {
        // 배열 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[i][j];
            }
        }

        // 장애물 입력
        for (int i = 0; i < 3; i++) {
            temp[object.get(i)[0]][object.get(i)[1]] = "O";
        }

//        printMap();

        boolean canHide = true;
xx:     for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j].equals("T")) {
                    // 모든 "T"에 대해 true가 나와야함
                    canHide = canSee(i, j);
                    if (!canHide) break xx;
                }
            }
        }

        if (canHide) ans = true;

    }

    static void dfs(int depth) {
        if (ans) return;
        // 장애물이 3개일 때
        if (depth == 3) {
            // 확인
            makeMap();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j] && !map[i][j].equals("S") && !map[i][j].equals("T")) {
                    isVisited[i][j] = true;
                    object.add(new int[]{i, j});
//                    System.out.println("i : " + i + " j :" + j);
                    dfs(depth + 1);
                    object.remove(object.size()-1);
                    isVisited[i][j] = false;
                }

            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        temp = new String[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }

        dfs(0);
        System.out.println(ans ? "YES" : "NO");
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }
}