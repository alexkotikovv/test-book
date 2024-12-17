import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class BookingPage {

    // Элементы страницы
    private SelenideElement searchField = $("input[name='ss']");
    private SelenideElement searchButton = $("button[type='submit']");
    private SelenideElement filterButton = $("button[data-testid='filter-button']");
    private SelenideElement fiveStarsCheckbox = $x("//div[@data-filters-item='class:class=5']");
    private SelenideElement mapButton = $("a[data-testid='map-button']");

    // Метод для поиска
    public void searchForLocation(String location) {
        searchField.setValue(location);
        searchButton.click();
    }

    // Метод для получения текста результата поиска
    public String getSearchResultText() {
        return $x("//h1").getText(); // используем более специфичный локатор
    }

    // Метод для выбора фильтра 5 звезд
    public void selectFiveStarFilter() {
        filterButton.click();
        fiveStarsCheckbox.scrollIntoView(true); // Прокручиваем к блоку со звездами
        fiveStarsCheckbox.click();
    }

    // Метод для проверки всех отелей на странице
    public boolean areAllHotelsFiveStars() {
        return $$(".sr_property_block").stream()
                .allMatch(hotel -> hotel.$x(".//span[contains(@class, 'bui-review-score__badge')]").getText().contains("5"));
    }

    // Метод для перехода на карту
    public void goToMap() {
        mapButton.click();
    }
}
