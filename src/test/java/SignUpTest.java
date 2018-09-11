import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.SignUpPage;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        String osName = System.getProperty("os.name");

        System.out.println(osName);

        if (osName.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver"
                    , "/Users/dzb/IdeaProjects/selenidecourse01/drivers/chromedriver");

            System.setProperty("selenium.browser", "Chrome");

        } else if (osName.contains("Win")) {
            System.setProperty("phantomjs.binary.path"
                    , "C:\\Users\\Dmitry B\\IdeaProjects\\selenidecourse01\\drivers\\chromedriver.exe");

            System.setProperty("selenium.browser", "Chrome");

        } else {
            System.out.println("Add any drivers for browsers for your OS");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        driver.get("https://www.spotify.com/int/signup/");

        //mainPage = new MainPage(driver);
        page = PageFactory.initElements(driver, SignUpPage.class);
    }

    @Test
    public void typeInvalidYear() {
        //page = new SignUpPage(driver);
        page.setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test
    public void typeInvalidEmail() {
        //page = new SignUpPage(driver);
        page.typeEmail("aaa@aaa.by")
                .typeConfirmEmail("bbb@bbb.by")
                .typeName("NameTest")
                .clickSignUpButton();

        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    public void signUpWithEmptyPassword() {
        //page = new SignUpPage(driver);
        page.typeEmail("aaa@aaa.by")
                .typeConfirmEmail("aaa@aaa.by")
                .typeName("TestName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void typeInvalidValues() {
        //page = new SignUpPage(driver);
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

    @After
    public void tearDown() {
        driver.quit();
    }
}
