package ca.testng.practice.testcases;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import javax.tools.StandardJavaFileManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.PrintStream;

public class VideoPlayback extends BSBase {

    @Test
    public void test() throws MalformedURLException, InterruptedException, FileNotFoundException {

        //click Skip button
        WebElement skipButton = driver.findElement(MobileBy.AndroidUIAutomator("text(\"SKIP\")"));
        skipButton.click();
        //driver.findElement(By.id("com.shawmedia.smglobal:id/home_page_video_art_view")).click();

        //Find Latest Episodes Collection - must end with /..
        WebElement latestEpisodesCollection = driver.findElement(By.xpath("//android.widget.TextView[@text='Latest Global TV Episodes']/.."));

        //find webelements within Latest Episodes collection
        List<WebElement> listOfThumbnails = latestEpisodesCollection.findElements(By.id("com.shawmedia.smglobal:id/home_page_video_layout"));
        swipeHorizontal(listOfThumbnails.get(1), listOfThumbnails.get(0));
        List<WebElement> listOfThumbnails1 = latestEpisodesCollection.findElements(By.id("com.shawmedia.smglobal:id/home_page_video_layout"));
        swipeHorizontal(listOfThumbnails1.get(1), listOfThumbnails1.get(0));

       //Find episode thumbnail to open player

        listOfThumbnails1.get(0).findElement(By.id("com.shawmedia.smglobal:id/home_page_video_art_view")).click();
        Thread.sleep(3000);

       // tap on palyer to show Control rack
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Show player controls\"]/android.widget.FrameLayout[1]/android.webkit.WebView/android.webkit.WebView/android.view.View\n")).click();

        //tap on Pause button to pause playback
        driver.findElement(By.id("com.shawmedia.smglobal:id/exo_pause")).click();
        Thread.sleep(2000);

        //click Back button to close the player
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton\n")).click();
        //or
        //driver.findElement(By.id("com.shawmedia.smglobal:id/player_back_button")).click();
        Thread.sleep(3000);


        //scroll down - 1 method
        // calculate bottom & top of the screen
        Dimension size = driver.manage().window().getSize();
        int middleX = (int) (size.getWidth() * 0.5);
        int bottomY = (int) (size.getHeight() * 0.8);
        int topY = (int) (size.getHeight() * 0.3);
        // lookup for element to refresh appium
        List<AndroidElement> lst = driver.findElements(By.id("com.shawmedia.smglobal:id/home_show_art_image_view"));
        System.out.println(lst.size());
        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(middleX, bottomY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(middleX, topY)).release()
                .perform();
        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(middleX, bottomY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(middleX, topY)).release()
                .perform();


        // Scroll down - 2 method -  using swipe() method in BSBAse
        //swipe();
        //Thread.sleep(3000);

        //Find Top News Collection - must end with /..
        WebElement topNewCollection = driver.findElement(By.xpath("//android.widget.TextView[@text='Top News']/.."));

        //find webelements within Top News collection

        List<WebElement> listOfThumbnails2 = topNewCollection.findElements(By.id("com.shawmedia.smglobal:id/home_page_video_layout"));

        //print names of the thumbnails
        for (WebElement thumbnail : listOfThumbnails2) {
            WebElement des = thumbnail.findElement(By.id("com.shawmedia.smglobal:id/home_page_description_episode_title"));
            System.out.println(des.getText());
        }
        //Swipe the first set of thumbnails - using swipeHorizontal method in BSBase
        swipeHorizontal(listOfThumbnails2.get(1), listOfThumbnails.get(0));
         // Find Elements and swipe
        List<WebElement> listOfThumbnails3 = topNewCollection.findElements(By.id("com.shawmedia.smglobal:id/home_page_video_layout"));
        swipeHorizontal(listOfThumbnails3.get(1), listOfThumbnails1.get(0));

        // Find list of Elements and swipe
        List<WebElement> listOfThumbnails4 = topNewCollection.findElements(By.id("com.shawmedia.smglobal:id/home_page_video_layout"));
        swipeHorizontal(listOfThumbnails4.get(1), listOfThumbnails1.get(0));

        //click on thumbnail to open player
        listOfThumbnails4.get(1).findElement(By.id("com.shawmedia.smglobal:id/home_page_video_art_view")).click();

        // tap on palyer to show Control rack
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Show player controls\"]/android.widget.FrameLayout[1]/android.webkit.WebView/android.webkit.WebView/android.view.View\n")).click();
        Thread.sleep(3000);
        //click Back button to close the player
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton\n")).click();
        //or
        //driver.findElement(By.id("com.shawmedia.smglobal:id/player_back_button")).click();
        Thread.sleep(3000);


/*String text = "Top News";
        WebElement el = driver.findElement(MobileBy
               .AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.shawmedia.smglobal:id/home_page_collection_list_item_rv\")).setAsVerticalList().scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));

        String text1 = "Watch out for loan scams during COVID-19 pandemic, experts warn";
        WebElement el1 = driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.shawmedia.smglobal:id/home_page_collection_list_item_rv\")).setAsHorizontalList().scrollIntoView("
                        + "new UiSelector().text(\""+text1+"\"));"));

        driver.findElement(MobileBy.id("com.shawmedia.smglobal:id/home_page_video_art_view")).click();
*/
        Thread.sleep(10000);


    }
}

