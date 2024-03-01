import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] wheels = new int[4][8];
    static int K;
    static Queue<Integer> q1,q2, q3, q4, q5, q6;
    static int ans;


    static void rotateWheels(int num, int dir) {
        if (dir == 1) { // 시계 방향 회전
            int temp = wheels[num][7];
            for (int i = 7; i > 0; i--) { // 뒤에서부터 하나씩 당겨옴
                wheels[num][i] = wheels[num][i - 1];
            }
            wheels[num][0] = temp;
        } else if (dir == -1) { // 반시계 방향 회전
            int temp = wheels[num][0];
            for (int i = 0; i < 7; i++) {
                wheels[num][i] = wheels[num][i + 1];
            }
            wheels[num][7] = temp;
        }

//        System.out.println("num : " + num + " " + dir + " " + Arrays.toString(wheels[num]));
    }

    static void rotate(int num, int dir) {
        int f1 = 0;
        int f2 = 0;
        int f3 = 0;
        int f4 = 0;
        int reverseDir = dir * (-1);

        if (num == 1) {
            f1 = dir;
            if (wheels[0][2] != wheels[1][6]) { // 2번도 돌려야함
                f2 = reverseDir;
                if (wheels[1][2] != wheels[2][6]) { // 3번도 돌려야함
                    f3 = dir;
                    if (wheels[2][2] != wheels[3][6]) f4 = reverseDir; // 4번도 돌려야함
                }
            }
        }

        if (num == 2) {
            f2 = dir;
            if (wheels[1][6] != wheels[0][2]) f1 = reverseDir;
            if (wheels[1][2] != wheels[2][6]) {
                f3 = reverseDir;
                if (wheels[2][2] != wheels[3][6]) f4 = dir;
            }
        }

        if (num == 3) {
            f3 = dir;
            if (wheels[2][2] != wheels[3][6]) f4 = reverseDir; // 4번
            if (wheels[2][6] != wheels[1][2]) {
                f2 = reverseDir;
                if (wheels[1][6] != wheels[0][2]) f1 = dir;
            }
        }

        if (num == 4) {
            f4 = dir;
            if (wheels[3][6] != wheels[2][2]) {
                f3 = reverseDir;
                if (wheels[2][6] != wheels[1][2]) {
                    f2 = dir;
                    if (wheels[1][6] != wheels[0][2]) f1 = reverseDir;
                }
            }

        }
        if (f1 != 0) rotateWheels(0, f1);
        if (f2 != 0) rotateWheels(1, f2);
        if (f3 != 0) rotateWheels(2, f3);
        if (f4 != 0) rotateWheels(3, f4);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = str.charAt(j) - 48;
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 톱니 번호
            int D = Integer.parseInt(st.nextToken()); // 방향 (1 : 시계, -1 : 반시계)

            rotate(N, D);
        }

        if (wheels[0][0] == 1) ans += 1;
        if (wheels[1][0] == 1) ans += 2;
        if (wheels[2][0] == 1) ans += 4;
        if (wheels[3][0] == 1) ans += 8;


        System.out.println(ans);

    }
}