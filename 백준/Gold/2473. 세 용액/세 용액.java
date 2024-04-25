import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int i, j, fIdx, mIdx, eIdx;
	static List<Long> ansList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        
        // 용액 오름차순 정렬
        Arrays.sort(nums);

        long ans = Long.MAX_VALUE;
        
        
        for (int a=0; a<N; a++) {
        	i = 0;
            j = N-1;
            fIdx = 0;
            mIdx = 0;
            eIdx = N-1;
            
            while (i < j) {
            	
            	if (i == a) {
            		i++;
            		continue;
            	}
            	else if (j == a) {
            		j--;
            		continue;
            	}

                long cur = nums[a] + nums[i] + nums[j];
                
                // i번째 용액과 j번쨰 용액 사이에 있는 용액에 대해 완전탐색
                if (ans > Math.abs(cur)) {
                	ansList = new ArrayList<>();
                	ans = Math.abs(cur);
                	
                	ansList.add(nums[a]);
                	ansList.add(nums[i]);
                	ansList.add(nums[j]);
                }
                

                // 세 용액의 합이 0일 경우 -> 답
                if (ans == 0) break;

                // 세 용액의 합이 양수일 경우
                if (cur > 0) j--;
                // 세 용액의 합이 음수일 경우
                else i++;
            }
            
            if (ans == 0) break;
            
        }
        
        Collections.sort(ansList);
        System.out.println(ansList.get(0) + " " + ansList.get(1) + " " + ansList.get(2));
    }
}