package rest_clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoardClient extends RestClient{
    public Response sendPostBoardRequest(String boardName){
        RequestSpecification request = prepareRequest();
        String url = BASE_URL + "boards?name=" + boardName + "&" + returnKeyAndToken();
        return request.post(url);
    }
}
