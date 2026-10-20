package stanel.app.store.exception;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status; // e.g., 404
    private final String error; // e.g., "Not Found"
    private final String message; // Your custom message
    private final Instant timestamp;

    public ErrorResponse(int status, String error, String message, Instant timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

}
