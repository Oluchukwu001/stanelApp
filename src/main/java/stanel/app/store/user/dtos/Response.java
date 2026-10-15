package stanel.app.store.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class Response {
    private Object object;
    private String message;
}
