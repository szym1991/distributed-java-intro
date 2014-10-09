package exercise1;
import java.lang.Thread;

public class Main {

    public static void main(String[] args) {
        System.out.print("Thread id: ");
        System.out.println(Thread.currentThread().getId());
        System.out.print("Thread name: ");
        System.out.println(Thread.currentThread().getName());
    }
}
