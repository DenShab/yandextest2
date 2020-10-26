package pageobject;

import classis.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.deserialize.CatalogPageDeserializer;
import helper.Helper;
import helper.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.*;

import static helper.Helper.readUsingBufferedReader;
import static helper.JSUtils.*;

public class CatalogPage {
    private WebDriver driver;

    private String URL_MATCH;// = "https://market.yandex.ru/";
    //"catalog";

    private List<WebElement> elements;
    private ArrayList<Item> items;

    private String productXpath;// = "//article[@data-autotest-id=\"product-snippet\" or @data-autotest-id=\"offer-snippet\"] ";


    /**
     * Кнопка сортировки по цене
     */
    @FindBy(xpath = "//button[text()=\"по цене\"]")
    private WebElement sortBt;
    private String xSortBt;// = "//button[text()=\"по цене\"]";
    /**
     * Первый элемент списка
     */
    @FindBy(xpath = "//*[@data-zone-name=\"snippetList\"]/article[position()=1]")
    private WebElement firstProdXpath;
    private String xFirstProd;// = "//*[@data-zone-name=\"snippetList\"]/article[position()=1]//h3[@data-zone-name=\"title\"]/a";

    public CatalogPage(WebDriver driver) {
        setWebDriver(driver);
    }

    public CatalogPage() {

    }

    public CatalogPage(WebDriver driver, String fileName) {
        setWebDriver(driver);
        String jsonCatalogPage = "";
        try {
            jsonCatalogPage = readUsingBufferedReader(fileName);
        } catch (IOException e) {
            System.out.println("не удалось считать json CatalogPage");
            e.printStackTrace();
        }
        if (jsonCatalogPage != "") {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(CatalogPage.class, new CatalogPageDeserializer())
                    .create();
            CatalogPage сatalogPageBack = gson.fromJson(jsonCatalogPage, CatalogPage.class);
            this.setXSortBt(сatalogPageBack.getXSortBt());
            this.setXFirstProd(сatalogPageBack.getXFirstProd());
            this.setProductXpath(сatalogPageBack.getProductXpath());
            this.setURL_MATCH(сatalogPageBack.getURL_MATCH());
            System.out.println("Десерилизаци CatalogPage прошла успешно");

        } else {
            System.out.println("json CatalogPage пуст");
        }
    }

    /**
     * Проверка наличия товара на стрнице
     */
    public boolean chekProd(String product) {
        productSearch();
        boolean ret = false;
        for (Item item : this.items) {
            if (item.getName().contains(product.toLowerCase())) {
                ret = true;
                break;
            }
        }
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(product.toLowerCase())) {
                scrollIntoViewByJS(driver, element);
                drawBorderByJS(driver, element);
                Helper.wait(1000);
            }
        }

        return ret;
    }

    /**
     * Поиск товаров на странице
     * (метод не доделан. Нужно перебрать все страницы каталога, если хотим действительно полный список)
     */
    public void productSearch() {

        this.elements = driver.findElements(By.xpath(productXpath));

        this.items = new ArrayList<>();
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//h3")).getText();
            String price = element.findElement(By.xpath(".//a")).getText();

            this.items.add(new Item(name, price));
        }
    }

    /**
     * Нажатие на кнопку сортировки по цене
     */
    public void submitSortBt() {
        WebElement sortBt1 = driver.
                findElement(By.xpath(xSortBt));
        sortBt1.click();
        Helper.wait(5000);
    }

    /**
     * Нажатие на первый элеент списка
     */
    public void clickFirstProd() {
        //WebElement firstprod = firstProdXpath.
        //        findElement(By.xpath(".//h3[@data-zone-name=\"title\"]/a"));
        WebElement firstprod1 = driver.
                findElement(By.xpath(xFirstProd));
        JSUtils.drawBorderByJS(driver, firstprod1);
        firstprod1.click();
    }


    public String getURL_MATCH() {
        return URL_MATCH;
    }

    public void setURL_MATCH(String URL_MATCH) {
        this.URL_MATCH = URL_MATCH;
    }

    public String getXFirstProd() {
        return xFirstProd;
    }

    public void setXFirstProd(String xFirstProd) {
        this.xFirstProd = xFirstProd;
    }

    public String getXSortBt() {
        return xSortBt;
    }

    public void setXSortBt(String xSortBt) {
        this.xSortBt = xSortBt;
    }

    public String getProductXpath() {
        return productXpath;
    }

    public void setProductXpath(String productXpath) {
        this.productXpath = productXpath;
    }

    public void setWebDriver(WebDriver driver) {
        //if (!driver.getCurrentUrl().contains(URL_MATCH)) {
        //    throw new IllegalStateException(
        //            "This is not the page you are expected"
        //    );
        //}
        this.driver = driver;
    }
}
