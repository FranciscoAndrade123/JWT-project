package com.example.Security.System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity (name="page")

public class Page {

    /*
     * Atributos  o columnas de la tabla Page
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="page_id") 
    private int page_id;

    @Column(name="page_name",length = 50,nullable = false)
    private String page_name;

    // Constructor por defecto
    public Page() {
    }

    //Constructor con parametros 
    public Page(int page_id, String page_name) {
        this.page_id = page_id;
        this.page_name = page_name;
    }

    // Getters y Setters
    public int getPage_id() {
        return page_id;
    }
    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }
}
