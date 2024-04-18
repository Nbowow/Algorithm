import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final double FOR_POSNUM = 100000.0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        double sx = Double.parseDouble(st.nextToken());
        double sy = Double.parseDouble(st.nextToken());

        double beforeX = sx;
        double beforeY = sy;
        double ans = 0;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            ans += (beforeX + x) * (beforeY - y) * 0.5;
            beforeX = x;
            beforeY = y;
        }

        ans += (beforeX + sx) * (beforeY - sy) * 0.5;

        System.out.printf("%.1f", Math.abs(ans));
    }
}