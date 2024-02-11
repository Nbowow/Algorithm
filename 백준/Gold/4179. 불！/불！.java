import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	작성자 : 남보우
	문제 : [G4] 불! - 4179번
	제출 : 2024년 2월 11일
	결과 : 통과
	성능 요약 : 메모리 48120KB, 시간 2864ms
	아이디어 :
	    1. 전체 미로를 돌며 지훈이가 위치한 곳과 불이 위치한곳을 큐에 각각 넣어준다.
	    2. 불을 먼저 이동시킨 후, 지훈이가 이동할 수 있으면 이동시키며 escapeTime을 1증가시킨다.
	    3. 더 이상 지훈이가 이동할 수 없을 때 까지 1~2를 반복한다.

	    위 방식말고 불길이 가는 시간을 체크하며 모든 bfs를 다 돈 후
	    지훈이의 이동을 체크하면(불길이 가는 시간보다 빨리 움직일 경우 이동가능) 시간복잡도를 훨씬 많이 줄일 수 있을 것 같다...
*/

public class Main {

    static int R, C, escapeTime;
    static boolean isArrive;
    static boolean[][] isJVisited;
    static boolean[][] isFVisited;
    static char[][] miro;
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, { 0, 1}};
    static Queue<int[]> qJ = new ArrayDeque<>();
    static Queue<int[]> qF = new ArrayDeque<>();

    static void bfs(int del, int row, int col) {

        // 미로의 가장자리에 접한 공간에서 탈출 가능
        if (del == 1 && (row == 0 || row == R - 1 || col == 0 || col == C - 1)) {
            isArrive = true;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dx = row + dxdy[i][0];
            int dy = col + dxdy[i][1];

            if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
                // 불길
                if (del == 2 && (miro[dx][dy] == '.' || miro[dx][dy] == 'J')) {
                    miro[dx][dy] = 'F';
                }

                // 지훈이
                else if (del == 1 && miro[dx][dy] == '.') {
                    miro[dx][dy] = 'J';
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        miro = new char[R][C];
        isJVisited = new boolean[R][C];
        isFVisited = new boolean[R][C];

        // 입력받은 문자열을 char형 2차원 배열로 변환
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            miro[i] = st.nextToken().toCharArray();
        }

        // 더이상 지훈이가 이동할 수 없을 때까지 반복
        boolean canMove = true;
        while (canMove) {
            canMove = false;
            // 지훈이와 불 위치 체크
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (!isJVisited[i][j] && miro[i][j] == 'J') {
                        isJVisited[i][j] = true;
                        canMove = true;
                        qJ.offer(new int[]{i, j});
                    } else if (!isFVisited[i][j] && miro[i][j] == 'F') {
                        isFVisited[i][j] = true;
                        qF.offer(new int[]{i, j});
                    }
                }
            }
            // <불보다 지훈이를 먼저 이동시키면 시간초과가 난다!>

            // 불 이동 체크 (F's del == 2)
            while (!qF.isEmpty()) {
                int[] temp = qF.poll();
                bfs(2, temp[0], temp[1]);
            }

            // 지훈이 이동 체크 (J's del == 1)
            escapeTime += 1;
            while (!qJ.isEmpty() && !isArrive) {
                int[] temp = qJ.poll();
                bfs(1, temp[0], temp[1]);
            }

            if (isArrive) break;
        }

        System.out.println(isArrive ? escapeTime : "IMPOSSIBLE");

    }

}