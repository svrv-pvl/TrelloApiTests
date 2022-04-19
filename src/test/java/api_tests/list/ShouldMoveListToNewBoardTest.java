package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import model.list.GetListResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldMoveListToNewBoardTest extends ListBaseTest {
    private static String firstBoardId;
    private static String secondBoardId;
    private static String listId;

    @BeforeAll
    public static void arrange(){
        firstBoardId = createDefaultBoardAndReturnId();
        secondBoardId = createDefaultBoardAndReturnId();
        listId = createDefaultListAndReturnId(firstBoardId);
    }

    @Test
    public void act(){
        listClient = new ListClient();
        Response updateResponse = listClient.sendMoveListRequest(listId, secondBoardId);
        GetListResponseBody updateListResponseBody = updateResponse.as(GetListResponseBody.class);
        GetListResponseBody getListResponseBody = getListAndReturnBody(listId);

        assertEquals(200, updateResponse.statusCode());
        assertEquals(secondBoardId, updateListResponseBody.getIdBoard());
        assertEquals(secondBoardId, getListResponseBody.getIdBoard());
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(firstBoardId);
        deleteBoard(secondBoardId);
    }
}
