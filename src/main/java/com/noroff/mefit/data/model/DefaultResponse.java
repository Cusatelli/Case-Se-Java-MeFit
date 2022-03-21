package com.noroff.mefit.data.model;

public class DefaultResponse<T> {
    public static class ErrorResponse {
        private final Integer status;
        private final String message;

        public ErrorResponse(Integer status, String message) {
            this.status = status;
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    private final Boolean success;
    private final T payload;
    private final ErrorResponse error;

    public DefaultResponse(T payload) {
        this.success = true;
        this.payload = payload;
        this.error = null;
    }

    public DefaultResponse(Integer status, String message) {
        this.success = false;
        this.payload = null;
        this.error = new ErrorResponse(status, message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getPayload() {
        return payload;
    }

    public ErrorResponse getError() {
        return error;
    }

    public static String NOT_FOUND(String modelName, Long id) {
        return "Could not find " + modelName + " with ID: " + id;
    }

    public static String NO_CONTENT(String modelName) {
        return modelName + " does not contain any content.";
    }

    public static String FOUND(String modelName, Long id) {
        return "Found " + modelName + " with ID: " + id;
    }
}
