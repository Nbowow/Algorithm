import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int idx;
        int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    static int N, M;
    static List<List<Node>> relation;
    static boolean[] isVisited;
    static int[] d;
    static StringBuilder sb;
    static int[] prev;

    static void dijkstra() {
    	// 이전 노드 초기화
    	for (int i=0; i<prev.length; i++) {
    		prev[i] = -1;
    	}
    	
        // 노드 거리 초기화
        for (int i = 1; i < M; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[0] = 0;

        // 노드 갯수만큼 반복
        for (int i = 0; i < M; i++) {

            // 해당 노드까지의 거리 최솟값을 찾기 위한 변수
            int nodeValue = Integer.MAX_VALUE;

            // 거리의 최솟값을 가지고 있는 노드의 인덱스
            int nodeidx = 0;

            // 방문하지 않은 노드 중 거리가 최소인 노드 찾기
            for (int j = 0; j < M; j++) {
                if (!isVisited[j] && d[j] < nodeValue) {
                    nodeValue = d[j];
                    nodeidx = j;
                }
            }
            
//            if (nodeidx == -1) break;
            
            // 거리 최솟값 노드 방문
            isVisited[nodeidx] = true;
            

            // 해당 노드와 연결된 모든 노드에 대해
            for (int j = 0; j < relation.get(nodeidx).size(); j++) {
                Node adjNode = relation.get(nodeidx).get(j);

                // 시작 노드에서 해당 노드를 거쳐 다른 노드로 갈 수 있다면
                if (d[adjNode.idx] > d[nodeidx] + adjNode.value) {
                    d[adjNode.idx] = d[nodeidx] + adjNode.value;
                    // 이전 노드 저장
                    prev[adjNode.idx] = nodeidx;
                }

            }
        }


    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            isVisited = new boolean[M];
            d = new int[M];
            prev = new int[M];
            
            relation = new ArrayList<>();

            // 정치인의 수
            for (int i = 0; i < M; i++) {
                relation.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                relation.get(x).add(new Node(y, z));
                // 양항뱡 간선이므로 꼭 추가해줘야함!! ... 이거 빠뜨려서 한시간동안 고민했음...ㅜ
                relation.get(y).add(new Node(x, z));
            }

            dijkstra();
            
            // 최고위원까지의 경로가 없다면
            if (prev[M-1] == -1) System.out.println("Case #" + tc + ": " + -1);
            else {
            	Stack<Integer> stack = new Stack<>();
            	int index = M-1;
            	while (index != -1) {
            		stack.push(index);
            		index = prev[index];
            	}
            	
            	while (!stack.isEmpty()) sb.append(stack.pop() + " ");
            	System.out.println("Case #" + tc + ": " + sb);
            }
            	

        }

    }
}