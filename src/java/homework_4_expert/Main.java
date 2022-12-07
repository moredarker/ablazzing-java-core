package homework_4_expert;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(5_000);
        CarShop carShop = new CarShop(car);
        carShop.sellCar();

        System.out.println("--- --- ---");

        Car ghostCar = new Car(-1);
        CarShop anotherCarShop = new CarShop(ghostCar);
        anotherCarShop.sellCar();
    }
}
