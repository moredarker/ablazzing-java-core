package homework_4_expert;

public class NegativeCost extends Exception {
    public NegativeCost() {
        super("Цена меньше нуля");
    }
}
