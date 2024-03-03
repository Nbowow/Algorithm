import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] region;
    static List<List<Integer>> people = new ArrayList<>();
    static boolean[] isVisited;
    static List<Integer> l1 = new ArrayList<>();
    static List<Integer> l2;
    static int[] parents;
    static int ans = Integer.MAX_VALUE;

    static int find(int x) {
        if (x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        for (int i = 0; i < people.get(a).size(); i++) {
            if (people.get(a).get(i) == b) {
                // 연결 가능
                int pa = find(a);
                int pb = find(b);

                // 부모에 연결 시켜줌
                parents[pa] = pb;
            }
        }
    }


    // 조합 만들기
    static void makeSet(int index) {
        if (index == N+1) {
            l2 = new ArrayList<>();
            makeL2();

//            System.out.println("=================");
//            System.out.println(l1.toString());
//            System.out.println(l2.toString());
            // 만든 조합에 대해 해당 구역끼리 연결될 수 있는지 검사
            parents = new int[N + 1];
            for (int i = 1; i < parents.length; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < l1.size(); i++) {
                for (int j = i+1; j < l1.size(); j++) {
                    if (find(l1.get(i)) != find(l1.get(j))) {
                        union(l1.get(i), l1.get(j));
                    }
                }
            }

//            System.out.println("before 1 : " + Arrays.toString(parents));
            int cnt1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                if (parents[l1.get(i)] == l1.get(i)) cnt1++;
            }

            // l2도 동일
            for (int i = 1; i < parents.length; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < l2.size(); i++) {
                for (int j = i+1; j < l2.size(); j++) {
                    if (find(l2.get(i)) != find(l2.get(j))) {
                        union(l2.get(i), l2.get(j));
                    }
                }
            }

//            System.out.println("before 2 : " + Arrays.toString(parents));
            int cnt2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                if (parents[l2.get(i)] == l2.get(i)) cnt2++;
            }

            // l1, l2 구역 모두 연결될 수 있는 경우
            int people1 = 0;
            int people2 = 0;
            for (int i = 0; i < l1.size(); i++) {
                people1 += region[l1.get(i)];
            }
            for (int i = 0; i < l2.size(); i++) {
                people2 += region[l2.get(i)];
            }

//            System.out.println("l1, l2 : " + people1 + " " + people2);

            // 자기 자신이 부모인 경우가 하나 밖에 없어야지 다 연결된 것
            if (cnt1 <= 1 && cnt2 <= 1) ans = Math.min(ans, Math.abs(people1 - people2));
            return;
        }

        // 해당 구역을 선택
        isVisited[index] = true;
        l1.add(index);
        makeSet(index + 1);
        isVisited[index] = false;
        l1.remove(l1.size() - 1);
        makeSet(index + 1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        region = new int[N + 1];
        isVisited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) { // 1번부터 N번 구역까지 인원 담음
            region[i] = Integer.parseInt(st.nextToken());
        }

        // 1번~ N번 구역과 연결된 구역 번호가 담긴 리스트
        for (int i = 0; i < N + 1; i++) {
            people.add(new ArrayList<>());
        }

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                people.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        makeSet(1);

        System.out.println(ans != Integer.MAX_VALUE ? ans : -1);

    }

    static void printList() {
        for (int i = 1; i < N + 1; i++) {
            System.out.println(people.get(i).toString());
        }
    }

    static void makeL2() {
        for (int i = 1; i < N + 1; i++) {
            if (!isVisited[i]) l2.add(i);
        }
    }
}