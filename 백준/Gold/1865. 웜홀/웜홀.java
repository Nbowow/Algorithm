import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final long MAX_NUM = (long)600 * 10000;
    static int N, M, W;
//    static List<int[]> nodes;

    static long[][] nodes;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점의 수
            M = Integer.parseInt(st.nextToken()); // 도로 개수
            W = Integer.parseInt(st.nextToken()); // 웜홀 개수

            nodes = new long[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(nodes[i], MAX_NUM);
            }

            for (int i = 0; i < M; i++) { // 도로 : 양방향
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                // 양방향 연결
//                nodes.add(new int[]{S, E, T});
//                nodes.add(new int[]{E, S, T});
                nodes[S][E] = Math.min(nodes[S][E], T);
                nodes[E][S] = Math.min(nodes[E][S], T);
            }

            for (int i = 0; i < W; i++) { // 웜홀 : 단방향
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                // 단방향 연결
//                nodes.add(new int[]{S, E, T});
                nodes[S][E] = Math.min(nodes[S][E], -T);
            }

            // 플로이드 워셜
            for (int k = 0; k < N; k++) { // 경로
                for (int i = 0; i < N; i++) { // 출발지
                    for (int j = 0; j < N; j++) { // 도착지
                        if (nodes[i][k] != MAX_NUM && nodes[k][j] != MAX_NUM) {
                            nodes[i][j] = Math.min(nodes[i][j], nodes[i][k] + nodes[k][j]);
                        }
                    }
                }
            }

            boolean canTimeleap = false;
            for (int i = 0; i < N; i++) {
                if (nodes[i][i] < 0) {
                    canTimeleap = true;
                    break;
                }
            }

            System.out.println(canTimeleap ? "YES" : "NO");

        }
    }
}