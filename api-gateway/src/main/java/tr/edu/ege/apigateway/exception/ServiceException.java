package tr.edu.ege.apigateway.exception;

public class ServiceException extends RuntimeException {
    private final ApiError apiError;

    public ServiceException(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
