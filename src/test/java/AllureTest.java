import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AllureTest {

    SelenideElement search = $(".header-search-button"),
                    searchField = $("#query-builder-test"),
                    issuesTab = $("#issues-tab");
    public static final String testRepo = "eroshenkoam/allure-example",
                               issue = "#80",
                                link = "https://github.com";

    @Test
    @Feature("Вкладка Issue в репозитории")
    @Story("Создание Issue")
    @Owner("kryukovvg")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка наличия Issue в репозитории")
    void cleanSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(link);
        search.click();
        searchField.setValue(testRepo).pressEnter();

        $(By.linkText(testRepo)).click();
        issuesTab.click();
        $(withText(issue)).should(Condition.exist);

    }

    @Test
    void lambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт", () -> open(link));

        step("Ищем тестовый репозиторий" + testRepo, () -> {
            search.click();
            searchField.setValue(testRepo).pressEnter();
        });

        step("Выбираем из списка необходимый репозиторий" + testRepo, () -> $(By.linkText(testRepo)).click());

        step("Открываем вкладку issues", () -> issuesTab.click());

        step("Проверяем наличие issue с нужным номером" + issue, () -> {
            $(withText(issue)).should(Condition.exist);
        });

    }

    @Test
    void webSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AllureWebSteps allureWebSteps = new AllureWebSteps();

        allureWebSteps.openPage();
        allureWebSteps.searchTestRepo();
        allureWebSteps.chooseTestRepo();
        allureWebSteps.openIssuesTab();
        allureWebSteps.checkExistenceIssueTab();
    }
}
