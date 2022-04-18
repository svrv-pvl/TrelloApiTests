package model.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateListResponseBody extends GetListResponseBody{
    private Object limits;
    //TODO Need to create normal class for limits
}