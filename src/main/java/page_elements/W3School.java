package page_elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class W3School {

    @FindBy(xpath = "//div[@class='w3-example']//table")
    public WebElement w3s_table;

}
