package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;


public class SignUpPage {

    private By emailField = By.cssSelector("input#register-email");
    //private  By emailField = By.xpath("//input[@id='register-email']");
    //private By confirmEmailField = By.cssSelector("input#register-confirm-email");
    private By confirmEmailField = xpath("//input[@id='register-confirm-email']");
    private By passField = By.cssSelector("input#register-password");
    //private By passField = By.xpath("//input[@id='register-password']");
    private By nameField = By.cssSelector("input#register-displayname");
    //private By nameField = By.xpath("//input[@id='register-displayname']");
    private By monthDropDown = By.cssSelector("select#register-dob-month");
    private String monthDropDownOption = "//select/option[text()='%s']";
    private By dayField = By.cssSelector("input#register-dob-day");
    private By yearField = By.cssSelector("input#register-dob-year");
    private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    private By shareCheckBox = By.cssSelector("input#register-thirdparty");
    private By registerButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel = xpath("//label[@class='has-error' and string-length(text())>0]");
    private String errorByText = "//label[@class='has-error' and contains(text(), \"%s\")]";

    public SignUpPage open() {
        Selenide.open("/");
        return this;
    }

    public SignUpPage typeEmail(String email) {
        $(emailField).val(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        $(confirmEmailField).setValue(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        $(passField).setValue(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        $(nameField).val(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        $(monthDropDown).selectOption(month);
        return this;
    }

    public SignUpPage typeDay(String day) {
        $(dayField).setValue(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        $(yearField).setValue(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        $(xpath(format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        $(shareCheckBox).setSelected(value);
        return this;
    }

    public void clickSignUpButton() {
        $(registerButton).click();
    }

    public ElementsCollection getErrors() {
        return $$(errorLabel);
    }

    public SelenideElement getErrorByNumber(int number) {
        return getErrors().get(number - 1);
    }

//    public boolean isErrorVisible(String message) {
//        return $(xpath(format(errorByText, message))).isDisplayed();
//    }

    public SelenideElement getError(String message) {
        return $(xpath(format(errorByText, message)));
    }
}
