package com.example.Security.System.Controller;

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

import com.example.Security.System.DTO.Page_roleDTO; // Importar la clase Page_roleDTO
import com.example.Security.System.Model.Page_role; // Importar la clase Page_role
import com.example.Security.System.Service.Page_roleService; // Importar la clase Page_roleService


import java.util.List;


@RestController
@RequestMapping("/api/v1/pageRole")
public class Page_roleController {

    @Autowired
    private Page_roleService page_roleService;

    //Endpoint para insertar un page_role
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> insertPageRole(@RequestBody Page_roleDTO page_roleDTO ){
        ResponseDTO response = page_roleService.insertPage_role(page_roleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Endpoint para listar todos los page_role
    @GetMapping("/")
    public ResponseEntity<List<Page_role>> listPageRole(){
        List<Page_role> page_roles = page_roleService.listPage_roles();
        return new ResponseEntity<>(page_roles, HttpStatus.OK);
    }

    //Endpoint para buscar un page_role por id
    @GetMapping("/{id}")
    public ResponseEntity<Page_role> findPageRole(@PathVariable int id) {
    return page_roleService.findPageRolByID(id)
        .map(pageRole -> new ResponseEntity<>(pageRole, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint para eliminar un page_role por id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePageRole (@PathVariable int id  ){
        ResponseDTO response = page_roleService.deletePageRol(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Endpoint para actualizar un page_role
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePageRole (@PathVariable int id, @RequestBody Page_roleDTO page_roleDTO ){
        ResponseDTO response = page_roleService.updatePageRol(id, page_roleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
