import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.Language;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MethodSourceWebTest {
    @BeforeEach
    void setUp() {
        open("https://msu.ru/");
        Configuration.browserSize = "1920x1080";
    }

    static Stream<Arguments> buttonsForLanguage() {
        return Stream.of(
                Arguments.of(Language.Eng, List.of("MSU Online", "Addresses", "Site map", "Search")),
                Arguments.of(Language.Рус, List.of("Карта",
                        "Медиа", "Контакты", "Информация", "Выпускники"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "При переключении языка отображаются кнопки на выбранном языке: {0}")
    @Tag("REGRESS")
    void buttonsForLanguage(Language language, List<String> expectedButtons) {

        $(".dropdown-block .btn.btn_text").click();
        $$(".dropdown-block__content .btn.btn_text")
                .findBy(text(language.getDisplayName()))
                .click();
        $$(".header__menu-center.menu.menu_horizontal.svelte-1irsy4n a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }
    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}