package helper;


import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

public class Helper {
    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean existsElement(WebDriver driver, String id) {
        try {
            driver.findElement(By.xpath(id));
            System.out.println("existsElement = true");
        } catch (NoSuchElementException e) {
            System.out.println("existsElement = false");
            return false;
        }
        return true;
    }

    public WebElement fluentWait(WebDriver driver, final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return foo;
    }

    public static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        String str =new String(stringBuilder.toString().getBytes(),"UTF-8");

        System.out.println(str);

        return str;
    }



    // try(FileWriter writer = new FileWriter("mainPage.json", false))
    // {
    //     writer.write(json);
    //     writer.flush();
    // }
    // catch(IOException ex){
    //     System.out.println(ex.getMessage());
    // }

    public static void writer(String fileName, String text) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public static byte[] makeScreenshot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
