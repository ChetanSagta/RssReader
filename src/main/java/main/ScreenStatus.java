package main;

public class ScreenStatus {

    public static String status = "First";

    public static void clearScreen() {
        System.out.print("\033\143");
    }
}
