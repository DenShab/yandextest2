package helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils {

    public static void clickElementByJS(WebDriver driver, WebElement element) {
        String script = "arguments[0].click();";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public static void inputElementByJS(WebDriver driver, WebElement element, String value) {
        //String script = "arguments[0].setAttribute('value','"+product+"')";
        //clickElementByJS(driver, element);
        String script2 = "arguments[0].value = 'Вставляем текст';\n" +
                "var evt = document.createEvent('HTMLEvents');\n" +
                "evt.initEvent(\"change\", true, true);\n" +
                "arguments[0].dispatchEvent(evt);";
        ((JavascriptExecutor) driver).executeScript(script2, element);
        //String script1 = "arguments[0].focus();";
        //((JavascriptExecutor) driver).executeScript(script1, element);
        //String script = "arguments[0].focus().value='" + value + "';";
        //((JavascriptExecutor) driver).executeScript(script, element);

    }

    public static void drawBorderByJS(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.border='3px solid red';";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public static void scrollIntoViewByJS(WebDriver driver, WebElement element) {
        String script = "arguments[0].scrollIntoView(true);";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public static void refreshBrowserByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("history.go(0)");
    }


    public static void readonlyByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].readonly", element);
    }

    //document.getElementById("header-search").setAttribute("readonly", "readonly");
    //document.getElementsByClassName("_1XiEJDPVpk").disabled = true;
    //document.getElementById("header-search").removeAttribute('readonly');
    //.style.backgroundColor= '#ddd'
    //.setAttribute("disabled", "disabled");
    //.setAttribute("disabled", "disabled");
}
