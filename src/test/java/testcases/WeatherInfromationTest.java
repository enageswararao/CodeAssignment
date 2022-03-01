package testcases;

import apis.RestAssuredApis;
import beans.Temperature;
import dataprovider.TemperatureCityDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccuweatherSearchPage;
import utlities.CommonFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WeatherInfromationTest extends BaseTest{
    Temperature temperature_ui;
    Temperature temperature_api;
    WebDriver driver;
    AccuweatherSearchPage accuweatherSearchPage;
    Properties prop;

    @Parameters({ "broswer" })
    @BeforeTest
    public void beforeTest(String browser) throws IOException {
        driver= launchingBrowser(browser);
        prop =CommonFunctions.readPropertiesFile(System.getProperty("user.dir")+"\\src\\main\\resources\\qa\\environment.properties");

    }

    @Test(dataProvider = "temp-data-provider", dataProviderClass = TemperatureCityDataProvider.class)
    public void weatherInfo(HashMap<String, String> searchValues) throws InterruptedException {
        //UI
        driver.get(prop.getProperty("uiappurl"));
        accuweatherSearchPage = new AccuweatherSearchPage();
        accuweatherSearchPage.searchCityName(driver,searchValues.get("CityName"));
        accuweatherSearchPage.selectCityName(driver,searchValues.get("SelectCityName"));
        String temp=CommonFunctions.splitString(accuweatherSearchPage.getTemperature(driver));
        temperature_ui = new Temperature();
        temperature_ui.setTemperature(Double.parseDouble(temp));
        temperature_ui.setUnits("metric");

        //API
        Response response =RestAssuredApis.get(prop.getProperty("apiappurl"),searchValues.get("CityName"));
        temperature_api = new Temperature();
        Map<String, Double> main = response.jsonPath().getMap("main");
        temperature_api.setTemperature(Double.parseDouble(String.valueOf(main.get("temp"))));
        temperature_api.setUnits("standard");
        System.out.println(temperature_ui.compareTo(temperature_api));
        int result= temperature_ui.compareTo(temperature_api);
        Assert.assertEquals(0,result);
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }
}
