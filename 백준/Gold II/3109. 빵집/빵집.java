import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans, temp, maxLine;
    static boolean isArrive;
    static Character[][] gas;
    static boolean[][] isVisited;
    static boolean[] isVisitedCombo;
    // 오른쪽위, 오른쪽, 오른쪽 아래
    static int[][] dxPipe = {{-1, 1}, {0, 1}, {1, 1}};

    static void dfs(int row, int col, int depth) {
        // 원웅이네 빵집에 파이프가 도착했을 때
        if (depth == C) {
//            System.out.println("row = " + row + " col = " + col);
//            printMap(isVisited);

            // 최종 목적지(빵집)에 도착했음을 표시 - 더이상 이 dfs에 남아있지 않아도 됨.
            isArrive = true;
            ans += 1;
            return;
        }


        int[] rightUp = {row+dxPipe[0][0], col+dxPipe[0][1]};
        int[] right = {row+dxPipe[1][0], col+dxPipe[1][1]};
        int[] rightDown = {row+dxPipe[2][0], col+dxPipe[1][1]};

        // 오른쪽 위
        if (rightUp[0] >= 0 && !isVisited[rightUp[0]][rightUp[1]] && gas[rightUp[0]][rightUp[1]] == '.') {
            isVisited[rightUp[0]][rightUp[1]] = true;
            dfs(rightUp[0], rightUp[1], depth + 1);
        }

        // 오른쪽
        // 오른쪽을 가는 경우는 (오른쪽 위)에서 도착지에 도착 하지 못했을 경우에만 가야함
        if (!isArrive) {
            if (!isVisited[right[0]][right[1]] && gas[right[0]][right[1]] == '.') {
                isVisited[right[0]][right[1]] = true;
                dfs(right[0], right[1], depth + 1);
            }
        }

        // 오른쪽 아래
        // 오른쪽 아래를 가는 경우는 (오른쪽 위, 오른쪽)에서 도착지에 도착하지 못했을 경우에만 가야함
        if (!isArrive) {
            if (rightDown[0] <R && !isVisited[rightDown[0]][rightDown[1]] && gas[rightDown[0]][rightDown[1]] == '.') {
                isVisited[rightDown[0]][rightDown[1]] = true;
                dfs(rightDown[0], rightDown[1], depth + 1);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        gas = new Character[R][C];
        isVisited = new boolean[R][C];
        isVisitedCombo = new boolean[R];
        maxLine = R;

        for (int i=0; i<R; i++) {
            String str = br.readLine();
            for (int j=0; j<C; j++) {
                gas[i][j] = str.charAt(j);
            }
        }

        // 근처 빵집에서 파이프라인 연결 시작
        for (int i = 0; i < R; i++) {
            isArrive = false;
            dfs(i, 0, 1);
        }


        System.out.println(ans);
    }

    static void printMap(boolean[][] arr) {
        for (int i = 0; i < R; i++) {
            System.out.print(i);
            for (int j = 0; j < C; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}