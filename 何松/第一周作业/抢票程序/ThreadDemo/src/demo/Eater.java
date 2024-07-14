package demo;

public class Eater extends Thread{
    private final Table table;

    public Eater(Table table){
        this.table = table;
    }
    public void run(){
        while (true) {
            synchronized (table.getLock()) {
                if (table.getNoodleCount() == 0) {
                    // 吃了10碗 吃完了
                    break;
                }else {
                    // 判断是否有面条
                    if (table.isNoodleExisted()) {
                        // 有面条
                        table.setNoodleCount(table.getNoodleCount() - 1);
                        System.out.println("吃货在吃面条,还能吃" + table.getNoodleCount() + "碗.");
                        table.setNoodleExisted(false);
                        // 唤醒厨师继续做
                        table.getLock().notifyAll();
                    } else {
                        // 没有面条 等待
                        try {
                            table.getLock().wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        table.getLock().notifyAll();
                    }
                }
            }
        }
    }
}
