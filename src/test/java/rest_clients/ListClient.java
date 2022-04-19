package rest_clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ListClient extends RestClient{

    public Response sendPostListRequest(String boardId, String listName){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists?idBoard=" + boardId + "&name=" + listName + "&" + returnKeyAndToken();
        return  request.post(url);
    }

    public Response sendGetListRequest(String listId){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists/" + listId + "?" + returnKeyAndToken();
        return request.get(url);
    }

    public Response sendUpdateListRequest(String listId, String parameter, String newValue){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists/" + listId + "?" + parameter + "=" + newValue + "&" + returnKeyAndToken();
        return request.put(url);
    }

    public Response sendMoveListRequest(String listId, String newBoardId){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "lists/" + listId + "/idBoard?value=" + newBoardId + "&" + returnKeyAndToken();
        return request.put(url);
    }
}
