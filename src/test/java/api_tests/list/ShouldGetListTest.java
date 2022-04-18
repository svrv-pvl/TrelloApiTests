package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import model.list.GetListResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldGetListTest extends ListBaseTest {
    private static String listId;
    private static GetListResponseBody getListResponseBody;
    private static final Boolean EXPECTED_CLOSED_VALUE = false;

    @BeforeAll
    public static void arrange(){
        listId = createDefaultListAndReturnId(BOARD_ID);
        Response getListResponse = listClient.sendListGetRequest(listId);
        getListResponseBody = getListResponse.as(GetListResponseBody.class);
    }

    @Test
    public void act(){
        assertEquals(listId, getListResponseBody.getId());
        assertEquals(BOARD_ID, getListResponseBody.getIdBoard());
        assertEquals(EXPECTED_CLOSED_VALUE, getListResponseBody.getClosed());
    }
}
