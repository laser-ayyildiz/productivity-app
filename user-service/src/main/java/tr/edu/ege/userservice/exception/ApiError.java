package tr.edu.ege.userservice.exception;

import org.springframework.http.HttpStatus;

public interface ApiError {

    HttpStatus getHttpStatus();

    String getMessage();
}
