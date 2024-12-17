import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class BookingTest {

    BookingPage bookingPage = new BookingPage();

    @BeforeAll
    static void setup() {
        // Настройка WebDriverManager для автоматической загрузки ChromeDriver
        WebDriverManager.edgedriver().setup();
    }

    @Test
    @Description("Тест на поиск отелей в Анталье и проверку 5 звезд")
    public void testSearchAndFilterFiveStarHotels() {
        open("https://booking.com/");

        bookingPage.searchForLocation("Анталья");

        // Проверяем, что в поиске отображается "Анталья"
        String searchResult = bookingPage.getSearchResultText();
        assert searchResult.contains("Анталья") : "Результаты поиска не содержат 'Анталья'";

        bookingPage.selectFiveStarFilter();

        // Убедимся, что все отели на данной странице имеют 5 звёзд
        boolean allHotelsAreFiveStars = bookingPage.areAllHotelsFiveStars();
        assert allHotelsAreFiveStars : "Не все отели имеют 5 звёзд";

        bookingPage.goToMap();

        // Здесь можно добавить логику для проверки информации о первом отеле на карте
        // Например, можно кликнуть на первый отель и проверить его детали
    }
}
