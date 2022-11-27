package homework_3_advanced.task1;

public class Main {
    public static void main(String[] args) {
        Fir fir = new Fir();
        Pine pine = new Pine();
        Rose rose = new Rose();
        Fern fern = new Fern();

        HaveCone[] coneable = {fir, pine};
        Smell[] smellable = {fir, pine, rose};
        Bloom[] bloomable = {rose, fern};

        for (HaveCone haveCone : coneable) {
            System.out.print(haveCone + ": ");
            haveCone.haveCone();
        }

        for (Smell smell : smellable) {
            System.out.print(smell + ": ");
            smell.smell();
        }

        for (Bloom bloom : bloomable) {
            System.out.print(bloom + ": ");
            bloom.bloom();
        }
    }
}
