package api_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class BaseTest {
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
}

