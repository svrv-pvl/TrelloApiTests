package api_tests.list;

import api_tests.BaseTest;
import io.restassured.response.Response;
import model.board.CreateBoardResponseBody;
import model.list.CreateListResponseBody;
import model.list.GetListResponseBody;
import org.apache.http.HttpStatus;
import rest_clients.BoardClient;
import rest_clients.ListClient;

public class ListBaseTest extends BaseTest {
    protected static String boardId;


    protected static GetListResponseBody getListAndReturnBody(String listId){
        listClient = new ListClient();
        Response getListResponse = listClient.sendGetListRequest(listId);
        return getListResponse.as(GetListResponseBody.class);
    }

}
