import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class Time implements Comparable<Time>{
        int index;
        int time;

        public Time(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Time o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static class Stair{
        int row;
        int col;
        // 계단의 길이
        int k;
        public Stair(int row, int col, int k) {
            this.row = row;
            this.col = col;
            this.k = k;
        }
    }

    static class Person implements Comparable<Person>{
        int row;
        int col;
        // 계단 도착 시각
        int t;

        public Person(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Person o) {
            // 시간기준 오름차순 정렬
            return Integer.compare(this.t, o.t);
        }
    }

    static int N, pCount;
    static int[][] map;
    static List<Person> persons;
    static List<Stair> stairs;
    static List<Integer> l1, l2;
    static List<Integer> ans;
    static Queue<Time> q1, q2;
    static int[] a1, a2;

    // 계단 내려가기
    static void goDown() {
        int[] arr1 = new int[l1.size()];
        int[] arr2 = new int[l2.size()];

        for (int i = 0; i < l1.size(); i++) {
            arr1[i] = l1.get(i);
        }

        for (int i = 0; i < l2.size(); i++) {
            arr2[i] = l2.get(i);
        }

        // 시간 순으로 정렬
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();

        for (int i = 0; i < l1.size(); i++) {
            q1.offer(new Time(i, arr1[i]));
        }

        for (int i = 0; i < l2.size(); i++) {
            q2.offer(new Time(i, arr2[i]));
        }

        // 각 index번호의 사람마다 내려간 계단 길이를 가지고 있는 배열
        a1 = new int[q1.size()];
        a2 = new int[q2.size()];

        // 시간의 경과에 따른 시뮬레이션
        int t = 0;
        while (true) {
            // 1번 계단
            int k1 = stairs.get(0).k;
            Queue<Time> temp1 = new ArrayDeque<>();
            // 계단은 최대 3명 가능
            for (int i = 0; i < 3; i++) {
                if (q1.isEmpty() || q1.peek().time >= t) break;

                Time p1 = q1.poll();
                // 계단을 내려가며, 계단 내려가기 시작한 시각부터 시간이 K보다 덜 지났을 때
                if (a1[p1.index] < k1) {
                    // 계단 내려가는 시간 늘려주고
                    // k1 시간이 되지 않았을 경우만 큐에 넣어줌
                    if (++a1[p1.index] != k1) temp1.offer(p1);
                }
                // 계단 내려갈 수 없으면 그냥 저장
                else temp1.offer(p1);
            }
            while (!q1.isEmpty()) temp1.offer(q1.poll());
            // 재투입
            while (!temp1.isEmpty()) q1.offer(temp1.poll());

            // 2번 계단
            int k2 = stairs.get(1).k;
            Queue<Time> temp2 = new ArrayDeque<>();
            // 계단은 최대 3명 가능
            for (int i = 0; i < 3; i++) {
                if (q2.isEmpty() || q2.peek().time >= t) break;

                Time p2 = q2.poll();

                // 계단 내려갈 수 있을 때
                if (a2[p2.index] < k2) {
                    // k2 시간이 되지 않았을 경우에만 다시 큐에 넣어줌
                    if (++a2[p2.index] != k2) temp2.offer(p2);
                } else temp2.offer(p2);
            }
            while (!q2.isEmpty()) temp2.offer(q2.poll());
            while (!temp2.isEmpty()) q2.offer(temp2.poll());

            // a1, a2의 마지막 인덱스가 각각 k1, k2이면 조건 만족
            boolean isAFin = true;
            if (a1.length != 0) {
                isAFin = false;
                if (a1[a1.length-1] == k1) isAFin = true;
            }

            boolean isBFin = true;
            if (a2.length != 0) {
                isBFin = false;
                if (a2[a2.length-1] == k2) isBFin = true;
            }

            if (isAFin && isBFin) break;
            t++;
        }

        ans.add(t);
    }

    static void dfs(int index) {
        // 모든 사람이 계단에 도착하였을 때
        if (index == pCount) {
            // 계단 내려감
            goDown();
            return;
        }
        Person p = persons.get(index);

        int x = p.row;
        int y = p.col;

        // 1번 계단으로 이동할 시
        Stair s1 = stairs.get(0);
        int s1r = s1.row;
        int s1c = s1.col;
        int timeToS1 = Math.abs(x-s1r) + Math.abs(y-s1c);
        // 계단 내려갈 수 있는 시각 리스트에 담아줌
        l1.add(timeToS1+1);
        dfs(index + 1);
        l1.remove(l1.size() - 1);

        // 2번 계단으로 이동할 시
        Stair s2 = stairs.get(1);
        int s2r = s2.row;
        int s2c = s2.col;
        int timeToS2 = Math.abs(x-s2r) + Math.abs(y-s2c);
        // 계단 내려갈 수 있는 시각 리스트에 담아줌
        l2.add(timeToS2+1);
        dfs(index + 1);
        l2.remove(l2.size() - 1);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            pCount = 0;
            persons = new ArrayList<>();
            stairs = new ArrayList<>();
            l1 = new ArrayList<>();
            l2 = new ArrayList<>();
            ans = new ArrayList<>();

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 사람
                    if (map[i][j] == 1) {
                        pCount++;
                        persons.add(new Person(i, j));
                    }
                    // 계단
                    else if (map[i][j] > 1) {
                        stairs.add(new Stair(i, j, map[i][j]));
                    }
                }
            }
            dfs(0);

            Collections.sort(ans);
            System.out.println("#" + tc + " " + ans.get(0));
        }

    }

}