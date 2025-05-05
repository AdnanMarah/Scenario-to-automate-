package almosafer.almosafer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v133.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class App {

    WebDriver driver = new ChromeDriver();
    Random rand = new Random();
    WebDriverWait wait;

    String ExpectedLanguage = "en";
    String ExpectedCurrency = "SAR";
    String ExpectedContactNumber = "+966554400000";

    @BeforeTest
    public void mySetup() {
        driver.get("https://www.almosafer.com/en?ncr=1");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @Test(priority = 1) // CheckTheDefaultLanguageIsEnglish
    public void CheckTheDefaultLanguageIsEnglish() throws InterruptedException {
        String actualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
        Assert.assertEquals(actualLanguage, ExpectedLanguage, "اللغة الافتراضية ليست EN");
        Thread.sleep(3000);
    }
    @Test(priority = 2) // CheckDefaultCurrencyIsSAR
    public void CheckDefaultCurrencyIsSAR() throws InterruptedException {
        String actualCurrency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();
        Assert.assertEquals(actualCurrency, ExpectedCurrency, "Virtual currency is not SAR");
        System.out.println(actualCurrency);
        Thread.sleep(3000);
    }

    @Test(priority = 3) // CheckTheContactNumber
    public void CheckTheContactNumber() throws InterruptedException {
        WebElement contactNumberElement = driver.findElement(By.cssSelector(".sc-cjHlYL.gdvIKd"));
        String actualContactNumber = contactNumberElement.getText();
        System.out.println(actualContactNumber);
        Assert.assertEquals(actualContactNumber, ExpectedContactNumber, "Invalid contact number");
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void TestVerifyQitafLogoInFooter() throws InterruptedException {
        driver.get("https://www.almosafer.com/en");
        Thread.sleep(2000);         // الانتظار لمدة 2 ثانية

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(2000);  
        WebElement qitafLogo = driver.findElement(By.cssSelector("[data-testid='Footer__QitafLogo']"));
        System.out.println(qitafLogo.isDisplayed() ? "The Qitaf logo was found in the footer"+ "." : "No Qitaf logo found in the footer"+ "");

    }

    @SuppressWarnings("deprecation")
	@Test(priority = 5) // checkHotelsTabNotSelectedByDefault
    public void checkHotelsTabNotSelectedByDefault() throws InterruptedException {
        WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        String className = hotelsTab.getAttribute("class");
        System.out.println("Hotels Tab class: " + className);
        Assert.assertFalse(className.contains("active"), "Hotels tab is enabled by default");
        Thread.sleep(3000);
    }

    @Test(priority = 6) // checkDepartureDateIsDayAfterTomorrow
    public void checkDepartureDateIsDayAfterTomorrow() throws InterruptedException {
        String departureDateText = driver.findElement(By.cssSelector("[data-testid='FlightSearchBox__FromDateButton']"))
                .getText().trim();
        String expectedDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMM dd"));
        String actualDate = departureDateText.split("\\s+")[0] + " " + departureDateText.split("\\s+")[1];
        Assert.assertTrue(actualDate.contains(expectedDate),
                "Expected: " + expectedDate + " but found: " + actualDate);
        Thread.sleep(3000);
    }

    @Test(priority = 7) // checkReturnDateIsDayAfterTomorrow
    public void checkReturnDateIsDayAfterTomorrow() throws InterruptedException {
        WebElement returnDateElement = driver.findElement(By.cssSelector("[data-testid='FlightSearchBox__ToDateButton']"));
        String actualDate = returnDateElement.getText().replaceAll("\\s+", " ").trim();
        String expectedDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MMM dd"));
        System.out.println("Actual Return Date: " + actualDate);
        Assert.assertTrue(actualDate.contains(expectedDate),
                "Expected return date: " + expectedDate + ", but found: " + actualDate);
        Thread.sleep(3000);
    }

    @Test(priority = 8) // Use random method to change language
    public void changeLanguageRandomly() throws InterruptedException {
        WebElement languageButton = driver.findElement(By.className("sc-imABML"));
        String currentLanguage = languageButton.getText().trim();
        System.out.println("Current Language: " + currentLanguage);
        boolean changeLanguage = Math.random() < 0.5;

        if (changeLanguage) {
            languageButton.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(languageButton, currentLanguage)));
            languageButton = driver.findElement(By.className("sc-imABML"));
            String newLanguage = languageButton.getText().trim();
            System.out.println("New Language after change: " + newLanguage);
            Assert.assertNotEquals(newLanguage, currentLanguage, "Language should have changed.");
        } else {
            System.out.println("Language is still: " + currentLanguage);
            String currentLanguageAfterCheck = languageButton.getText().trim();
            Assert.assertEquals(currentLanguage, currentLanguageAfterCheck, "Language should remain the same.");
        }
        Thread.sleep(3000);
    }

    @Test(priority = 9) // Switch to hotel search tab
    public void switchToHotelTab() throws InterruptedException {
        WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        String URL = driver.getCurrentUrl();
        hotelTab.click();
        Thread.sleep(1000);

        WebElement placeInputField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

        List<String> cities = URL.contains("/en")
                ? Arrays.asList("Dubai", "Jeddah", "Riyadh")
                : Arrays.asList("دبي", "جدة", "الرياض");

        placeInputField.sendKeys(cities.get(rand.nextInt(cities.size())));
        Thread.sleep(1000);
        placeInputField.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
        Thread.sleep(3000);
    }
    

    @Test(priority = 10) // Random Stay
    public void TestSelectRandomStayOption() throws InterruptedException {
    WebElement staysTab = driver.findElement(By.cssSelector("a[data-rb-event-key='hotels']"));
    staysTab.click();
    Thread.sleep(2000); 

    WebElement reservationDropdown = driver.findElement(By.cssSelector("select[data-testid='HotelSearchBox__ReservationSelect_Select']"));
     Select select = new Select(reservationDropdown);
      List<WebElement> options = select.getOptions();

       int maxIndex = options.size() - 1; 
       int randomIndex = new Random().nextInt(maxIndex);

        select.selectByIndex(randomIndex);
        System.out.println("Random selection"+ ": " + options.get(randomIndex).getText());
    }
    

  @Test(priority = 11) // Click on Search Hotels button
    public void clickOnSearchHotelsButton() throws InterruptedException {
    Thread.sleep(2000);
    WebElement searchButton = driver.findElement(By.cssSelector("button[data-testid='HotelSearchBox__SearchButton']"));
    searchButton.click();
    Thread.sleep(5000);
} 

  
   @Test(priority = 12)
   public void waitForHotelsResultsToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    List<WebElement> results = driver.findElements(By.cssSelector("div[data-testid='HotelSearchResultCard']"));
    System.out.println("عدد نتائج البحث: " + results.size());
}
 

}