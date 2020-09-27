package classis;

import org.openqa.selenium.WebDriver;

/**
 * класс Item, является моделью товара и содержащий поля: название товара, цена (названия магазинов)
 */
public class Item {
    private String name;
    private String price;

    /**
     *
     * @param name
     * @param price
     */
    public Item(String name,String price) {
        this.name = name;
        this.name = price;
    }

    public String getName(){
        String name = this.name.toLowerCase();
        return name;
    }
}
