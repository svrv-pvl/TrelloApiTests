package api_tests.list;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldReturnCorrectHeadersOnUpdateListTest extends ListBaseTest{
    private static String boardId;
    private static String listId;

    @BeforeAll
    public static void arrange(){
        boardId = createDefaultBoardAndReturnId();
        listId = createDefaultListAndReturnId(boardId);
    }

    @ParameterizedTest
    @MethodSource("successfulHeadersDataProvider")
    public void act(String headerName, String headerValue){
        listClient = new ListClient();
        Response response = listClient.sendUpdateListRequest(listId, "name", "newName");
        assertEquals(headerValue, response.getHeader(headerName));
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
