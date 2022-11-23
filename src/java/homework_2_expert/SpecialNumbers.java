package homework_2_expert;

import java.util.HashSet;
import java.util.Set;

public class SpecialNumbers {
    public static void main(String[] args) {
        var data = GeneratorExpertHomework.getData();
        Set<String> specials = new HashSet<>();

        //итерироваться по entrySet() избыточно для задачи?
        for (var outer : data.values()) {
            for (var inner : outer.values()) {
                for (String number : inner) {
                    if (number.matches("М[\\d]{3}АВ[\\d]{2,3}")) {
                        specials.add(number);
                    }
                }
            }
        }

        System.out.println(specials.size() + " машин со спецномерами");
    }
}
