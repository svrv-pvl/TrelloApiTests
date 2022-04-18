package rest_clients;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ListClient extends RestClient{

    public Response sendListPostRequest(String boardId, String listName){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists?idBoard=" + boardId + "&name=" + listName + "&" + returnKeyAndToken();
        return  request.post(url);
    }

    public Response sendListGetRequest(String listId){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists/" + listId + "?" + returnKeyAndToken();
        return request.get(url);
    }
}
