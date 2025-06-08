package com.example.Security.System.DTO;

public class RecoveryRequestDTO {
    private int user_id;

    //Constructor por defecto de JPA 
    public RecoveryRequestDTO () {}

    //Constructor con parametros
    public RecoveryRequestDTO(int user_id) {
        this.user_id = user_id;
    }

    //Getters and setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }   

    
}
