package pageobject;

public class PageParametrs {
    MainPage mainPage;
    CatalogPage catalogPage;

    public CatalogPage getCatalogPage() {
        return catalogPage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setCatalogPage(CatalogPage catalogPage) {
        this.catalogPage = catalogPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
