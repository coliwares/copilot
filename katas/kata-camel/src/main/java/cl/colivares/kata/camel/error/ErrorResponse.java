package cl.colivares.kata.camel.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    private int code;
    private String message;
    private List<String> errors;
    private HashMap<String, Object> errorsDetails;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(int code, String message, LocalDateTime timestamp) {
        super();
        this.code = code;
        this.message = message;
        this.errors = null;
        this.errorsDetails = null;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HashMap<String, Object> getErrorsDetails() {
        return errorsDetails;
    }

    public void setErrorsDetails(HashMap<String, Object> errorsDetails) {
        this.errorsDetails = errorsDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

    
