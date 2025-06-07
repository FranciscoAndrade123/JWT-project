package com.example.Security.System.DTO;

public class UserDTO {

    private int rol_id;
    private String user_email;
    private String password_hash;
    private String user_name;

    // Constructor vacío
    public UserDTO() {}

    // Constructor con parámetros
    public UserDTO(int rol_id, String user_email, String password_hash, String user_name) {
        this.rol_id = rol_id;
        this.user_email = user_email;
        this.password_hash = password_hash;
        this.user_name = user_name;
    }

    // Getters y setters
    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
