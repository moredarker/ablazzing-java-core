import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //String line = "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344;my_mail@gmail.com</data></client>";
        //String line = "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>";
        //String line = "<client>(Какие то данные)<data></data></client>";
        String line = "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client>";

        Pattern tag = Pattern.compile("<data>(.*?)</data>");
        Matcher data = tag.matcher(line);

        String result = data.find() ? data.group(1) : "";

        Pattern split = Pattern.compile("[^;]+");
        Matcher items = split.matcher(result);
        StringBuilder output = new StringBuilder();

        while (items.find()) {
            String current = items.group();
            String firstChar = current.substring(0, 1);

            if (Pattern.matches("\\d", firstChar)) {
                String temp = current.substring(0, 4) + "***" + current.substring(7);
                output.append(temp + ";");
            } else if (Pattern.matches("[А-Я]", firstChar)) {
                String[] names = current.split(" ");
                String lastName = names[0];
                String temp = lastName.substring(0, 1) + "*".repeat(lastName.length() - 2) + lastName.substring(lastName.length() - 1);
                names[0] = temp;
                String fatherName = names[2].charAt(0) + ".";
                names[2] = fatherName;
                output.append(String.join(" ", names) + ";");
            } else if (Pattern.matches("[a-z]", firstChar)) {
                String[] parts = current.split("[@]+");
                StringBuilder left = new StringBuilder(parts[0]);
                left.setCharAt(left.length() - 1, '*');
                String right = "*".repeat(parts[1].indexOf("."));
                output.append(left + "@" + right + parts[1].substring(parts[1].indexOf(".")));
            }
        }

        Pattern beforeData = Pattern.compile(".+?(?=<data>)");
        Matcher left = beforeData.matcher(line);
        result = left.find() ? left.group() : "";
        result += "<data>" + output + "</data>";
        Pattern afterData = Pattern.compile("(?<=</data>).*$");
        Matcher right = afterData.matcher(line);
        result += right.find() ? right.group() : "";
        System.out.println(result);
    }
}