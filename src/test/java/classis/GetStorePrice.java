package classis;

import helper.Helper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class GetStorePrice extends MyStep{
  /*  @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step  - ввести в строку поиска какой-либо товар (например, модель вашего телефона)")
    @Override
    public void execution() {

        String newWindow = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until((ExpectedCondition<String>) driver -> {
                            assert driver != null;
                            Set<String> newWindowsSet;
                            newWindowsSet = driver.getWindowHandles();
                            newWindowsSet.removeAll(oldWindowsSet);
                            return newWindowsSet.size() > 0 ?
                                    newWindowsSet.iterator().next() : null;
                        }
                );

        driver.switchTo().window(newWindow);
        if (driver.getCurrentUrl().toLowerCase().contains(url.toLowerCase())) {
            WebElement dynamicElement4 = (new WebDriverWait(driver, Duration.ofSeconds(50)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(price)));
            if (dynamicElement4.getText().toLowerCase().contains("нет в продаже")) {
                System.out.println("нет в продаже");
            } else {
                if (Helper.existsElement(driver, (store + "//img"))) {
                    String storeStr = driver.findElement(By.xpath(store)).findElement(By.xpath(".//img"))
                            .getAttribute("title");
                    String priceStr = driver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                    System.out.println("Магазин " + storeStr + " цена " + priceStr);
                } else {
                    if (Helper.existsElement(driver, (store + "//[@data-zone-name=\"shopName\"]"))) {
                        String storeStr = driver.findElement(By.xpath(store)).findElement(By.xpath(".//[@data-zone-name=\"shopName\"]")).getText();
                        String priceStr = driver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                        System.out.println("Магазин " + storeStr + " цена " + priceStr);
                    } else {
                        String priceStr = driver.findElement(By.xpath(store)).findElement(By.xpath(".//div[@class=\"_3NaXxl-HYN _3PwoBT4kxK ffKant8Dgf\"]")).getText();
                        System.out.println("Магазин Яндекс Маркет цена " + priceStr);
                    }
                }
            }
        } else {
            driver.switchTo().window(originalWindow);

            String storeStr = driver.findElement(By.xpath(onestore)).getText();
            String priceStr = driver.findElement(By.xpath(oneprise)).getText();
            System.out.println("Магазин " + storeStr + " цена " + priceStr);

        }
        Helper.makeScreenshot(driver);
    }

   */
}
