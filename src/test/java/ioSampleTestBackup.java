import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ioSampleTestBackup {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    By addButton = By.id("com.splendapps.splendo:id/fabAddTask");
    By editTaskNameTextbox = By.id("com.splendapps.splendo:id/edtTaskName");
    By editDueDateTextbox = By.id("com.splendapps.splendo:id/edtDueD");
    By datePicker = By.id("android:id/datePicker");
    By saveTaskButton = By.id("com.splendapps.splendo:id/fabSaveTask");
    By taskNameView = By.id("com.splendapps.splendo:id/task_name");
    By editDueTimeTextbox = By.id("com.splendapps.splendo:id/edtDueT");
    By timePicker = By.id("android:id/radial_picker");
    By repeatDropdownlist = By.id("com.splendapps.splendo:id/spinnerRepeat");
    By addToListDropdownlist = By.id("com.splendapps.splendo:id/spinnerLists");


    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel XL API 30");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("browserName","Chrome");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.splendapps.splendo");
        caps.setCapability("appActivity", "com.splendapps.splendo.MainActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void basicTest() throws InterruptedException {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editTaskNameTextbox)).sendKeys("Demo Function adding of To Do List");
        wait.until(ExpectedConditions.visibilityOfElementLocated(editDueDateTextbox)).click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(datePicker)).isDisplayed()) {
            driver.findElement(By.xpath("//android.view.View[@content-desc = '10 March 2022']")).click();
            driver.findElement(By.xpath("//android.widget.Button[@text = 'OK']")).click();
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(editDueTimeTextbox)).click();
//       Boolean shown = wait.until(ExpectedConditions.visibilityOfElementLocated(timePicker)).isDisplayed();
//       System.out.println("shown "+shown);
//        if (shown) {
//            driver.findElement(By.xpath("//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@content-desc='1']")).click();
//            driver.findElement(By.xpath("//android:id/button1")).click();
//        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(repeatDropdownlist)).click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ListView"))).isDisplayed()) {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Once a Day']")).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(addToListDropdownlist)).click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ListView"))).isDisplayed()) {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Personal']")).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveTaskButton)).click();
        String taskNameViewStr = wait.until(ExpectedConditions.visibilityOfElementLocated(taskNameView)).getText();
        Assert.assertTrue(taskNameViewStr.toLowerCase().contains("demo function adding of to do list"));


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
