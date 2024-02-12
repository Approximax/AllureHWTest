import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AllureWebSteps {

    SelenideElement search = $(".header-search-button"),
            searchField = $("#query-builder-test"),
            issuesTab = $("#issues-tab");
    public static final String testRepo = "eroshenkoam/allure-example",
            issue = "#80",
            link = "https://github.com";

    @Step("Открываем сайт")
    public void openPage() {
        open(link);
    }

    @Step("Ищем тестовый репозиторий {repo}")
    public void searchTestRepo() {
        search.click();
        searchField.setValue(testRepo).pressEnter();
    }

    @Step("Выбираем из списка необходимый репозиторий")
    public void chooseTestRepo() {
        $(By.linkText(testRepo)).click();
    }

    @Step("Открываем вкладку issues")
    public void openIssuesTab() {
        issuesTab.click();
    }

    @Step("Проверяем наличие issue с нужным номером")
    public void checkExistenceIssueTab() {
        $(withText(issue)).should(Condition.exist);
    }
}
