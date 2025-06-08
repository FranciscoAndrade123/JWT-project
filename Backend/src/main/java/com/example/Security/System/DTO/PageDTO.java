package com.example.Security.System.DTO;

public class PageDTO {

    String page_name;

    //Constructor por defecto
    public PageDTO() {}

    //Constructor con parametros
    public PageDTO(String page_name) {
        this.page_name = page_name;
    }

    // Getter y Setter
    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }
    
}
