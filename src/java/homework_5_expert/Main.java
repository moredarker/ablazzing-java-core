package homework_5_expert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static String path = "src/java/homework_5_expert/reports";
    static String[] brands = DataGenerator.getShops();

    public static void main(String[] args) throws IOException {
        DataGenerator.createReports(path);

        String left = "report_";
        String order, filename;
        String right = "_" + DataGenerator.getYear() + ".txt";

        HashMap<String, Double> shops = new HashMap<>();
        for (String name : brands) {
            shops.put(name, 0d);
        }

        System.out.println("Прибыль по магазину pyterochka по месяцам");
        for (int i = 1; i < DataGenerator.getCountReports() + 1; i++) {
            order = String.format("%02d", i);
            filename = left + order + right;
            //метод для решения первой задачи
            getProfit(filename, order);
            //метод для заполнения мапы расходов для второй задачи
            getExpenses(filename, shops);
        }

        System.out.println("------");

        for (Map.Entry<String, Double> record: shops.entrySet())
            System.out.println("Расходы " + record.getKey() +
                    " за весь период: " + new DecimalFormat("###,###.##").format(record.getValue()));
    }

    public static void getProfit(String filename, String order) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "/" + filename))) {
            String line;
            double income = 0, expense = 0;

            while (reader.ready()) {
                line = reader.readLine();
                if (line.startsWith("pyterochka")) {
                    int begin = line.indexOf(";") + 1;
                    int mid = line.indexOf(";", begin);
                    int end = line.indexOf(";", mid + 1);
                    income += Double.parseDouble(line.substring(begin, mid));
                    expense += Double.parseDouble(line.substring(mid + 1, end));
                }
            }

            System.out.println(order + "." + DataGenerator.getYear() + ": " + new DecimalFormat("###,###.##").format(income - expense));
        }
    }

    public static void getExpenses(String filename, HashMap<String, Double> shops) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "/" + filename))) {
            String line;
            double expense;

            while (reader.ready()) {
                line = reader.readLine();
                try {
                    String[] data = line.split(";");
                    expense = Double.parseDouble(data[2]);
                    shops.put(data[0], shops.get(data[0]) + expense);
                } catch (NumberFormatException e) {
                    //игнор шапки файла
                }
            }
        }
    }
}
