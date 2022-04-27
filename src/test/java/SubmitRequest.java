
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import java.time.Duration;



import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;




public class SubmitRequest {
    int delay = 15;

@NotNull
public String generateDate(int days, String formatPattern) {
    return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    @Test
        void shouldSubmitRequest() {
        String planningDate = generateDate(4, "dd.MM.yyyy");
            open("http://localhost:9999/");
            $("[data-test-id=city] .input__control").setValue("Москва");
            $("[data-test-id=date] [placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] [placeholder=\"Дата встречи\"]").setValue(planningDate);
            $("[data-test-id=name] [name=name]").setValue("Джейсон Вурхез");
            $("[data-test-id=phone] [name=phone]").setValue("+79859292566");
            $("[data-test-id=agreement]>.checkbox__box").click();
            $("button>.button__content").click();
            $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(delay)).shouldHave(exactText("Успешно! Встреча успешно забронирована на " + planningDate));
        }
    }
