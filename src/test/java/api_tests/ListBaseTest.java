package api_tests;

import io.restassured.response.Response;
import model.list.CreateListResponseBody;
import org.apache.http.HttpStatus;
import rest_clients.ListClient;

public class ListBaseTest {
    protected static final String BOARD_ID = "62445ffced32e95e1629e783";
    protected static ListClient listClient;

    protected static String createDefaultListAndReturnId(String boardId){
        listClient = new ListClient();
        String defaultBoardName = "defaultBoardName";
        Response response = listClient.sendListPostRequest(BOARD_ID, defaultBoardName);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        CreateListResponseBody createListResponseBody = response.as(CreateListResponseBody.class);
        return createListResponseBody.getId();
    }
}
