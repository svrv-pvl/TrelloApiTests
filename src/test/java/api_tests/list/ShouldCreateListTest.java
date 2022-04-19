package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import model.list.CreateListResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldCreateListTest extends ListBaseTest {
    private static String boardId;
    private static Response createListResponse;
    private static final String LIST_NAME = "testList";
    private static CreateListResponseBody createListResponseBody;
    private static final Boolean EXPECTED_CLOSED_VALUE = false;

    @BeforeAll
    public static void arrange(){
        boardId = createDefaultBoardAndReturnId();
        listClient = new ListClient();
        createListResponse = listClient.sendPostListRequest(boardId, LIST_NAME);
        createListResponseBody = createListResponse.as(CreateListResponseBody.class);
    }

    @Test
    public void act(){
        assertEquals(200, createListResponse.statusCode());
        assertEquals(LIST_NAME, createListResponseBody.getName());
        assertEquals(boardId, createListResponseBody.getIdBoard());
        assertEquals(EXPECTED_CLOSED_VALUE, createListResponseBody.getClosed());
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
