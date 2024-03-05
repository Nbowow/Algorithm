import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static char[][] bulbs = new char[10][10];
    static int[][] dxdy = {{-1, 0}, {1, 0}, {0, 0}, {0, -1}, {0, 1}};
    static int ans = Integer.MAX_VALUE;

    static void check() {
        // 2^10의 모든 경우의 수에 대해 검사
        for (int i = 0; i < 1<<10; i++) {
            char[][] tempBulbs = copyBulbs();
            int count = 0; // 눌러준 스위치의 갯수

            // 모든 경우의 수에 대해 각 전구(0~9)의 스위치를 눌러준다
            // ex) i = 15 : 0000001111 -> 첫번째부터 네번째의 전구 스위치 눌렀을 경우
            for (int n = 0; n < 10; n++) {
                if ( (i & 1<<n) == 1<<n ) {
                    count++; // 스위치 눌러줌
                    for (int k = 0; k < dxdy.length; k++) {
                        int dx = dxdy[k][0];
                        int dy = n + dxdy[k][1];

                        if (dx >= 0 && dx < 10 && dy >= 0 && dy < 10) {
                            tempBulbs[dx][dy] = tempBulbs[dx][dy] == '#' ? 'O' : '#';
                        }
                    }
                }
            }

            for (int x = 1; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    // 내 위에 전구에 불이 켜져있을 경우
                    if (tempBulbs[x-1][y] == 'O') {
                        count++; // 스위치 눌러줌
                        for (int k = 0; k < dxdy.length; k++) {
                            int dx = x + dxdy[k][0];
                            int dy = y + dxdy[k][1];

                            if (dx >= 0 && dx < 10 && dy >= 0 && dy < 10) {
                                tempBulbs[dx][dy] = tempBulbs[dx][dy] == '#' ? 'O' : '#';
                            }
                        }
                    }
                }
            }

            if (isFinish(tempBulbs)) ans = Math.min(ans, count);

        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            bulbs[i] = br.readLine().toCharArray();
        }
        check();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static char[][] copyBulbs() {
        char[][] temp = new char[10][];
        for (int i=0; i<10; i++) temp[i] = bulbs[i].clone();
        return temp;
    }

    static boolean isFinish(char[][] arr) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] == 'O') return false;
            }
        }
        return true;
    }
}