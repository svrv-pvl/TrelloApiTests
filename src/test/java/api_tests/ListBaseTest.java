package api_tests;

import io.restassured.response.Response;
import model.board.CreateBoardResponseBody;
import model.list.CreateListResponseBody;
import model.list.GetListResponseBody;
import org.apache.http.HttpStatus;
import rest_clients.BoardClient;
import rest_clients.ListClient;

public class ListBaseTest {
    protected static String boardId;
    protected static ListClient listClient;
    protected static BoardClient boardClient;

    protected static String createDefaultBoardAndReturnId(){
        String defaultBoardName = "defaultBoard";
        boardClient = new BoardClient();
        Response response = boardClient.sendPostBoardRequest(defaultBoardName);
        CreateBoardResponseBody createBoardResponseBody = response.as(CreateBoardResponseBody.class);
        return createBoardResponseBody.getId();
    }

    protected static String createDefaultListAndReturnId(String boardId){
        listClient = new ListClient();
        String defaultBoardName = "defaultBoardName";
        Response response = listClient.sendPostListRequest(boardId, defaultBoardName);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        CreateListResponseBody createListResponseBody = response.as(CreateListResponseBody.class);
        return createListResponseBody.getId();
    }

    protected static GetListResponseBody getListAndReturnBody(String listId){
        Response getListResponse = listClient.sendGetListRequest(listId);
        return getListResponse.as(GetListResponseBody.class);
    }

}
