package pageobject;

import classis.Item;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogPage {
    private WebDriver driver;
    private static String URL_MATCH = "https://market.yandex.ru/catalog";

    private List<WebElement> elements;
    private ArrayList<Item> items;

    private String productXpath = "//*[@data-autotest-id=\"product-snippet\"]";
    private String offerXpath = "//*[@data-autotest-id=\"offer-snippet\"]";
    /**
     * Кнопка сортировки по цене
     */
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[3]/div[3]/div/div[1]/button[2]")
    ///html/body/div[2]/div[3]/div[3]/div[3]/div/div[1]/button[2]
    private WebElement sortBt;

    /**
     * Первый элемент списка
     */
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[1]/div[4]/div[1]/h3/a")
    private WebElement firstprod;

    public CatalogPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }
        //PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Проверка наличия товара на стрнице
     */
    public boolean chekProd(String product) {
        productSearch();
        boolean ret=false;
        for (Item item : this.items) {
            if (item.getName().contains(product.toLowerCase())){
                ret= true;
                break;
            }
        }
        return ret;
    }

    /**
     * Поиск товаров на странице
     * (метод не доделан. Нужно перебрать все страницы каталога, если хотим действительно полный список)
     */
    public void productSearch() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.elements = driver.findElements(By.xpath(productXpath));
        this.elements.addAll(driver.findElements(By.xpath(offerXpath)));

        this.items = new ArrayList<>();
        for (WebElement element : this.elements) {
            String name = element.findElement(By.xpath("./div[5]/div[1]")).getText();
            String price = element.findElement(By.xpath("./div[4]/div[1]/h3/a")).getText();

            this.items.add(new Item(name, price));
        }
    }

    /**
     * Нажатие на кнопку сортировки по цене
     */
    public void submitSortBt() {
        sortBt.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Нажатие на первый элеент списка
     */
    public void clickFirstProd() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstprod.click();
    }
}
