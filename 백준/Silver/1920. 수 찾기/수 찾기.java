import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] data;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		data = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			if (binarySearch(data, Integer.parseInt(st.nextToken())) > 0) {
				System.out.println("1");
			} else System.out.println("0");
		}

	}
	
	static int binarySearch(int[] arr, int key) {
		int start = 0;
		int end = arr.length - 1;
		
		while(start<=end) {
			int mid = (start + end) / 2;
			if (key == data[mid]) {
				return 1;
			} else if (key < data[mid]) {
				end = mid - 1;
			} else start = mid + 1;
		}
		
		return -1;
	}

}