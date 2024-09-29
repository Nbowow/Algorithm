import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point>{
        int idx, val;

        public Point(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Point p) {
            if (this.val == p.val) return Integer.compare(this.idx, p.idx);
            return Integer.compare(this.val, p.val);
        }
    }


    static int r, c, k;
    static int maxR, maxC;
    static int[][] map;

    static void doC(int row, int col) {

        int[][] cur = new int[105][105];
        for (int j = 0; j < col; j++) {
            int[] temp = new int[105];
            for (int i = 0; i < row; i++) {
                if (map[i][j] == 0) continue;
                temp[map[i][j]]++;
            }

            List<Point> points = new ArrayList<>();
            for (int i = 1; i < 105; i++) {
                if (temp[i] == 0) continue;

                points.add(new Point(i, temp[i]));
            }

            Collections.sort(points);

            maxR = Math.max(maxR, points.size() * 2);

            int idx = 0;
            for (Point p : points) {
                if (idx == 100) break;
                cur[idx++][j] = p.idx;
                if (idx == 100) break;
                cur[idx++][j] = p.val;
            }
        }

        map = copyMap(cur);
    }

    static void doR(int row, int col) {

        int[][] cur = new int[105][105];
        for (int i = 0; i < row; i++) {
            int[] temp = new int[105];
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 0) continue;
                temp[map[i][j]]++;
            }

            List<Point> points = new ArrayList<>();
            for (int j = 1; j < 105; j++) {
                if (temp[j] == 0) continue;

                points.add(new Point(j, temp[j]));
            }

            Collections.sort(points);

            maxC = Math.max(maxC, points.size() * 2);

            int idx = 0;
            for (Point p : points) {
                if (idx == 100) break;
                cur[i][idx++] = p.idx;
                if (idx == 100) break;
                cur[i][idx++] = p.val;
            }
        }

        map = copyMap(cur);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        map = new int[105][105];
        maxR = 3;
        maxC = 3;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 0;
        while (true) {
            if (map[r][c] == k || t>100) break;

            t++;
            if (maxR >= maxC) {
                doR(maxR, maxC);
            } else doC(maxR, maxC);

        }

        System.out.println(t <= 100 ? t : -1);

    }

    // debug
    static void printMap(int[][] map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] copyMap(int[][] map) {
        int[][] temp = new int[105][105];
        for (int i = 0; i < 105; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }
}