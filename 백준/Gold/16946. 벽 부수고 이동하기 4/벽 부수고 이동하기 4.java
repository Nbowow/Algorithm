import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N, M;
    public static int[][] map = new int[1000][1000];         //빈칸 : 0, 벽 : -1
    public static int[][] grpMap = new int[1000][1000];
    public static List<Point> wallList = new ArrayList<>();
    public static List<Integer> cellCnt = new ArrayList<>(); //그룹별 cell 갯수
    public static int[] dx = {0,0,1,-1};
    public static int[] dy = {1,-1,0,0};

    public static void makeResult() {
        //각 벽에 대해 부순후 이동가능한 칸 갯수를 map에 입력해줌
        for(int i = 0; i < wallList.size(); i++) {
            Point cur = wallList.get(i);
            int totalCnt = 1;
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int d = 0; d < 4; d++) {
                int X = cur.x + dx[d];
                int Y = cur.y + dy[d];
                if (X < 0 || Y < 0 || X >= N || Y >= M || map[X][Y] != 0) continue;
                if (hashMap.containsKey(grpMap[X][Y])) continue;
                hashMap.put(grpMap[X][Y], 1);
                totalCnt += cellCnt.get(grpMap[X][Y]);
            }
            map[cur.x][cur.y] = totalCnt % 10;
        }
    }

    public static void grouping() {
        Queue<Point> queue = new LinkedList<>();
        int grpIndex = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) continue;
                if (grpMap[i][j] != 0) continue;
                //해당 셀에서 그룹핑 시작
                grpIndex++;
                int totalCnt = 0;

                //BFS
                totalCnt++;
                grpMap[i][j] = grpIndex;
                queue.add(new Point(i, j));
                while(!queue.isEmpty()) {
                    Point cur = queue.poll();
                    for (int d = 0; d < 4; d++) {
                        int X = cur.x + dx[d];
                        int Y = cur.y + dy[d];
                        if (X < 0 || Y < 0 || X >= N || Y >= M || grpMap[X][Y] != 0 || map[X][Y] == -1) continue;
                        totalCnt++;
                        grpMap[X][Y] = grpIndex;
                        queue.add(new Point(X, Y));
                    }
                }
                cellCnt.add(totalCnt);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String tmp = sc.next();
            for (int j = 0; j < M; j++) {
                if (tmp.charAt(j) == '0') map[i][j] = 0;
                if (tmp.charAt(j) == '1') {
                    wallList.add(new Point(i,j));
                    map[i][j] = -1;
                }
            }
        }
        //그룹핑
        cellCnt.add(0);
        grouping();
        //결과 계산
        makeResult();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}