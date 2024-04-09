import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static class People {
        int row, col;

        public People(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Charger {
        int row, col, C, P;

        public Charger(int row, int col, int C, int P) {
            this.row = row;
            this.col = col;
            this.C = C;
            this.P = P;
        }
    }

    static int M, A, ans;
    static int[][] map;
    static Charger[] chargers;
    static People[] people;
    static int[] routeA, routeB;
    // 이동x, 상, 우, 하, 좌
    static int[][] dxdy = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static void move() {
        // 움직이기 전 충전 가능
        for (int i = 0; i < 2; i++) { // 사용자 2명
            int maxP = 0;
            for (int j = 0; j < A; j++) { // 각 충전기 중 최댓값
                // 충전 범위 내에 위치할 경우
                if (Math.abs(people[i].row - chargers[j].row) + Math.abs(people[i].col - chargers[j].col) <= chargers[j].C) {
                    maxP = Math.max(maxP, chargers[j].P);
                }
            }
            ans += maxP;
        }

        for (int m = 0; m < M; m++) { // 이동 횟수
            List<Integer> charge1 = new ArrayList<>();
            List<Integer> charge2 = new ArrayList<>();

            people[0].row += dxdy[routeA[m]][0];
            people[0].col += dxdy[routeA[m]][1];

            people[1].row += dxdy[routeB[m]][0];
            people[1].col += dxdy[routeB[m]][1];

            for (int i = 0; i < A; i++) { // 모든 charger에 대해 검사 charging 가능한지 검사
                if (Math.abs(people[0].row - chargers[i].row) + Math.abs(people[0].col - chargers[i].col) <= chargers[i].C) {
                    charge1.add(i);
                }

                if (Math.abs(people[1].row - chargers[i].row) + Math.abs(people[1].col - chargers[i].col) <= chargers[i].C) {
                    charge2.add(i);
                }
            }

            // 둘다 충전 가능할 경우
            int maxP = 0;
            // 완전 탐색으로 가능한 조합 모두 비교하여 최대 P 구하기
            if (charge1.size() > 0 && charge2.size() > 0) { // 둘다 충전 가능할 경우
                for (int i : charge1) {
                    for (int j : charge2) {
                        int temp = 0;
                        if (i == j) {
                            temp = chargers[i].P;
                        } else {
                            temp += chargers[i].P;
                            temp += chargers[j].P;
                        }
                        maxP = Math.max(maxP, temp);
                    }
                }
            } else if (charge1.size() > 0) {
                for (int i : charge1) {
                    maxP = Math.max(maxP, chargers[i].P);
                }
            } else if (charge2.size() > 0) {
                for (int i : charge2) {
                    maxP = Math.max(maxP, chargers[i].P);
                }
            }

            ans += maxP;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            map = new int[10][10];

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            // input route
            routeA = new int[M];
            routeB = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<M; i++) routeA[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<M; i++) routeB[i] = Integer.parseInt(st.nextToken());

            // input charger
            chargers = new Charger[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int C = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                chargers[i] = new Charger(x, y, C, P);
                map[x][y] = 1; // charger
            }
            // user
            map[0][0] = 2;
            map[9][9] = 2;
            people = new People[2];
            people[0] = new People(0, 0);
            people[1] = new People(9, 9);

            ans = 0;
            move();
            System.out.println("#" + tc + " " + ans);

        }
        
    }

}