package api_tests.list;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldReturnCorrectHeadersOnGetNonExistentListTest extends ListBaseTest{
    private static String boardId;

    @BeforeAll
    public static void arrange(){
        boardId = createDefaultBoardAndReturnId();
    }

    @ParameterizedTest
    @MethodSource("errorHeadersDataProvider")
    public void act(String headerName, String headerValue){
        listClient = new ListClient();
        String listIdWhichDoesNotExist = "6245ceb79f1e246fb742c111";
        Response response = listClient.sendGetListRequest(listIdWhichDoesNotExist);
        assertEquals(headerValue, response.getHeader(headerName));
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
