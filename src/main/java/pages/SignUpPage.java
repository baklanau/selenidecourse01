package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    //private By emailField = By.cssSelector("input#register-email");
    private  By emailField = By.xpath("//input[@id='register-email']");
    private By confirmEmailField = By.cssSelector("input#register-confirm-email");
    //private By passField = By.cssSelector("input#register-password");
    private By passField = By.xpath("//input[@id='register-password']");
    //private By nameField = By.cssSelector("input#register-displayname");
    private By nameField = By.xpath("//input[@id='register-displayname']");
    private By monthDropDown = By.cssSelector("select#register-dob-month");
    private String monthDropDownOption = "//select/option[text()='%s']";
    private By dayField = By.cssSelector("input#register-dob-day");
    private By yearField = By.cssSelector("input#register-dob-year");
    private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    private By shareCheckBox = By.cssSelector("input#register-thirdparty");
    private By registerButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel = xpath("//label[@class='has-error' and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and contains(text(), \"%s\")]";

    public  SignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public  SignUpPage typeConfirmEmail(String email) {
        driver.findElement(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        driver.findElement(passField).sendKeys(password);
        return this;
    }

    public  SignUpPage typeName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public  SignUpPage setMonth(String month) {
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 5)
                .until(visibilityOfElementLocated(xpath(format(monthDropDownOption, month))))
                .click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        driver.findElement(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        driver.findElement(yearField).sendKeys(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        driver.findElement(xpath(format(sexRadioButton, value))).click();;
        return this;
    }

    public SignUpPage setShare(boolean value) {
        WebElement checkbox = driver.findElement(shareCheckBox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;
    }

    public void clickSignUpButton() {
        driver.findElement(registerButton).click();
        //return new SignUpPage(driver);
    }

    public List<WebElement> getErrors() {
        return driver.findElements(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message) {
        return driver.findElements(xpath(format(errorByText, message))).size() > 0 &&
                driver.findElements(xpath(format(errorByText, message))).get(0).isDisplayed();
    }


}
