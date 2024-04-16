import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    private static final int BIGNUM = 1000 * 1000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] colors = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken()); // R
            colors[i][1] = Integer.parseInt(st.nextToken()); // G
            colors[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        int[][] redStart = new int[N][3];
        int[][] greenStart = new int[N][3];
        int[][] blueStart = new int[N][3];

        // red로 시작
        redStart[0][0] = colors[0][0];
        redStart[0][1] = BIGNUM;
        redStart[0][2] = BIGNUM;

        // green으로 시작
        greenStart[0][0] = BIGNUM;
        greenStart[0][1] = colors[0][1];
        greenStart[0][2] = BIGNUM;

        // blue로 시작
        blueStart[0][0] = BIGNUM;
        blueStart[0][1] = BIGNUM;
        blueStart[0][2] = colors[0][2];

        for (int i = 1; i < N; i++) {
            redStart[i][0] = Math.min(redStart[i-1][1], redStart[i-1][2]) + colors[i][0]; // R
            redStart[i][1] = Math.min(redStart[i-1][0], redStart[i-1][2]) + colors[i][1]; // G
            redStart[i][2] = Math.min(redStart[i-1][0], redStart[i-1][1]) + colors[i][2]; // B

            greenStart[i][0] = Math.min(greenStart[i-1][1], greenStart[i-1][2]) + colors[i][0]; // R
            greenStart[i][1] = Math.min(greenStart[i-1][0], greenStart[i-1][2]) + colors[i][1]; // G
            greenStart[i][2] = Math.min(greenStart[i-1][0], greenStart[i-1][1]) + colors[i][2]; // B

            blueStart[i][0] = Math.min(blueStart[i-1][1], blueStart[i-1][2]) + colors[i][0]; // R
            blueStart[i][1] = Math.min(blueStart[i-1][0], blueStart[i-1][2]) + colors[i][1]; // G
            blueStart[i][2] = Math.min(blueStart[i-1][0], blueStart[i-1][1]) + colors[i][2]; // B
        }

        int redMin = Math.min(redStart[N-1][1], redStart[N-1][2]);
        int greenMin = Math.min(greenStart[N-1][0], greenStart[N-1][2]);
        int blueMin = Math.min(blueStart[N-1][0], blueStart[N-1][1]);

        int ans = Math.min(redMin, Math.min(greenMin, blueMin));
        System.out.println(ans);
    }
}