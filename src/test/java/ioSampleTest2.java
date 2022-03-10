import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ioSampleTest2 {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    private String packageApp="com.splendapps.splendo";

    By addButton = By.id(packageApp+":id/fabAddTask");
    By editTaskNameTextbox = By.id(packageApp+":id/edtTaskName");
    By editDueDateTextbox = By.id("com.splendapps.splendo:id/edtDueD");
    By datePicker = By.id("android:id/datePicker");
    By saveTaskButton = By.id("com.splendapps.splendo:id/fabSaveTask");
    By taskNameView = By.id("com.splendapps.splendo:id/task_name");
    By editDueTimeTextbox = By.id("com.splendapps.splendo:id/edtDueT");
    By timePicker = By.id("android:id/radial_picker");
    By repeatDropdownlist = By.id("com.splendapps.splendo:id/spinnerRepeat");
    By addToListDropdownlist = By.id("com.splendapps.splendo:id/spinnerLists");

    Boolean isAndroid;


        @BeforeMethod
    @Parameters("android")
    public void setup(String android) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        isAndroid = android.equalsIgnoreCase("1");
//        isAndroid =  System.getenv("IS_ANDROID").equalsIgnoreCase("1")  ; // Get from env
        System.out.println("isAndroid: " + isAndroid);
        if (isAndroid) {
            // Case Android
            caps.setCapability("deviceName", "Pixel XL API 30");
            caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "11.0");
//            caps.setCapability("browserName","Chrome");
            caps.setCapability("skipUnlock", "true");
            caps.setCapability("appPackage", "com.splendapps.splendo");
            caps.setCapability("appActivity", "com.splendapps.splendo.MainActivity");
            caps.setCapability("noReset", "false");
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            // Get id for Android
            addButton = By.id("com.splendapps.splendo:id/fabAddTask");
            editTaskNameTextbox = By.id("com.splendapps.splendo:id/edtTaskName");
            editDueDateTextbox = By.id("com.splendapps.splendo:id/edtDueD");
            datePicker = By.id("android:id/datePicker");
            saveTaskButton = By.id("com.splendapps.splendo:id/fabSaveTask");
            taskNameView = By.id("com.splendapps.splendo:id/task_name");
            editDueTimeTextbox = By.id("com.splendapps.splendo:id/edtDueT");
            timePicker = By.id("android:id/radial_picker");
            repeatDropdownlist = By.id("com.splendapps.splendo:id/spinnerRepeat");
            addToListDropdownlist = By.id("com.splendapps.splendo:id/spinnerLists");

        } else {

            // Case iOS
            caps.setCapability("platformName", "iOS");
            caps.setCapability("platformVersion", "11.0");
            caps.setCapability("browserName","Chrome");
            caps.setCapability("skipUnlock", "true");
            caps.setCapability("noReset", "false");

            caps.setCapability("deviceName", "iPhone 6s");

            /*
            udid value must be set if you run your test on a real iOS device.
            The udid of your real device could be retrieved from Xcode->Windows->Devices and Simulators
            dialog.
            Usually, it is not enough to simply provide udid itself in order to automate apps
            on real iOS devices. You must also verify the target device is included into
            your Apple developer profile and the WebDriverAgent is signed with a proper signature.
            Refer https://github.com/appium/appium-xcuitest-driver/blob/master/docs/real-device-config.md
            for more details.
            */
            // capabilities.setCapability("udid", "ABCD123456789");

            /*
            This is the only supported automation backend for iOS
            */
            caps.setCapability("automationName", "XCUITest");


            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            // TODO: Get real ID on iOS
            addButton = By.id("com.splendapps.splendo:id/fabAddTask");
            editTaskNameTextbox = By.id("com.splendapps.splendo:id/edtTaskName");
            editDueDateTextbox = By.id("com.splendapps.splendo:id/edtDueD");
            datePicker = By.id("android:id/datePicker");
            saveTaskButton = By.id("com.splendapps.splendo:id/fabSaveTask");
            taskNameView = By.id("com.splendapps.splendo:id/task_name");
            editDueTimeTextbox = By.id("com.splendapps.splendo:id/edtDueT");
            timePicker = By.id("android:id/radial_picker");
            repeatDropdownlist = By.id("com.splendapps.splendo:id/spinnerRepeat");
            addToListDropdownlist = By.id("com.splendapps.splendo:id/spinnerLists");
        }

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void basicTest() throws InterruptedException {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editTaskNameTextbox)).sendKeys("Demo Function adding of To Do List");
        wait.until(ExpectedConditions.visibilityOfElementLocated(editDueDateTextbox)).click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(datePicker)).isDisplayed()) {
            if (isAndroid) {
                driver.findElement(By.xpath("//android.view.View[@content-desc = '10 March 2022']")).click();
                driver.findElement(By.xpath("//android.widget.Button[@text = 'OK']")).click();
            } else {
                // TODO: Get real iOS xpath
                driver.findElement(By.xpath("//android.view.View[@content-desc = '10 March 2022']")).click();
                driver.findElement(By.xpath("//android.widget.Button[@text = 'OK']")).click();
            }

        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(editDueTimeTextbox)).click();
//       Boolean shown = wait.until(ExpectedConditions.visibilityOfElementLocated(timePicker)).isDisplayed();
//       System.out.println("shown "+shown);
//        if (shown) {
//            driver.findElement(By.xpath("//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@content-desc='1']")).click();
//            driver.findElement(By.xpath("//android:id/button1")).click();
//        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(repeatDropdownlist)).click();
        if (isAndroid) {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ListView"))).isDisplayed()) {
                driver.findElement(By.xpath("//android.widget.TextView[@text='Once a Day']")).click();
            }
        } else {
            // TODO: Implement on real iOS
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ListView"))).isDisplayed()) {
                driver.findElement(By.xpath("//android.widget.TextView[@text='Once a Day']")).click();
            }
        }


        if (isAndroid) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToListDropdownlist)).click();
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ListView"))).isDisplayed()) {
                driver.findElement(By.xpath("//android.widget.TextView[@text='Personal']")).click();
            }
        } else {
            // TODO: Implement on real iOS
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
