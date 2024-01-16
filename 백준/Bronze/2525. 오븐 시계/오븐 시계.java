import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int plusTime = (B+C) / 60;
        int minute = (B+C) % 60;

        int time = (A+plusTime) % 24;

        System.out.println(time + " " + minute);
    }
}