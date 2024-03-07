import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Rock {
        int row, col;

        public Rock(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int n, T;
    static List<List<Integer>> rock = new ArrayList<>();

    static int bfs() {

        Queue<Rock> q = new ArrayDeque<>();
        q.offer(new Rock(0, 0));

        int ans = 0;
        while (!q.isEmpty()) {

            int qSize = q.size();
            // 현재 들어가 있는 모든 노드에 대해 검사
            for (int i = 0; i < qSize; i++) {
                Rock temp = q.poll();

                if (temp.col == T) {
                    return ans;
                }

                for (int y = temp.col - 2; y <= temp.col + 2; y++) {
                    if (y<0 || y > T) continue;

                    for (int x = 0; x < rock.get(y).size(); x++) {
                        if (temp.row + 2 < rock.get(y).get(x)) break;
                        else if (temp.row - 2 > rock.get(y).get(x)) continue;

                        q.add(new Rock(rock.get(y).get(x), y));
                        rock.get(y).remove(x); // x번째 암벽 제거
                        x--;
                    }
                }

            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T+1; i++) {
            rock.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            rock.get(y).add(x);
        }

        for (int i = 0; i < T+1; i++) {
            Collections.sort(rock.get(i));
        }

        System.out.println(bfs());


    }
}