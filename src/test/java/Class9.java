import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Class9 {
    //1.
    @Test
    public void getSevenButonLocation_Test() {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dgotlieb.github.io/WebCalculator/");

        WebElement buttonSeven = driver.findElement(By.id("seven"));

        System.out.println("Height: " + buttonSeven.getRect().getHeight());
        System.out.println("Width: " + buttonSeven.getRect().getWidth());
        System.out.println("X: " + buttonSeven.getRect().getX());
        System.out.println("Y: " + buttonSeven.getRect().getY());

        driver.findElement(By.id("six")).isDisplayed();

        String number = "eight";
        driver.findElement(By.id("seven")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id(number)).click();
        driver.findElement(By.id("equal")).click();

        Assert.assertEquals("15", driver.findElement(By.id("screen")).getText());
    }

    //2.
    @Test
    public void checkURL_Test() {
        String url = "https://dgotlieb.github.io/WebCalculator/";
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    //3.
    @Test
    public void assertWebsiteTitle_Test() {
        String title = "Calculator";
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        driver.navigate().refresh();

        Assert.assertEquals(title, driver.getTitle());
    }

    //4.
    @Test
    public void assertWebsiteTitle_Headless_Test() {
        String title = "Calculator";
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://dgotlieb.github.io/WebCalculator/");

        driver.navigate().refresh();

        Assert.assertEquals(title, driver.getTitle());
    }

    //5.
    @Test
    public void actions_Test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://dgotlieb.github.io/Actions/");


        WebElement element = driver.findElement(By.id("div1"));
        File screenShotFile = element.getScreenshotAs(OutputType.FILE); // take the screenshot
        try {
            FileUtils.copyFile(screenShotFile, new File("element-screenshot.png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebElement buttonElement = driver.findElement(By.xpath("/html/body/p[2]"));
        Actions myAction = new Actions(driver);
        myAction.doubleClick(buttonElement);
        myAction.perform();
        Assert.assertEquals("You double clicked", driver.findElement(By.id("demo")).getText());

        myAction.moveToElement(driver.findElement(By.id("close")));
        myAction.build().perform();

        Select mySelection = new Select(driver.findElement(By.name("food")));
        mySelection.selectByIndex(0);
        mySelection.selectByIndex(1);
        myAction.build().perform();

        driver.findElement(By.name("pic")).sendKeys("/Users/nsananes/Dropbox/אושרי ונתנאלילה/קוד נתנאל/Class9/element-screenshot.png");

        WebElement clickme = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        clickme.click();
        driver.switchTo().alert().accept();

        clickme.sendKeys(Keys.HOME);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0, 250)");
    }

    //6.
    @Test
    public void controllers_Test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://dgotlieb.github.io/Controllers/");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[value='Cheese'")).click();

        Select mySelection = new Select(driver.findElement(By.name("dropdownmenu")));
        mySelection.selectByValue("Cheese");

        for (int i = 0; i < mySelection.getOptions().size() ; i++) {
            mySelection.selectByIndex(i);
            System.out.println(mySelection.getFirstSelectedOption().getText());
        }

    }

    //7.
    @Test
    public void WebCalculator_Test() {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dgotlieb.github.io/WebCalculator/");

        WebElement buttonTwo = driver.findElement(By.id("two"));
        WebElement buttonSix = driver.findElement(By.id("six"));

        System.out.println("Height two: " + buttonTwo.getRect().getHeight());
        System.out.println("Width six : " + buttonSix.getRect().getWidth());

    }

    //8.
    @Test
    public void WordCounter_Test() {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.themarker.com/");

        String str = driver.getPageSource();
        String findStr = "חדשות";
        int rowcount = str.split(findStr, 0).length-1;
        System.out.println("word count " + rowcount);

    }

    //9.
    @Test
    public void PrintWebsite_Test() {
        System.setProperty("webdriver.chrome.driver", "/Users/nsananes/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk-printing");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://google.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.print();");
    }
}
