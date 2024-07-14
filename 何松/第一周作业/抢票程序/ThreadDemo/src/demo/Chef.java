package demo;

public class Chef extends Thread {

    public Table table;

    public Chef() {}

    public Chef(Table table) {
        this.table = table;
    }

    public void run() {
        while (true) {
            synchronized (table.getLock()) {
                if (table.getNoodleCount() == 0) {
                    // 吃了10碗 吃不下了
                    break;
                } else {
                    if (table.isNoodleExisted()) {
                        // 有面条
                        try {
                            table.getLock().wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // 没有面条
                        System.out.println("厨师做了一碗面条");
                        table.setNoodleExisted(true);
                        table.getLock().notifyAll();
                    }
                }
            }
        }
    }

}
