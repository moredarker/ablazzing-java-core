import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String maskData(String line) {
        Pattern tag = Pattern.compile("<data>(.*?)</data>");
        Matcher data = tag.matcher(line);
        String result = data.find() ? data.group(1) : "";

        Pattern split = Pattern.compile("[^;]+");
        Matcher items = split.matcher(result);
        List<String> output = new ArrayList<>();

        while (items.find()) {
            String current = items.group();
            String firstChar = current.substring(0, 1);

            if (Pattern.matches("\\d", firstChar)) {
                output.add(current.replaceFirst("(\\d{4})(\\d{3})(\\d*)", String.format("$1%s$3", "*".repeat(3))));
            } else if (Pattern.matches("[А-Я]", firstChar)) {
                String[] names = current.split(" ");
                String lastName = names[0];
                names[0] = lastName.charAt(0) + "*".repeat(lastName.length() - 2) + lastName.substring(lastName.length() - 1);
                names[2] = names[2].charAt(0) + ".";
                output.add(String.join(" ", names));
            } else if (Pattern.matches("[a-z]", firstChar)) {
                String[] parts = current.split("[@]+");
                StringBuilder left = new StringBuilder(parts[0]);
                left.setCharAt(left.length() - 1, '*');
                String right = "*".repeat(parts[1].indexOf("."));
                output.add(left + "@" + right + parts[1].substring(parts[1].indexOf(".")));
            }
        }

        String out = String.join(";", output);
        if (out.isEmpty()) return line;
        return line.replaceFirst("(.+(?<=<data>))(.+(?=</data>))(.+)", String.format("$1%s$3", out));
    }
}