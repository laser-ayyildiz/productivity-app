package tr.edu.ege.apigateway.exception;

import org.springframework.http.HttpStatus;

public enum GlobalError implements ApiError {
    FORBIDDEN("You don't have permission. If you believe this is a mistake, please contact support!",
            HttpStatus.FORBIDDEN);

    private final String message;

    private final HttpStatus httpStatus;

    GlobalError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
