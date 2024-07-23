package demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + " Hello World";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo3 threadDemo3 = new ThreadDemo3();
        FutureTask<String> futureTask = new FutureTask<>(threadDemo3);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
