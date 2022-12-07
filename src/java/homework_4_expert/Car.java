package homework_4_expert;

public class Car {
    private int cost;

    public Car(int cost) {
        this.cost = cost;
    }

    public void printCost() throws NegativeCost {
        if (cost < 0) {
            throw new NegativeCost();
        }

        System.out.print(cost);
    }
}
