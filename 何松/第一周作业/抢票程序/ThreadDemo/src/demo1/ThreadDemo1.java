package demo1;

public class ThreadDemo1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadDemo1()).start();
        // 或者写得详细一点
        Thread thread = new Thread(new ThreadDemo1());
        thread.start();
    }

}
