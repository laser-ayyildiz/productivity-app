package tr.edu.ege.userservice.exception;

import org.springframework.http.HttpStatus;

public enum UserError implements ApiError {
    NOT_FOUND("User not found!", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS("Username already exists!", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("Email already exists!", HttpStatus.BAD_REQUEST);

    private final String message;

    private final HttpStatus httpStatus;

    UserError(String message, HttpStatus httpStatus) {
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
