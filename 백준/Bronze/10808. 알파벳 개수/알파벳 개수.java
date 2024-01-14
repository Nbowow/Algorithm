import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] alpha = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpha[(int)str.charAt(i)-97] ++;
        }
        for (int num : alpha) {
            System.out.print(num+" ");
        }
    }
}