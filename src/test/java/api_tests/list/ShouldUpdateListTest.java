package api_tests.list;

import io.restassured.response.Response;
import model.list.GetListResponseBody;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShouldUpdateListTest extends ListBaseTest {
    private static String boardId;
    private static String listId;

    @BeforeAll
    public static void arrange(){
        boardId = createDefaultBoardAndReturnId();
        listId = createDefaultListAndReturnId(boardId);
    }

    //TODO add more parameters
    @ParameterizedTest
    @CsvSource({
            "name,TEST",
            "name,' '",
            "name,'    '",
            "name,'                     '",
            "name,'  '",
            "name,!!!",
            "name,'@#$'",
            "name,'%^'",
            "name,'*()'",
            "name,'[]'",
            "name,'+++'",
            "name,adf af  adsf  adf ad f ad f dsafadsfdasf  adsf das f asdf  dsaf a sdf da f dsa f dasffadsfdasfadf",
            "pos,124",
            "closed, true",
            "closed, false"
            })
    public void act(String parameter, String value){
        //ACT
        Response updateListResponse = listClient.sendUpdateListRequest(listId, parameter, value);
        GetListResponseBody updateListResponseBody = updateListResponse.as(GetListResponseBody.class);
        GetListResponseBody getListResponseBody = getListAndReturnBody(listId);
        //ASSERT
        assertEquals(200, updateListResponse.statusCode());
        assertEquals(value, updateListResponseBody.getValueByFieldName(parameter));
    }

    @AfterAll
    public static void tearDown(){
        deleteBoard(boardId);
    }
}
