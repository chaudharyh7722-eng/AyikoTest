import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.SignupPage;

import java.time.Duration;

public class SignupTest
{
    WebDriver driver;
    WebDriverWait wait;
    SignupPage signupPage;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ayikostore.com/supplier/sign-up");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        signupPage = new SignupPage(driver);
    }
//    @AfterMethod
//    public void teardown(){
//        driver.close();
//    }
    @DataProvider(name = "SignupData") // Spelling Fixed
    public Object[][] getSignupData() {
        return new Object[][]{
                {"Kirti", "Singh", "kirti.testyopmail.com","+44", "7894561230", "System@123", "System@123", "RbRDDlNbkQa"}
                // 91 = India ka dial code (data-dial-code attribute value)
        };
    }
    @Test(dataProvider = "SignupData")
    public void signupSuccessTest(String fname, String lname, String email, String code, String mobile, String pwd, String spwd, String pro) {
        signupPage.signup(fname, lname, email, code, mobile, pwd, spwd, pro);
        //signupPage.setCountry_Code();
        // Next Step: Aap yahan Assertions add kar sakte ho
        // Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
}
