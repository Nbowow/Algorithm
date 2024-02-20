import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 위 - 0(w), 아래 - 1(y), 앞 - 2(r), 뒷 - 3(o), 왼 - 4(g), 오 - 5(b)
    // U : 윗면, D : 아랫면, F : 앞면, B : 뒷면, L : 왼쪽면, R : 오른쪽면
    static String[] oper;
    // 6개의 면, 면 마다 3*3 배열
    static char[][][] cube = new char[6][3][3];

    static void rotate(int index) {
        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[index][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[index][i][j] = arrTemp[3-1-j][i];
            }
        }
    }
    static void R() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][2];
        temp[1] = cube[0][1][2];
        temp[2] = cube[0][2][2];

        // 2(0 2, 1 2, 2 2) -> 0(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[0][i][2] = cube[2][i][2];

        // 1(0 2, 1 2, 2 2) -> 2(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[2][i][2] = cube[1][i][2];

        // 3(2 0, 1 0, 0 0) -> 1(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[1][i][2] = cube[3][2-i][0];

        // 0(0 2, 1 2, 2 2) -> 3(2 0, 1 0, 0 0)
        for (int i = 0; i < 3; i++) cube[3][2-i][0] = temp[i];

        rotate(5);
    }
    static void L() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][0];
        temp[1] = cube[0][1][0];
        temp[2] = cube[0][2][0];

        // 3(2 2, 1 2, 0 2) -> 0(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[0][i][0] = cube[3][2-i][2];

        // 1(0 0, 1 0, 2 0) -> 3(2 2, 1 2, 0 2)
        for (int i = 0; i < 3; i++) cube[3][2-i][2] = cube[1][i][0];

        // 2(0 0, 1 0, 2 0) -> 1(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[1][i][0] = cube[2][i][0];

        // 0(0 0, 1 0, 2 0) -> 2(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[2][i][0] = temp[i];

        rotate(4);
    }
    static void B() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][0];
        temp[1] = cube[0][0][1];
        temp[2] = cube[0][0][2];

        // 5(0 2, 1 2, 2 2) -> 0(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[0][0][i] = cube[5][i][2];

        // 1(2 0, 2 1, 2 2) -> 5(2 2, 1 2, 0 2)
        for (int i = 0; i < 3; i++) cube[5][2-i][2] = cube[1][2][i];

        // 4(0 0, 1 0, 2 0) -> 1(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) cube[1][2][i] = cube[4][i][0];

        // 0(0 2, 0 1, 0 0) -> 4(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[4][i][0] = temp[2-i];

        rotate(3);
    }
    static void F() { // 다시 확인
        char[] temp = new char[3];
        temp[0] = cube[0][2][0];
        temp[1] = cube[0][2][1];
        temp[2] = cube[0][2][2];

        // 4(2 2, 1 2, 0 2) -> 0(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) cube[0][2][i] = cube[4][2-i][2];

        // 1(0 0, 0 1, 0 2) -> 4(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[4][i][2] = cube[1][0][i];

        // 5(2 0, 1 0, 0 0) -> 1(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[1][0][i] = cube[5][2-i][0];

        // 0(2 0, 2 1, 2 2) -> 5(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[5][i][0] = temp[i];

        rotate(2);
    }
    static void D() {
        char[] temp = new char[3];
        temp[0] = cube[2][2][0];
        temp[1] = cube[2][2][1];
        temp[2] = cube[2][2][2];

        // 4 -> 2
        for (int i = 0; i < 3; i++) cube[2][2][i] = cube[4][2][i];

        // 3 -> 4
        for (int i = 0; i < 3; i++) cube[4][2][i] = cube[3][2][i];

        // 5 -> 3
        for (int i = 0; i < 3; i++) cube[3][2][i] = cube[5][2][i];

        // 2 -> 5
        for (int i = 0; i < 3; i++) cube[5][2][i] = temp[i];

        rotate(1);
    }
    static void U() {
        char[] temp = new char[3];
        temp[0] = cube[5][0][0];
        temp[1] = cube[5][0][1];
        temp[2] = cube[5][0][2];

        // 3(0 0, 0 1, 0 2) -> 5(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[5][0][i] = cube[3][0][i];
        }
        // 4(0 0, 0 1, 0 2) -> 3(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[3][0][i] = cube[4][0][i];
        }
        // 2(0 0, 0 1, 0 2) -> 4(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[4][0][i] = cube[2][0][i];
        }
        // 5 -> 2(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[2][0][i] = temp[i];
        }

        // 배열 90도 돌리기
        // [i, j] <- [j, N-1-i]
        // [N-1-j, i] <- [i, j]
        rotate(0);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            makeCube();

            int N = Integer.parseInt(br.readLine());
            oper = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                oper[i] = st.nextToken();
            }

            for (int i = 0; i < oper.length; i++) {
                char direction = oper[i].charAt(0);
                char plusMinus = oper[i].charAt(1);

                if (direction == 'U') {
                    if (plusMinus == '+') U();
                    else {U(); U(); U();}

                } else if (direction == 'D') {
                    if (plusMinus == '+') D();
                    else {D(); D(); D();}

                } else if (direction == 'F') {
                    if (plusMinus == '+') F();
                    else {F(); F(); F();}

                } else if (direction == 'B') {
                    if (plusMinus == '+') B();
                    else {B(); B(); B();}

                } else if (direction == 'L') {
                    if (plusMinus == '+') L();
                    else {L(); L(); L();}

                } else if (direction == 'R') {
                    if (plusMinus == '+') R();
                    else {R(); R(); R();}
                }
            }
            printCube();
        }
    }

    static void makeCube() {
        // 위 - 0(w), 아래 - 1(y), 앞 - 2(r), 뒷 - 3(o), 왼 - 4(g), 오 - 5(b)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[0][i][j] = 'w';
                cube[1][i][j] = 'y';
                cube[2][i][j] = 'r';
                cube[3][i][j] = 'o';
                cube[4][i][j] = 'g';
                cube[5][i][j] = 'b';
            }
        }
    }

    static void printCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[0][i][j]);
            }
            System.out.println();
        }
    }
}