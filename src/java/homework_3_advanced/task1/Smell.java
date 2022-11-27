package homework_3_advanced.task1;

public interface Smell {
    default void smell() {
        System.out.println("*пахну*");
    }
}
