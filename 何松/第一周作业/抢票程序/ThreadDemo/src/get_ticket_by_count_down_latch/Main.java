package get_ticket_by_count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{
    /*
  模拟场景.
  有10张票. 20个人准备抢这10张票, 在同一时间放票, 记录谁抢到了哪张票.
  使用CountDownLatch进行线程之间的同步.
  20个人有20个线程.
 */
    public static void main(String[] args) throws InterruptedException {
        // 准备20个线程代表人, 准备一个含有10个字符串的list代表抢票队列
        // 然后准备两个CountDownLatch, 一个用于放票, 一个用于通知主线程抢票结束
        CountDownLatch countGetTickets = new CountDownLatch(1);
        CountDownLatch countDownIfOver = new CountDownLatch(20);

        // 线程安全的容器
        AtomicInteger ticketCount = new AtomicInteger(10);


        for (int i = 0; i < 20; i++){
            // 设置线程名
            Thread thread = new Thread(new People(ticketCount, countGetTickets, countDownIfOver));
            thread.setName((i + 1) + "号");
            thread.start();
        }
        // 可选休眠, 输出结果更整齐
        Thread.sleep(1000);

        // 放票
        countGetTickets.countDown();

        countDownIfOver.await();
        System.out.println("抢票结束");

    }

    static class People extends Thread{
        // 使用队列存放票
        private final AtomicInteger ticketCount;

        private final CountDownLatch countDownGetTickets;

        private final CountDownLatch countDownIfOver;

        public People(AtomicInteger ticketCount, CountDownLatch countDownGetTickets, CountDownLatch countDownIfOver) {
            this.ticketCount = ticketCount;
            this.countDownGetTickets = countDownGetTickets;
            this.countDownIfOver = countDownIfOver;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在等待抢票...");
            try {
                countDownGetTickets.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (People.class) {
                int i = ticketCount.decrementAndGet();
                if (i >= 0) {
                    // 抢到了
                    System.out.println("第" + Thread.currentThread().getName() + "抢到票:" + i + "号票");
                } else {
                    // 没抢到
                    System.out.println("第" + Thread.currentThread().getName() + "没抢到票...");
                }
            }
            countDownIfOver.countDown();
        }
    }
}