package com.example.Security.System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity (name="page_role")

public class Page_role {

    /*
     * Atributos  o columnas de la tabla page_role
     * 
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pagerole_id")
    private int pagerole_id;

    
    // Relación con la tabla rol
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol_id;

    
    // Relación con la tabla page
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page_id;

    // Constructor por defecto
    public Page_role() {}

    //Constructor con parametros
    public Page_role(int pagerole_id, Rol rol_id, Page page_id) {
        this.pagerole_id = pagerole_id;
        this.rol_id = rol_id;
        this.page_id = page_id;
    }

    // Getter y setter 
    public int getPagerole_id() {
        return pagerole_id;
    }

    public void setPagerole_id(int pagerole_id) {
        this.pagerole_id = pagerole_id;
    }

    public Rol getRol_id() {
        return rol_id;
    }

    public void setRol_id(Rol rol_id) {
        this.rol_id = rol_id;
    }

    public Page getPage_id() {
        return page_id;
    }

    public void setPage_id(Page page_id) {
        this.page_id = page_id;
    }

}
