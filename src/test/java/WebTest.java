import com.codeborne.selenide.impl.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {


    @BeforeEach
    void setUp(){
        open("https://samcomsys.ru/");
    }

    @CsvFileSource(resources = "/test_data/checkCitiesInPopUpWindow.csv")
    @ParameterizedTest(name = "Из селлектора выбираем город {0} и переходим на его сайт {1}")
    @Tag("SMOKE")
    void checkCitiesInPopUpWindow(String cityName, String expectedLink) {

        $(".confirm").click();
        $("#cityselection").click();
        $("#cityselection").selectOption(cityName);


        $("#footer_center").shouldHave(text(expectedLink));//переделать проверку
    }



}
