package api_tests.list;

import api_tests.ListBaseTest;
import io.restassured.response.Response;
import model.list.GetListResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldUpdateListTest extends ListBaseTest {
    private static String listId;

    @BeforeAll
    public static void arrange(){
        listId = createDefaultListAndReturnId(BOARD_ID);
    }

    //TODO add more parameters
    @ParameterizedTest
    @CsvSource({
            "name,TEST",
            "pos,124"
            })
    public void act(String parameter, String value){
        Response updateListResponse = listClient.sendUpdateListRequest(listId, parameter, value);
        GetListResponseBody updateListResponseBody = updateListResponse.as(GetListResponseBody.class);
        Response getListResponse = listClient.sendGetListRequest(listId);
        GetListResponseBody getListResponseBody = getListResponse.as(GetListResponseBody.class);
        assertEquals(200, updateListResponse.statusCode());
        assertEquals(value, updateListResponseBody.getValueByFieldName(parameter));
    }
}
