package Capstone.Capstone;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Selenium {
    private WebElement element;

    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "C:/chromedriver-win32/chromedriver.exe"; // WebDriver 경로

    //생성자
    public Selenium() throws IOException {
        chrome();
    }

    //ChromeDriver 연결
    private void chrome() throws IOException {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");

        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-protected-mode-settings");
        //options.addArguments("--remote-debugging-port=9222");
        options.setExperimentalOption("detach", true);

        // weDriver 생성.
        driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void useDriver(String url) {
        driver.get(url) ;
        try {
            Thread.sleep(1000);


            element= driver.findElement(By.className("framer-1enh8gy"));
            element.click();
            log.info("{}","로그인 클릭!");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  // 필요에 따라 대기 시간 설정
            String newPageHandle = driver.getWindowHandles().toArray()[1].toString();
            driver.switchTo().window(newPageHandle);


            WebElement firstButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/button[1]"));



            firstButton.click();
            log.info("{}","구글 로그인 클릭!");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            newPageHandle = driver.getWindowHandles().toArray()[1].toString();
            driver.switchTo().window(newPageHandle);

            WebElement textBox = driver.findElement(By.xpath("  //*[@id=\"identifierId\"]"));
            textBox.sendKeys("99kimst@gmail.com");
            element=driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
            element.click();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void quitDriver() {
        driver.quit(); // webDriver 종료
    }
}


