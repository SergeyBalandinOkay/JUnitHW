import com.codeborne.selenide.Configuration;
import data.NamePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {


    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://chaconne.ru/");
    }

    @ValueSource(strings = {
            "Булгаков","Сорокин","Пелевин"
    })
    @ParameterizedTest(name = "Отображаются результаты поиска по фамилии {0}")
    @Tag("SMOKE")
    void searchResults(String productCard) {

        $("#search").setValue(productCard).pressEnter();
        $(".small").shouldHave(text("Поиск"));

    }


    @CsvSource(value = {
            "Булгаков, Булгаков",
            "Сорокин, Сорокин",
            "Пелевин, Пелевин"
    })
    @ParameterizedTest(name = "Для поискового запроса по фамилии {0} найдены конкретные книги автора {1}")
    @Tag("SMOKE")
    void searchResultsWithFullName(String productCard,String resultBooks) {

        $("#search").setValue(productCard).pressEnter();
        $(".grid-row-height.text-center").shouldHave(text(resultBooks));

    }

    @EnumSource(NamePage.class)
    @ParameterizedTest
    void theDesiredPageOpensWhenYouClickTheButton(NamePage namePage){
        $$(".header-nav a").find(text(namePage.selectionName)).click();
        $(".page_content").shouldHave(text(namePage.selectionName));
    }
}