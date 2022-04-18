package model.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetListResponseBody {
    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private int pos;

    public String getValueByFieldName(String fieldName){
        return switch (fieldName) {
            case ("name") -> name;
            case ("closed") -> closed.toString();
            case ("idBoard") -> idBoard;
            case ("pos") -> String.valueOf(pos);
            default -> "no value";
        };
    }
}