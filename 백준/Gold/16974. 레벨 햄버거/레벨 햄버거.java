import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long ans;
    static boolean isFind;
    static long[] breadLen;
    static long[] patty;
    static long divide(long X, int level) {
        // 기저조건
        if (level == 0) return 1L;
        if (X<=1) return 0L;

        long mid = breadLen[level] / 2 + 1;

        // level 빵 전체를 먹는 경우
        if (X == breadLen[level]) return patty[level];

        // mid 번째 까지만 먹는 경우
        if (X == mid) return patty[level-1] + 1;

        if (1 < X && X < mid) return divide(X-1, level-1);

        if (mid < X && X < breadLen[level]) return patty[level-1] + 1 + divide(X-mid, level-1);

        return -1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        breadLen = new long[N + 1];
        patty = new long[N + 1];
        long X = Long.parseLong(st.nextToken());

        // lvl 0 일때 버거 길이와 패티 갯수

        breadLen[0] = 1;
        patty[0] = 1;
        // L(n-1)햄버거 두개 + 패티 한장 + 햄버거번 2장
        for (int i = 1; i <= N; i++) {
            breadLen[i] = breadLen[i-1] * 2 + 3;
            patty[i] = patty[i-1] * 2 + 1;
        }


        System.out.println(divide(X, N));

    }
}