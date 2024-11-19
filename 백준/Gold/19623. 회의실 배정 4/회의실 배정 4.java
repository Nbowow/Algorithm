import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Room implements Comparable<Room>{
        int start, end, value;

        public Room(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Room o) {
            if (this.end == o.end) return Integer.compare(this.start, o.start);
            return Integer.compare(this.end, o.end);
        }
    }

    static int binarySearch(Room[] rooms, int currentIdx) {
        int start = 0, end = currentIdx - 1;
        int target = rooms[currentIdx].start;

        while (start <= end) {
            int mid = (start + end) / 2;
            // 더 뒤쪽 탐색
            if (rooms[mid].end <= target) {

                if (mid == currentIdx - 1 || rooms[mid + 1].end > target) {
                    return mid;
                }
                start = mid + 1;
            }
            // 더 앞쪽 탐색
            else {
                end = mid - 1;
            }
        }

        // i번째 회의보다 먼저 시작해서 끝나는 회의가 없는 경우
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Room[] rooms = new Room[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            rooms[i] = new Room(start, end, value);
        }

        // 회의 끝나는 시간 오름차순 정렬
        Arrays.sort(rooms);

        int[] dp = new int[N];
        dp[0] = rooms[0].value;

        for (int i = 1; i < N; i++) {

            // 현재 회의 선택 x
            dp[i] = dp[i-1];

            int lastIdx = binarySearch(rooms, i);
            if (lastIdx == -1) dp[i] = Math.max(dp[i], rooms[i].value);
            else dp[i] = Math.max(dp[i], rooms[i].value + dp[lastIdx]);

        }

        System.out.println(dp[N-1]);

    }
}