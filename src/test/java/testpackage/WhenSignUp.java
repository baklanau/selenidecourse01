package testpackage;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testpackage.steps.SignUpSteps;

@RunWith(SerenityRunner.class)
public class WhenSignUp {
    //private WebDriver driver;
    //private SignUpPageWD page;

//    @Before
//    public void setUp() {
//        String osName = System.getProperty("os.name");
//
//        System.out.println(osName);
//
//        if (osName.contains("Mac")) {
//            System.setProperty("webdriver.chrome.driver"
//                    , "/Users/dzb/IdeaProjects/selenidecourse01/drivers/chromedriver");
//
//            System.setProperty("selenium.browser", "Chrome");
//
//        } else if (osName.contains("Win")) {
//            System.setProperty("webdriver.chrome.driver"
//                    , "C:\\Users\\Dmitry B\\IdeaProjects\\selenidecourse01\\drivers\\chromedriver.exe");
//
//            System.setProperty("selenium.browser", "Chrome");
//
//        } else {
//            System.out.println("Add any drivers for browsers for your OS");
//        }
//
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//
//
//        driver.get("https://www.spotify.com/int/signup/");
//
//        //mainPage = new MainPage(driver);
//        page = PageFactory.initElements(driver, SignUpPageWD.class);
//    }

    @Steps
    SignUpSteps steps;

    @Managed
    WebDriver driver;

    @Test
    @Title("When the user types invalid year")
    public void typeInvalidYear() {
        steps.open_signup_page();
        steps.set_month("December");
        steps.type_day("20");
        steps.type_year("85");
        steps.set_share(true);

        steps.should_see_error("Please enter a valid year.");
        steps.should_not_see_error("When were you born?");

//        page = new SignUpPageWD(driver);
//        page.setMonth("December")
//                .typeDay("20")
//                .typeYear("85")
//                .setShare(true);
//        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
//        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test
    public void typeInvalidEmail() {
        steps.open_signup_page();
        steps.type_mail("aaa@aaa.by");
        steps.type_confirmation_email("bbb@bbb.by");
        steps.type_name("Name Test");
        steps.click_signup();
//        page = new SignUpPageWD(driver);
//        page.typeEmail("aaa@aaa.by")
//                .typeConfirmEmail("bbb@bbb.by")
//                .typeName("NameTest")
//                .clickSignUpButton();

        steps.should_see_error("Email address doesn't match.");
        //Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    @Pending
    public void signUpWithEmptyPassword() {
        steps.open_signup_page();
        steps.type_mail("aaa@aaa.by");
        steps.type_confirmation_email("aaa@aaa.by");
        steps.type_name("TestName");
        steps.click_signup();

//        page = new SignUpPageWD(driver);
//        page.typeEmail("aaa@aaa.by")
//                .typeConfirmEmail("aaa@aaa.by")
//                .typeName("TestName")
//                .clickSignUpButton();
        steps.should_see_error("Please choose a password.");
        //Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    @Pending
    public void typeInvalidValues() {
        steps.open_signup_page();
        steps.type_mail("aaa@aaa.by");
        steps.type_confirmation_email("aaa@aaa.by");
        steps.type_name("TestName");
        steps.type_password("qqqqqqqqq");
        steps.select_sex("Male");
        steps.set_share(false);
        steps.click_signup();

//        page = new SignUpPageWD(driver);
//        page.typeName("TestName")
//                .typeEmail("aaa@")
//                .typeConfirmEmail("aaa@a.by")
//                .typePassword("qqqqqqqqq")
//                .setSex("Male")
//                .setShare(false)
//                .clickSignUpButton();

        steps.should_see_errors_count(4);
        steps.should_see_error_by_number(3, "Please enter a valid year.");



        //Assert.assertEquals(6, page.getErrors().size());
        //Assert.assertEquals("Please enter your birth month.", page.getErrorByNumber(3));
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}
