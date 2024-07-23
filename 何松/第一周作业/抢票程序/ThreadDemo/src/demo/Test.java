package demo;

public class Test {
    public static void main(String[] args) {
        Table table = new Table();
        Chef chef = new Chef(table);
        Eater eater = new Eater(table);

        chef.setName("厨师");
        eater.setName("吃货");

        chef.start();
        eater.start();
    }
}
