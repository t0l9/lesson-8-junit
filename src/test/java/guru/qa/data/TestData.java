package guru.qa.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class TestData {

    public static Stream<Arguments> MainPageChoiceLanguageTest(){

        return Stream.of(
                Arguments.of(Language.EN, List.of("Login BCC Business", "Continue", "Phone number")),
                Arguments.of(Language.RU, List.of("Вход в BCC Business", "Продолжить", "Номер телефона")),
                Arguments.of(Language.QAZ, List.of("BCC Business кіру", "Жалғастыру", "Телефон нөмірі"))

        );
    }
}
