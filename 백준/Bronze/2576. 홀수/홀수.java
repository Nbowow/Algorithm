import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minNum = 1000;
        int sum = 0;

        for (int i = 0; i < 7; i++) {
            int num = sc.nextInt();
            if (num % 2 != 0) {
                sum += num;
                minNum = Math.min(minNum, num);
            }
        }
        if (minNum == 1000) {
            System.out.println(-1);
        }
        else {
            System.out.println(sum);
            System.out.println(minNum);
        }
    }
}