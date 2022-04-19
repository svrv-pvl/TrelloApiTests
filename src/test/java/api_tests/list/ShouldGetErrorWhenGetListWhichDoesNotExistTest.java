package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldGetErrorWhenGetListWhichDoesNotExistTest extends ListBaseTest {
    private static String boardId;

    @BeforeAll
    public static void arrange(){
        boardId = createDefaultBoardAndReturnId();
    }

    @Test
    public void act(){
        listClient = new ListClient();
        String notExistingListId = "6245ceb79f1e246fb742c211";
        Response response = listClient.sendGetListRequest(notExistingListId);
        assertEquals(404, response.statusCode());
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
