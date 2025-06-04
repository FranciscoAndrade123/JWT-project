package com.example.Security.System.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;


@Entity (name="user")

public class User {

     /*
     *  Atributos  o columnas de la tabla user
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    // Relación con la tabla rol
    @Column(name="rol_id", nullable = false)
    private Rol rol_id;

    // Atributos de la tabla user
    @Column(name="user_email", length = 255, nullable = false)
    private String user_email;

    @Column(name="password_hash", length = 255, nullable = false)
    private String password_hash;

    @Column(name="user_name", length = 50, nullable = false)
    private String user_name;

    @Column(name="is_active", nullable = false ,  columnDefinition = "boolean default true")
    private Boolean is_active;

    @Column(name="created_at", nullable = false)
    private LocalDateTime created_at;

    // Constructor por defecto de JPA
    public User() {
    }

    // Constructor con parámetros

    public User(int user_id, Rol rol_id, String user_email, String password_hash, String user_name, Boolean is_active, LocalDateTime created_at) {
        this.user_id = user_id;
        this.rol_id = rol_id;
        this.user_email = user_email;
        this.password_hash = password_hash;
        this.user_name = user_name;
        this.is_active = is_active;
        this.created_at = created_at;
    }

    // Getters y Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Rol getRol_id() {
        return rol_id;
    }


    public void setRol_id(Rol rol_id) {
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
