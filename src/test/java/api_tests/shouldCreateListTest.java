package api_tests;

import io.restassured.response.Response;
import model.list.CreateListResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rest_clients.ListClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class shouldCreateListTest {
    private static final String BOARD_ID = "62445ffced32e95e1629e783";
    private static ListClient listClient;
    private static Response response;
    private static String listName;
    private static CreateListResponseBody createListResponseBody;
    private static Boolean expectedClosedValue;

    @BeforeAll
    public static void act(){
        listName = "testList";
        expectedClosedValue = false;
        listClient = new ListClient();
        response = listClient.sendListPostRequest(BOARD_ID, listName);
        createListResponseBody = response.as(CreateListResponseBody.class);
    }

    @Test
    public void test(){
        assertEquals(200, response.statusCode());
        assertEquals(listName, createListResponseBody.getName());
        assertEquals(BOARD_ID, createListResponseBody.getIdBoard());
        assertEquals(expectedClosedValue, createListResponseBody.getClosed());
    }
}
