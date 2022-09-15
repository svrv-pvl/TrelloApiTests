package rest_clients;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    protected final String NAME = "Production";
    protected final String BASE_URL = "https://api.trello.com/1/";
    protected final String KEY = "999d1bdf19e2a37ef3ad781a42b456fb";
    protected final String TOKEN = "d254d2de41b17424e1d964c40a86448481f5acad0a5b0ff651d590605f80f83a";
    //TODO Временное решение с хранением токена для отладки. Необходимо перенести в ресурс вне гита

    protected RequestSpecification prepareRequest(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        return RestAssured.given().header("Content-Type", "text/plain");
    }

    protected String returnKeyAndToken(){
        return "key=" + KEY + "&token=" + TOKEN;
    }
}
