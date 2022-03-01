package apis;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredApis {

    public static Response get(String url, String cityName){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setBaseUri(url)
                .addQueryParam("q", cityName)
                .addQueryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea")
                .build();
        Response response = RestAssured.given(requestSpec).get();
        System.out.println(response.asString());
        return response;
    }

    public static Double parseTemperatureValue(Response response){
        Map<String, Double> main = response.jsonPath().getMap("main");

        return main.get("temp");
    }

}
