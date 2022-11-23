package homework_1_expert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String line = "<client>(Какие то данные)<data>;Сидоров Василий Петрович;</data></client>";
        System.out.println(maskData(line));
    }

    public static String maskData(String line) {
        Pattern tag = Pattern.compile("<data>(.*?)</data>");
        Matcher data = tag.matcher(line);
        String result = data.find() ? data.group(1) : "";

        Pattern split = Pattern.compile("[^;]+");
        Matcher items = split.matcher(result);
        List<String> output = new ArrayList<>();

        while (items.find()) {
            String current = items.group();

            if (current.matches("[\\d]{11}")) {
                output.add(current.replaceFirst("(\\d{4})(\\d{3})(\\d*)", String.format("$1%s$3", "*".repeat(3))));
            } else if (current.matches("[А-ЯA-zа-яa-b\\s]*")) {
                String[] names = current.split(" ");
                Pattern p = Pattern.compile("([А-ЯA-Z])([а-яa-z]*)([а-яa-z]{1}$)");
                Matcher name = p.matcher(names[0]);
                names[0] = name.find() ? name.group(1) +
                        "*".repeat(name.end(2) - name.start(2)) + name.group(3) : "";
                names[2] = names[2].charAt(0) + ".";
                output.add(String.join(" ", names));
            } else if (current.matches(".+@.+")) {
                int begin = current.indexOf('@') + 1;
                int end = current.indexOf('.', begin);
                String replacement = "*@" + "*".repeat(end-begin);
                output.add(current.replaceAll("([\\w.-_](?=@))@([\\w_-]+(?=.))", replacement));
            }
        }

        String out = String.join(";", output);
        if (out.isEmpty()) return line;
        return line.replaceFirst("(.+(?<=<data>))(.+(?=</data>))(.+)", String.format("$1%s$3", out));
    }
}