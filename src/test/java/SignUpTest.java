//spotify.com Sign Up page

import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import pages.SignUpPageWD;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.

public class SignUpTest {
    private SignUpPage page;

    @BeforeClass
    public void setUp() {
        String osName = System.getProperty("os.name");

        System.out.println(osName);

        if (osName.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver"
                    , "/Users/dzb/IdeaProjects/selenidecourse01/drivers/chromedriver");

            System.setProperty("selenium.browser", "Chrome");

        } else if (osName.contains("Win")) {
            System.setProperty("webdriver.chrome.driver"
                    , "C:\\Users\\Dmitry B\\IdeaProjects\\selenidecourse01\\drivers\\chromedriver.exe");

            System.setProperty("selenium.browser", "Chrome");

        } else {
            System.out.println("Add any drivers for browsers for your OS");
        }

        baseUrl = "https://www.spotify.com/int/signup/";
        browser = "chrome";

    }

    @Test
    public void typeInvalidYear() {
        //page = new SignUpPageWD(driver);
        page.setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test
    public void typeInvalidEmail() {
        //page = new SignUpPageWD(driver);
        page.typeEmail("aaa@aaa.by")
                .typeConfirmEmail("bbb@bbb.by")
                .typeName("NameTest")
                .clickSignUpButton();

        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    public void signUpWithEmptyPassword() {
        //page = new SignUpPageWD(driver);
        page.typeEmail("aaa@aaa.by")
                .typeConfirmEmail("aaa@aaa.by")
                .typeName("TestName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void typeInvalidValues() {
        //page = new SignUpPageWD(driver);
        page.typeName("TestName")
                .typeEmail("aaa@")
                .typeConfirmEmail("aaa@a.by")
                .typePassword("qqqqqqqqq")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();


        Assert.assertEquals(6, page.getErrors().size());
        Assert.assertEquals("Please enter your birth month.", page.getErrorByNumber(3));

    }
}
