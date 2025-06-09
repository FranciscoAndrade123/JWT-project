package com.example.Security.System.DTO;

public class Page_roleDTO {

    private int rol_id ;
    private int page_id;

    //Constructor por defecto 
    public Page_roleDTO() {}

    //Constructor con parametros
    public Page_roleDTO(int rol_id, int page_id) {
        this.rol_id = rol_id;
        this.page_id = page_id;
    }

    //Getters y setters
    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }
    
}
