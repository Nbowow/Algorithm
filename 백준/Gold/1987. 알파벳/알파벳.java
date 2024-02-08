import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static Character[][] boards;
    static boolean[][] isVisited;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Character> temp = new ArrayList<>();
    static int ans;

    static void dfs(int row, int col, int depth) {

//        System.out.println("row : " + row + " col : " + col + " depth : " + depth);
        ans = Math.max(ans, depth);

        // 상 하 좌 우
        for (int i = 0; i < 4; i++) {
            int dx = row + dxdy[i][0];
            int dy = col + dxdy[i][1];

            if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
                // 아직 방문하지도 않았고, 지금까지 지나온 알파벳이 아닐 경우
                if (!isVisited[dx][dy] && !temp.contains(boards[dx][dy])) {
                    isVisited[dx][dy] = true;
                    temp.add(boards[dx][dy]);
                    dfs(dx, dy, depth + 1);
                    isVisited[dx][dy] = false;
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        boards = new Character[R][C];
        isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                boards[i][j] = str.charAt(j);
            }
        }

        isVisited[0][0] = true;
        temp.add(boards[0][0]);
        dfs(0, 0, 1);
        System.out.println(ans);
    }

}