package rest_clients;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ListClient extends RestClient{

    public Response sendListPostRequest(String boardId, String listName){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RequestSpecification request = RestAssured.given().header("Content-Type", "text/plain");
        String url = BASE_URL + "lists?idBoard=" + boardId + "&name=" + listName + "&key=" + KEY + "&token=" + TOKEN;
        return  request.post(url);
    }
}
