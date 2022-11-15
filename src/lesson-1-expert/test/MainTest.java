import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MainTest {
    @ParameterizedTest
    @MethodSource("lines")
    void maskData(String line, String expected) {
        String result = Main.maskData(line);

        assertEquals(expected, result);
    }

    static Stream<Arguments> lines() {
        return Stream.of(Arguments.of(
                "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>",
                "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>"),
                Arguments.of(
                "<client>(Какие то данные)<data></data></client>",
                "<client>(Какие то данные)<data></data></client>"
                ),
                Arguments.of(
                "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client>",
                "<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>"
                ),
                Arguments.of(
                "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344;my_mail@gmail.com</data></client>",
                "<client>(Какие то данные)<data>И****в Иван И.;7999***3344;my_mai*@*****.com</data></client>")
                );
    }
}