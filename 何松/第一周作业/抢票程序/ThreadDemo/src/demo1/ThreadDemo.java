package demo1;

public class ThreadDemo extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "hello world");
        }
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
//        demo1.ThreadDemo threadDemo1 = new demo1.ThreadDemo();
        threadDemo.setName("demo1.ThreadDemo");
//        threadDemo1.setName("ThreadDDemo");
        threadDemo.start();
//        threadDemo1.start();
    }
}
