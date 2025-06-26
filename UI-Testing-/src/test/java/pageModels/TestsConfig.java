package pageModels;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class TestsConfig {
    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-cache");
        Configuration.browserCapabilities = options;
        Selenide.clearBrowserCookies();
    }
    @AfterAll
    public static void closeBrowser() {
        closeWindow();
    }
    @BeforeEach
    public void openPage() {
        open("/");
        localStorage().clear();
    }
}
