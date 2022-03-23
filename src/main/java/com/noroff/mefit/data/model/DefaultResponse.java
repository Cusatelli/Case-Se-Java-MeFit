package com.noroff.mefit.data.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    public static String NO_CONTENT(String name) {
        return name + " does not contain any content.";
    }

    public static String FOUND(String name, Long id) {
        return "Found " + name + " with ID: " + id;
    }

    public static String BAD_REQUEST(String name) {
        return "Bad Request: " + name;
    }

    public static String NOT_IMPLEMENTED(String name) {
        return "Method [" + name + "] is not implemented yet.";
    }
}
