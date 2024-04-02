import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_000;
    static long[][] fiboMatrix = {{1, 1}, {1,0}};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        // S[n] = fibo[n+2] -1
        // S[b] - S[a-1] = fibo[b+2] - fibo[a+1];
        long ans = fibo(b + 2) - fibo(a + 1);
        System.out.println(ans > 0 ? ans : ans + MOD);
    }

    static Long fibo(Long num) {
        long[][] result = power(fiboMatrix, num - 1);
        return result[0][0] % MOD;
    }

    static long[][] power(long[][] matrix, long exponent) {
        if (exponent == 1) return matrix;

        if (exponent % 2 == 0) {
            long[][] temp = power(matrix, exponent / 2);
            return multiply(temp, temp);
        } else {
            long[][] temp = power(matrix, exponent - 1);
            return multiply(temp, fiboMatrix);
        }
    }

    // 행렬 곱셈 계산
    static long[][] multiply(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[2][2];
        result[0][0] = (matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0]) % MOD;
        result[0][1] = (matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1]) % MOD;
        result[1][0] = (matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0]) % MOD;
        result[1][1] = (matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1]) % MOD;
        return result;
    }
}