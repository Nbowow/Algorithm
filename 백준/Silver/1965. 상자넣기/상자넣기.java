import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] box = new int[n];
		int[] ans = new int[n];
		for (int i=0; i<n; i++) {
			box[i] = sc.nextInt();
			ans[i] = 1;
		}
		int fans = 0;
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) {
				if (box[j] > box[i]) {
					ans[j] = Math.max(ans[j], ans[i]+1);
				}
			}
			fans = Math.max(fans, ans[i]);
		}
		
		fans = Math.max(fans, ans[n-1]);
//		for (int num : ans) {
//			System.out.print(num + " ");
//		}
		System.out.println(fans);
	}
}
