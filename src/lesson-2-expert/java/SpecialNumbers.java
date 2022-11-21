import java.util.HashSet;
import java.util.Set;

public class SpecialNumbers {
    public static void main(String[] args) {
        var data = GeneratorExpertHomework.getData();
        Set<String> specials = new HashSet<>();

        for (var outer : data.entrySet()) {
            for (var inner : outer.getValue().entrySet()) {
                for (String number : inner.getValue()) {
                    if (number.matches("М[\\d]{3}АВ[\\d]{2,3}")) {
                        specials.add(number);
                    }
                }
            }
        }

        System.out.println(specials.size() + " машин со спецномерами");
    }
}
