package com.example.Security.System.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO    

import com.example.Security.System.DTO.PageDTO;

import com.example.Security.System.Service.PageService; // Importar la clase PageService
import com.example.Security.System.Model.Page; // Importar la clase Page

@RestController
@RequestMapping("/api/v1/page")
public class PageController {


    @Autowired
    private PageService pageService;

    // Endpoint para insertar una página
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> insertPage(@RequestBody PageDTO pageDTO) {
        ResponseDTO response = pageService.insertPage(pageDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las páginas
    @GetMapping("/")
    public ResponseEntity<List<Page>> listallPages(){
        List<Page> pages = pageService.getAllPage();
        return new ResponseEntity<>(pages,HttpStatus.OK); 
    } 

    //Endpoint para obtener una página por id
    @GetMapping("/{id}")
    public ResponseEntity<Page> findPageById (@PathVariable int id) {
       return pageService.findPageByID(id)
       .map(page -> new ResponseEntity<>(page,HttpStatus.OK))
       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint para eliminar una page meidiante el ID
   @DeleteMapping("/{id}")
public ResponseEntity<ResponseDTO> deletePage (@PathVariable int id) {
    ResponseDTO response = pageService.deletePage(id);
    if(response.getStatus().equals(HttpStatus.OK.toString())){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }else{
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); 
    }
}

    //Endpoint para actualizar el page mediante el ID
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePage (@PathVariable int id, @RequestBody PageDTO pageDTO) {
        ResponseDTO response = pageService.updatePagei(id, pageDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
}
