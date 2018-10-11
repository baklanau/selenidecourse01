package testpackage.steps;


import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import testpackage.pages.SignUpPage;


public class SignUpSteps {
    private SignUpPage page;

    @Step
    public void open_signup_page() {
        page.open();
    }

    @Step("User types email {0}")
    public void type_mail(String mail) {
        page.typeEmail(mail);
    }

    @Step("User types confirmation email {0}")
    public void type_confirmation_email(String mail) {
        page.typeConfirmEmail(mail);
    }

    @Step("User types password {0}")
    public void type_password(String password) {
        page.typePassword(password);
    }

    @Step("User types name {0}")
    public void type_name(String name) {
        page.typeName(name);
    }

    @Step("User sets mounth {0}")
    public void set_month(String month) {
        page.setMonth(month);
    }

    @Step("User types day {0}")
    public void type_day(String day) {
        page.typeDay(day);
    }

    @Step("User types year {0}")
    public void type_year(String year) {
        page.typeYear(year);
    }

    @Step("User select sex {0}")
    public void select_sex(String sex) {
        page.setSex(sex);
    }

    @Step
    public void set_share(boolean value) {
        page.setShare(value);
    }

    @Step
    public void click_signup() {
        page.clickSignUpButton();
    }

    @Step
    public void should_see_error(String message) {
        Assertions.assertThat(page.isErrorVisible(message))
                .as("Fail test")
                .isTrue();
    }

    @Step
    public void should_not_see_error(String message) {
        Assertions.assertThat(page.isErrorVisible(message))
                .as("Fail test")
                .isFalse();
    }

    @Step
    public void should_see_errors_count(int count) {
        Assertions.assertThat(page.getErrors()).hasSize(count);
    }

    @Step
    public void should_see_error_by_number(int number, String message) {
        Assertions.assertThat(page.getErrorByNumber(number)).isEqualTo(message);
    }

}
