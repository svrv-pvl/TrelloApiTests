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
}