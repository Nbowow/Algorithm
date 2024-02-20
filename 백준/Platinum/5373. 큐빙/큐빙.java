import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    // 위 - 0(w), 아래 - 1(y), 앞 - 2(r), 뒷 - 3(o), 왼 - 4(g), 오 - 5(b)
    // U : 윗면, D : 아랫면, F : 앞면, B : 뒷면, L : 왼쪽면, R : 오른쪽면
    static String[] oper;
    // 6개의 면, 면 마다 3*3 배열
    static char[][][] cube = new char[6][3][3];

    static void R() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][2];
        temp[1] = cube[0][1][2];
        temp[2] = cube[0][2][2];

        // 2(0 2, 1 2, 2 2) -> 0(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[0][i][2] = cube[2][i][2];
        }
        // 1(0 2, 1 2, 2 2) -> 2(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[2][i][2] = cube[1][i][2];
        }
        // 3(2 0, 1 0, 0 0) -> 1(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[1][i][2] = cube[3][2-i][0];
        }
        // 0(0 2, 1 2, 2 2) -> 3(2 0, 1 0, 0 0)
        for (int i = 0; i < 3; i++) {
            cube[3][2-i][0] = temp[i];
        }

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[5][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[5][i][j] = arrTemp[3-1-j][i];
            }
        }
    }
    static void L() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][0];
        temp[1] = cube[0][1][0];
        temp[2] = cube[0][2][0];

        // 3(2 2, 1 2, 0 2) -> 0(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) {
            cube[0][i][0] = cube[3][2-i][2];
        }
        // 1(0 0, 1 0, 2 0) -> 3(2 2, 1 2, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[3][2-i][2] = cube[1][i][0];
        }
        // 2(0 0, 1 0, 2 0) -> 1(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) {
            cube[1][i][0] = cube[2][i][0];
        }
        // 0(0 0, 1 0, 2 0) -> 2(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) {
            cube[2][i][0] = temp[i];
        }

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[4][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[4][i][j] = arrTemp[3-1-j][i];
            }
        }
    }
    static void B() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][0];
        temp[1] = cube[0][0][1];
        temp[2] = cube[0][0][2];

        // 5(0 2, 1 2, 2 2) -> 0(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[0][0][i] = cube[5][i][2];
        }
        // 1(2 0, 2 1, 2 2) -> 5(2 2, 1 2, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[5][2-i][2] = cube[1][2][i];
        }
        // 4(0 0, 1 0, 2 0) -> 1(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[1][2][i] = cube[4][i][0];
        }
        // 0(0 2, 0 1, 0 0) -> 4(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) {
            cube[4][i][0] = temp[2-i];
        }

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[3][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[3][i][j] = arrTemp[3-1-j][i];
            }
        }
    }
    static void F() { // 다시 확인
        char[] temp = new char[3];
        temp[0] = cube[0][2][0];
        temp[1] = cube[0][2][1];
        temp[2] = cube[0][2][2];

        // 4(2 2, 1 2, 0 2) -> 0(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[0][2][i] = cube[4][2-i][2];
        }
        // 1(0 0, 0 1, 0 2) -> 4(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) {
            cube[4][i][2] = cube[1][0][i];
        }
        // 5(2 0, 1 0, 0 0) -> 1(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) {
            cube[1][0][i] = cube[5][2-i][0];
        }
        // 0(2 0, 2 1, 2 2) -> 5(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) {
            cube[5][i][0] = temp[i];
        }

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[2][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[2][i][j] = arrTemp[3-1-j][i];
            }
        }
    }
    static void D() {
        char[] temp = new char[3];
        temp[0] = cube[2][2][0];
        temp[1] = cube[2][2][1];
        temp[2] = cube[2][2][2];

        // 4 -> 2
        for (int i = 0; i < 3; i++) {
            cube[2][2][i] = cube[4][2][i];
        }

        // 3 -> 4
        for (int i = 0; i < 3; i++) {
            cube[4][2][i] = cube[3][2][i];
        }

        // 5 -> 3
        for (int i = 0; i < 3; i++) {
            cube[3][2][i] = cube[5][2][i];
        }

        // 2 -> 5
        for (int i = 0; i < 3; i++) {
            cube[5][2][i] = temp[i];
        }

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[1][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[1][i][j] = arrTemp[3-1-j][i];
            }
        }

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
        // 5 -> 2
        for (int i = 0; i < 3; i++) {
            cube[2][0][i] = temp[i];
        }

        // 윗면 배열 90도 돌리기
        // [i, j] -> [j, N-1-i]
        // [N-1-j, i] -> [i, j]

        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[0][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[0][i][j] = arrTemp[3-1-j][i];
            }
        }
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
                    else {
                        for (int n = 0; n < 3; n++) {
                            U();
                        }
                    }

                } else if (direction == 'D') {
                    if (plusMinus == '+') D();
                    else {
                        for (int n = 0; n < 3; n++) {
                            D();
                        }
                    }

                } else if (direction == 'F') {
                    if (plusMinus == '+') F();
                    else {
                        for (int n = 0; n < 3; n++) {
                            F();
                        }
                    }

                } else if (direction == 'B') {
                    if (plusMinus == '+') B();
                    else {
                        for (int n = 0; n < 3; n++) {
                            B();
                        }
                    }

                } else if (direction == 'L') {
                    if (plusMinus == '+') L();
                    else {
                        for (int n = 0; n < 3; n++) {
                            L();
                        }
                    }

                } else if (direction == 'R') {
                    if (plusMinus == '+') R();
                    else {
                        for (int n = 0; n < 3; n++) {
                            R();
                        }
                    }

                }
            }

            printCube();

        }
    }


    static void printOper() {
        for (int i = 0; i < oper.length; i++) {

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
        // 0 - 위(w), 1 - 아래(y)
        // 2 - 앞(r), 3 - 뒤(o)
        // 4 - 왼(g), 5 - 오(b)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[0][i][j]);
            }
            System.out.println();
        }
    }
}