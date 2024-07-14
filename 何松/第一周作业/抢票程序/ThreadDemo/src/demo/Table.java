package demo;


public class Table {
    private boolean isNoodleExisted = false;

    private int noodleCount = 10;

    private Object lock = new Object();

    public Table() {
    }

    public Table(boolean isNoodleExisted, int noodleCount, Object lock) {
        this.isNoodleExisted = isNoodleExisted;
        this.noodleCount = noodleCount;
        this.lock = lock;
    }

    public boolean isNoodleExisted() {
        return isNoodleExisted;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public void setNoodleExisted(boolean noodleExisted) {
        isNoodleExisted = noodleExisted;
    }

    public int getNoodleCount() {
        return noodleCount;
    }

    public void setNoodleCount(int noodleCount) {
        this.noodleCount = noodleCount;
    }
}
