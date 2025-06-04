package com.example.Security.System.DTO;

public class ResponseDTO {
    private String message;
    private String status;

    // Constructor por defecto de JPA
    public ResponseDTO (String message, String status) {
        this.message = message;
        this.status = status;
    }

    // Constructor por defecto
    public ResponseDTO(){}

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
