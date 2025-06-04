package com.example.Security.System.DTO;

public class RolDTO {
    
    String rol_name;

    // Constructor por defecto
    public RolDTO() {
    }

    // Constructor con parámetros
    public RolDTO(String rol_name) {
        this.rol_name = rol_name;
    }

    // Getters y Setters

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
    }
    
    
}
