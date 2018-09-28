//spotify.com Sign Up page

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import pages.SignUpPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignUpTest {
    private SignUpPage page;

    @BeforeClass
    public static void setUp() {
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

        baseUrl = "https://www.spotify.com/int/signup";
        browser = "chrome";
        //browser = "marionette"; //for FF(or "gecko")

    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage();
        page.open()
                .setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);

        page.getError("Please enter a valid year.").shouldBe(visible);
        page.getError("When were you born?").shouldBe(not(visible));
        page.getError("When were you born?").shouldNotBe(visible);

//        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
//        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test
    public void typeInvalidEmail() {
        page = new SignUpPage();
        page.open()
                .typeEmail("aaa@aaa.by")
                .typeConfirmEmail("bbb@bbb.by")
                .typeName("NameTest")
                .clickSignUpButton();

        page.getError("Email address doesn't match.").shouldBe(visible);

//        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    public void signUpWithEmptyPassword() {
        page = new SignUpPage();
        page.open()
                .typeEmail("aaa@aaa.by")
                .typeConfirmEmail("aaa@aaa.by")
                .typeName("TestName")
                .clickSignUpButton();

        page.getError("Please choose a password.").shouldBe(visible);
        //Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void typeInvalidValues() {
        page = new SignUpPage();
        page.open()
                .typeName("TestName")
                .typeEmail("aaa@")
                .typeConfirmEmail("aaa@a.by")
                .typePassword("qqqqqqqqq")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();

        page.getErrors().shouldHave(size(6));
        page.getErrorByNumber(3).shouldHave(text("Please enter your birth month."));

        //Assert.assertEquals(6, page.getErrors().size());
        //Assert.assertEquals("Please enter your birth month.", page.getErrorByNumber(3));

    }
}
