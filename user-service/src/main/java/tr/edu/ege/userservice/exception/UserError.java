package tr.edu.ege.userservice.exception;

import org.springframework.http.HttpStatus;

public enum UserError implements ApiError {
    USERNAME_ALREADY_EXISTS("Username already exists!", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("Email already exists!", HttpStatus.BAD_REQUEST);

    private String message;

    private HttpStatus httpStatus;

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
