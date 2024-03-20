import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.*;
public class Main {
	
	
	static int [] arr;
	static int findParent(int x) {
		if(arr[x]==x) return x;
		return arr[x] = findParent(arr[x]);
	}
	
	static void union(int a,int b) {
		int ap = findParent(a);
		int bp = findParent(b);
		if(ap<bp) arr[ap] = bp;
		else if(ap>bp) arr[bp] = ap;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		arr = new int[n+1];
		for(int i=0;i<=n;i++) arr[i] = i;
		int k = parseInt(br.readLine());
		String [] input;

		for(int i=0;i<n;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				if(i==j) continue;
				if(parseInt(input[j])==1) union(i+1,j+1);
			}
		}
		input = br.readLine().split(" ");
		boolean flag = true;
		int parent = findParent(parseInt(input[0]));
		for(int i=1;i<k;i++) {
			if(parent!= findParent(parseInt(input[i]))) {
				flag = false;
				break;
			}
		}
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}
}