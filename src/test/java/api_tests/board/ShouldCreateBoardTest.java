package api_tests.board;

import io.restassured.response.Response;
import model.board.CreateBoardResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import rest_clients.BoardClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShouldCreateBoardTest extends BoardBaseTest {

    @Test
    public void act(){
        boardClient = new BoardClient();
        String boardName = "TestBoard";
        Response response = boardClient.sendPostBoardRequest(boardName);
        CreateBoardResponseBody createBoardResponseBody = response.as(CreateBoardResponseBody.class);
        boardId = createBoardResponseBody.getId();
        assertEquals(200, response.statusCode());
        assertEquals(boardName, createBoardResponseBody.getName());
        assertEquals("", createBoardResponseBody.getDesc());
        assertNull(createBoardResponseBody.getDescData());
        assertEquals(false, createBoardResponseBody.getPinned());
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
