package homework_3_expert;

public class Main {
    public static void main(String[] args) {
        Human human = Human.builder().name("Петр").age(20).weight(80).build();
        human.info();
    }
}
