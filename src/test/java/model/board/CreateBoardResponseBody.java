package model.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardResponseBody extends GetBoardResponseBody{
    private Object limits;
}
