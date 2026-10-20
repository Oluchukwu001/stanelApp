package stanel.app.store.user.dtos;

import lombok.Data;

@Data
public class Response {
    private Object object;
    private String message;

    public Response(Object object, String message) {
        this.object = object;
        this.message = message;
    }

}
