package api_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.board.CreateBoardResponseBody;
import model.list.CreateListResponseBody;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.provider.Arguments;
import rest_clients.BoardClient;
import rest_clients.ListClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class BaseTest {
    protected static ListClient listClient;
    protected static BoardClient boardClient;

    protected static Stream<Arguments> successfulHeadersDataProvider(){
        List<Arguments> list = new ArrayList<>();
        list.addAll(readHeadersFromFile("src/test/resources/general_headers.csv"));
        list.addAll(readHeadersFromFile("src/test/resources/additional_headers_200.csv"));
        return list.stream();
    }

    protected static Stream<Arguments> errorHeadersDataProvider(){
        List<Arguments> list = new ArrayList<>();
        list.addAll(readHeadersFromFile("src/test/resources/general_headers.csv"));
        list.addAll(readHeadersFromFile("src/test/resources/additional_headers_400.csv"));
        return list.stream();
    }

    private static List<Arguments> readHeadersFromFile(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        List<Arguments> headersList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                Scanner rowScanner = new Scanner(scanner.nextLine());
                rowScanner.useDelimiter(",");
                String headerName = rowScanner.next();
                String headerValue = rowScanner.nextLine().substring(1);

                headersList.add(Arguments.of(headerName, headerValue));
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Cannot read headers file");
        }
        return headersList;
    }

    protected static String createDefaultBoardAndReturnId(){
        String defaultBoardName = "defaultBoard";
        boardClient = new BoardClient();
        Response response = boardClient.sendPostBoardRequest(defaultBoardName);
        CreateBoardResponseBody createBoardResponseBody = response.as(CreateBoardResponseBody.class);
        return createBoardResponseBody.getId();
    }

    protected static void deleteBoard(String boardId){
        boardClient = new BoardClient();
        Response response = boardClient.sendDeleteBoardRequest(boardId);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    protected static String createDefaultListAndReturnId(String boardId){
        listClient = new ListClient();
        String defaultBoardName = "defaultBoardName";
        Response response = listClient.sendPostListRequest(boardId, defaultBoardName);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        CreateListResponseBody createListResponseBody = response.as(CreateListResponseBody.class);
        return createListResponseBody.getId();
    }
}

