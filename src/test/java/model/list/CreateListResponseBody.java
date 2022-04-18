package model.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateListResponseBody {
    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private int pos;
    private Object limits;
    //TODO Need to create normal class for limits
}