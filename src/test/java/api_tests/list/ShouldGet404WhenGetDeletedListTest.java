package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldGet404WhenGetDeletedListTest extends ListBaseTest {

    @Test
    public void act(){
        String boardId = createDefaultBoardAndReturnId();
        String listId = createDefaultListAndReturnId(boardId);
        deleteBoard(boardId);
        ListClient listClient = new ListClient();
        Response response = listClient.sendGetListRequest(listId);
        assertEquals(404, response.statusCode());
    }
}
