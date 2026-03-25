package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SignupPage
{
    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "first_name")
    WebElement First_Name;
    public void setFirst_Name(String FirstName){
        First_Name.clear();
        First_Name.sendKeys(FirstName);
    }
    @FindBy(id = "last_name")
    WebElement Last_Name;
    public void setLast_Name(String last_Name) {
        Last_Name.clear();
        Last_Name.sendKeys(last_Name);
    }
    @FindBy(id = "email")
    WebElement Email;
    public void setEmail(String email) {
        Email.clear();
        Email.sendKeys(email);
    }
    @FindBy(className = "selected-dial-code")
    WebElement Country_Code;

    public void setCountry_Code(String code) {
        // 1️⃣ Open dropdown
//        WebElement dropdown = wait.until(
//                ExpectedConditions.visibilityOf(Country_Code));
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block: 'center'});", Country_Code
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", Country_Code
//        );
        wait.until(ExpectedConditions.elementToBeClickable(Country_Code)).click();
        // 2️⃣ Clean code (+ remove kar do agar ho)
        String cleanCode = code.replace("+", "");

        // 3️⃣ Wait for country list (IMPORTANT - dropdown load hone do)
        WebElement list = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[contains(@class,'country-list')]")
                )
        );
        // 4️⃣ Locate country (SAME locator - no change)
        WebElement countryOption = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//li[@data-dial-code='" + cleanCode + "']")
                )
        );
        // 5️⃣ Scroll INSIDE dropdown (same as 1st code)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].parentElement.scrollTop = arguments[0].offsetTop;",
                countryOption
        );
        // 6️⃣ Click (same as 1st code - force JS click)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", countryOption
        );
    }

    @FindBy(id = "mobile")
    WebElement Contact_number;
    public void setContact_number(String number) {
        Contact_number.clear();
        Contact_number.sendKeys(number);
    }
    @FindBy(id="password")
    WebElement Password;
    public void setPassword(String password) {
       Password.clear();
       Password.sendKeys(password);
    }
    @FindBy(id="confirm_password")
    WebElement Comfirmed_Password;
    public void setConfirmed_Password(String Confirmed_password) {
        Comfirmed_Password.clear();
        Comfirmed_Password.sendKeys(Confirmed_password);
    }
    @FindBy(id="promo_code")
    WebElement Promo_Code;
    public void SetPromo_code(String Promo_code){
        Promo_Code.clear();
        Promo_Code.sendKeys(Promo_code);
    }
    @FindBy(id ="check1")
    WebElement checkbox;

    public void selectcheckbox(){
        // 1️⃣ Scroll element to center (best practice)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", checkbox
        );
        // 2️⃣ Wait until visible
        wait.until(ExpectedConditions.visibilityOf(checkbox));

        // 3️⃣ Direct JS click (avoid interception issue)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", checkbox
        );
    }
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submit;
    public void signup(){
        submit.click();
    }


    public void signup(String firstname, String lastname, String email, String code, String number, String password, String confirmedpassword, String promo) {
        setFirst_Name(firstname);
        setLast_Name(lastname);
        setEmail(email);
        setCountry_Code(code);
        setContact_number(number);
        setPassword(password);
        setConfirmed_Password(confirmedpassword);
        SetPromo_code(promo);
        selectcheckbox();
        signup();
    }
}
