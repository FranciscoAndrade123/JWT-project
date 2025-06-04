package com.example.Security.System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity (name="rol")

public class Rol {
    /*
     *  Atributos  o columnas de la tabla rol
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rol_id") 
    private int rol_id;

    @Column(name="rol_name",length = 50,nullable = false)
    private String rol_name;

    // Constructor por defecto de JPA 
    public Rol() {
    }

    // Constructor con parámetros

    public Rol(int rol_id, String rol_name) {
        this.rol_id = rol_id;
        this.rol_name = rol_name;
    }

    // Getters y Setters
    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
    }

}
