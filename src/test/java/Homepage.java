import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Homepage {

    @FindBy(id = "postcode")
    WebElement quickSearchPostcode;

    @FindBy(id = "radius")
    WebElement quickSearchDistanceDropDown;

    @FindBy(id = "oneSearchAdUsed")
    WebElement quickSearchShowUsed;

    @FindBy(id = "oneSearchAdNearlyNew")
    WebElement quickSearchShowNearlyNew;

    @FindBy(id = "oneSearchAdBrandNew")
    WebElement quickSearchShowNew;

    @FindBy(id = "searchVehiclesMake")
    WebElement quickSearchMake;

    @FindBy(id = "searchVehiclesModel")
    WebElement quickSearchModel;

    @FindBy(id = "searchVehiclesPriceFrom")
    WebElement quickSearchMinPrice;

    @FindBy(id = "searchVehiclesPriceTo")
    WebElement quickSearchMaxPrice;

    @FindBy(id = "searchVehiclesCount")
    WebElement quickSearchSearchButton;

    @FindBy(css = "body > main > div > section.is-non-critical > section.products__nav.cars > a:nth-child(4)")
    WebElement linkToRegCheck;

    public void enterPostcode(String postcode){
        quickSearchPostcode.sendKeys(postcode);
    }

    public void selectDistance(String distance){
        distance = distance.substring(0, distance.length()-2);
        Select distanceSelector = new Select(quickSearchDistanceDropDown);
        distanceSelector.selectByValue(distance);
    }

    public void toggleUsed(Boolean searchUsed){
        if(!searchUsed){
            quickSearchShowUsed.click();
        }
    }

    public void toggleNearlyNew(Boolean searchUsed){
        if(!searchUsed){
            quickSearchShowNearlyNew.click();
        }
    }

    public void toggleNew(Boolean searchUsed){
        if(!searchUsed){
            quickSearchShowNew.click();
        }
    }

    public void selectMake(String make){
        Select makeSelector = new Select(quickSearchMake);
        List<WebElement> makeOptions = makeSelector.getOptions();
        for(WebElement element : makeOptions){
            if(element.getText().contains(make)){
                element.click();
            }
        }
    }

    public void selectModel(String model) {
        Select modelSelector = new Select(quickSearchModel);
        List<WebElement> modelOptions = modelSelector.getOptions();
        for(WebElement element : modelOptions) {
            if (element.getText().contains(model)) {
                element.click();
            }
        }
    }

    public void selectMinPrice(String minPrice){
        minPrice = minPrice.substring(0,5);
        Select minPriceSelector = new Select(quickSearchMinPrice);
        minPriceSelector.selectByValue(minPrice);
    }

    public void selectMaxPrice(String maxPrice){
        maxPrice = maxPrice.substring(0,5);
        Select maxPriceSelector = new Select(quickSearchMaxPrice);
        maxPriceSelector.selectByValue(maxPrice);
    }

    public void quickSearch(){
        quickSearchSearchButton.click();
    }

    public void navigateToRegCheck(){
        linkToRegCheck.click();
    }
}
