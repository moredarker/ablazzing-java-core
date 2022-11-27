package homework_3_advanced.task2;

public class Main {
    public static void main(String[] args) {
        Store store = new Store(10);

        Worker first = new Worker("Ваня", store);
        Worker second = new Worker("Петя", store);
        Worker third = new Worker("Саша", store);

        first.destroy(4);
        second.destroy(3);
        third.destroy(2);
        first.destroy(2);
        second.destroy(1);
        third.destroy(0);

        System.out.println(first.getName() + " уничтожил " + first.getJournal());
        System.out.println(second.getName() + " уничтожил " + second.getJournal());
        System.out.println(third.getName() + " уничтожил " + third.getJournal());
        System.out.println("Остаток на складе: " + store.getVodka());
    }
}
