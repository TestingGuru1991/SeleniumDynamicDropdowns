import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome", "C:\\Program Files\\Web Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //go to web site
        driver.get("https://www.spicejet.com/");

        //select cities
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='GOI']")).click();

        driver.findElement(By.xpath("(//a[@value='DEL'])[2]")).click();

        //select date
        driver.findElement(By.xpath("(//a[@value='DEL'])[2]")).click();
        int count = driver.findElements(By.className("ui-state-default")).size();
        for(int i=0; i<count; i++) {
            String text = driver.findElements(By.className("ui-state-default")).get(i).getText();
            if(text.equals("25")) {
                driver.findElements(By.className("ui-state-default")).get(i).click();
                break;
            }
        }

        //select currency
        Select s = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        s.selectByVisibleText("USD");

        //click on submit / find
        WebElement element = driver.findElement(By.name("ctl00$mainContent$btn_FindFlights"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        String expectedURL = "https://book.spicejet.com/Select.aspx";
        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.equals(expectedURL));

        driver.close();
    }
}
