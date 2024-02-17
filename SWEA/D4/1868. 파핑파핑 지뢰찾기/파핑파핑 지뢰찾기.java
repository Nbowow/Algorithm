import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, ans;
    static char[][] map;
    static int[][] countMap;
    static boolean[][] isVisited;
    static boolean[][] dfsVisited;
    static Queue<Node> q;
    static Stack<Node> st;
    static int[][] dxdy = {{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}};

    // 한번 클릭시 맵 오픈
    static void bfs(int row, int col) {

        q.offer(new Node(row, col));
        while (!q.isEmpty()) {
            st = new Stack<>();

            Node temp = q.poll();
            // 해당 노드 방문 표시
            isVisited[temp.row][temp.col] = true;

            int count = 0;
            // 현재 위치에서 8방향 지뢰 탐색
            for (int i = 0; i < 8; i++) {
                int dx = temp.row + dxdy[i][0];
                int dy = temp.col + dxdy[i][1];

                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !isVisited[dx][dy]) {
                    if (map[dx][dy] == '*') count++;
                        // 지뢰가 아닐 경우
                    else {
                        st.push(new Node(dx, dy));

                    }

                }
            }
            // 8방향에 지뢰가 있을 경우 해당 자리만 지뢰 갯수 표시해주고 끝
            map[temp.row][temp.col] = Integer.toString(count).charAt(0);

            // 8방향에 지뢰가 없을 경우 모든 방향에 대해 다시 bfs
            if (count == 0) {
                while (!st.isEmpty()) {
                    Node node = st.pop();
                    isVisited[node.row][node.col] = true;
                    q.offer(node);
                }
            }

        }

    }

    // 0이 되는 곳 미리 다 눌러놓기
    static boolean checkZero(int row, int col) {

        boolean isZero = true;
        for (int i = 0; i < 8; i++) {
            int dx = row + dxdy[i][0];
            int dy = col + dxdy[i][1];

            if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                if (map[dx][dy] == '*') {
                    isZero = false;
                    break;
                }
            }
        }
        // 주변에 지뢰가 하나도 없으면 q에 넣어줌
        if (isZero) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            countMap = new int[N][N];
            isVisited = new boolean[N][N];
            st = new Stack<>();
            ans = 0;

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 미리 map 0클릭되는 곳 다 만들어 놓기
            q = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        // 주변에 지뢰가 하나도 없는 곳이라면 -> 무조건 누르는게 최고
                        if (!isVisited[i][j] && checkZero(i, j)){
                            map[i][j] = '0';
                            ans++;
                            bfs(i, j);
                        }
                    }
                }
            }

            // 나머지는 다 하나씩 눌러야 되는 것
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') ans++;
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}