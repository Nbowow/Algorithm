import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int N = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=N; tc++) {
            char[] A = br.readLine().toCharArray();
            char[] W = br.readLine().toCharArray();
            char[] S = br.readLine().toCharArray();

            // pruning
            if (W.length > S.length) {
                System.out.println("no solution");
                continue;
            }

            // 문자열 순서 (인덱스 번호, 값)으로 저장
            HashMap<Integer, Character> map1 = new HashMap<>();
            // 문자열 순서 (값, 인덱스 번호)로 저장
            HashMap<Character, Integer> map2 = new HashMap<>();
            for (int i = 0; i < A.length; i++) {
                map1.put(i, A[i]);
                map2.put(A[i], i);
            }

            int[] indexTable = new int[W.length];
            for (int i = 0; i < W.length; i++) {
                indexTable[i] = map2.get(W[i]);
            }

            int count = 0;
            sb = new StringBuilder();
            for (int r=0; r<A.length; r++) { // r번 반복

                // shift
                for (int j=0; j<indexTable.length; j++) {
                    W[j] = map1.get(indexTable[j]);
                    indexTable[j]++;
                    if (indexTable[j] == A.length) indexTable[j] = 0;
                }

                // 실패 함수 테이블 제작
                int[] table = new int[W.length];
                int j=0;
                for (int i=1; i<W.length; i++) {
                    while (j>0 && W[i] != W[j]) {
                        j = table[j-1];
                    }
                    if (W[i] == W[j]) {
                        table[i] = ++j;
                    }
                }

//                System.out.println(Arrays.toString(table));
                // 일치하는 문자열 갯수 찾기 (KMP)
                j = 0;
                int tempCount = 0;
                for (int i=0; i<S.length; i++) {
                    while (j>0 && S[i] != W[j]) {
                        j = table[j-1];
                    }
                    if (S[i] == W[j]) {
                        if (j == W.length - 1) {
                            // pruning
                            if(++tempCount >= 2) break;
                            j = table[j];
                        }
                        else j++;
                    }
                }

                if (tempCount == 1) {
                    count++;
                    sb.append(r + " ");
                }

            }

//            System.out.println(count);
            switch (count) {
                case 0: {
                    System.out.println("no solution");
                    break;
                }
                case 1: {
                    System.out.println("unique: " + sb);
                    break;
                }
                default : {
                    System.out.println("ambiguous: " + sb);
                    break;
                }

            }

        }
    }

}