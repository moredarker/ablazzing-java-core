package homework_4_expert;

public class CarShop {
    private Car car;

    public CarShop(Car car) {
        this.car = car;
    }

    public void sellCar() {
        System.out.println("Здравствуй, клиент, цена этого авто (вызвано из объекта CarShop)");

        try {
            car.printCost();
            System.out.println(" - (вызвано из объекта Car)");
            System.out.println("Хочешь купить авто? (вызвано из объекта Car)");
        } catch (NegativeCost e) {
            System.out.println("Неизвестна мне - (выдано из объекта CarShop)");
            System.out.println("Давайте посмотрим другое авто? (Вызвано из объекта CarShop)");
        }
    }
}
