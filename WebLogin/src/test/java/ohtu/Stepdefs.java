package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

//GIVEN
    @Given("^new user is selected$")
    public void new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is successfully created$")
    public void user_with_username_and_password_is_successfully_created(String username, String password) throws Throwable {
        new_user_is_selected();
        registerNewUserWith(username, password, password);
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is unsuccessfully created$")
    public void user_with_username_and_password_is_unsuccessfully_created(String username, String password) throws Throwable {
        new_user_is_selected();
        registerNewUserWith(username, password, password);
    }

//WHEN LOGIN    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void incorrect_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

//WHEN REGISTER    
    @When("^correct username \"([^\"]*)\" and valid password \"([^\"]*)\" are given$")
    public void correct_username_and_valid_password_are_given(String username, String password) throws Throwable {
        registerNewUserWith(username, password, password);
    }

    @When("^correct username \"([^\"]*)\" and invalid password \"([^\"]*)\" are given$")
    public void correct_username_and_invalid_password_are_given(String username, String password) throws Throwable {
        registerNewUserWith(username, password, password);
    }

    @When("^incorrect username \"([^\"]*)\" and valid password \"([^\"]*)\" are given$")
    public void incorrect_username_and_valid_password_are_given(String username, String password) throws Throwable {
        registerNewUserWith(username, password, password);
    }

    @When("^correct username \"([^\"]*)\" and valid password \"([^\"]*)\" and invalid passwordConfirmation \"([^\"]*)\" are given$")
    public void invalid_passwordConfirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        registerNewUserWith(username, password, passwordConfirmation);
    }

//THEN
    @Then("^user is created$")
    public void user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        pageHasContent("Create username and give password");
        pageHasContent(error);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

//AFTER
    @After
    public void tearDown() {
        driver.quit();
    }

//HELPER METHODS
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void registerNewUserWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
