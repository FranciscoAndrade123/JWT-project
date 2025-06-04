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

import com.example.Security.System.DTO.RolDTO; // Importar la clase RolDTO
import com.example.Security.System.Service.RolService; // Importar el servicio de roles
import com.example.Security.System.Model.Rol; // Importar la clase Rol

import java.util.List;

@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

     @Autowired
        private RolService rolService; // Inyección del servicio de roles

    // Endpoint para insertar un nuevo rol
     @PostMapping("/")
     public ResponseEntity<ResponseDTO> insertRol(@RequestBody RolDTO rolDTO) {
         ResponseDTO response = rolService.insertRol(rolDTO); // Llamar al servicio para insertar el rol
         return new ResponseEntity<>(response, HttpStatus.CREATED); // Retornar respuesta con estado 201 Created
     }

    // Endpoint para listar todos los roles
    @GetMapping("/")
    public ResponseEntity<List<Rol>> listAllRoles() {
        List<Rol> roles = rolService.listAllRoles(); // Llamar al servicio para listar roles
        return new ResponseEntity<>(roles, HttpStatus.OK); // Retornar lista de roles con estado 200 OK
    }

    // Endpoint para buscar un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> findRolById(@PathVariable int id) {
        return rolService.findRolById(id)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK)) // Retornar rol encontrado con estado 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retornar estado 404 Not Found si no se encuentra el rol
    }

    // Endpoint para eliminar un rol por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteRol(@PathVariable int id) {
        ResponseDTO response = rolService.deleteRol(id); // Llamar al servicio para eliminar el rol
        if (response.getMessage().equals("Rol eliminado correctamente")) {
            return new ResponseEntity<>(response, HttpStatus.OK); // Retornar respuesta con estado 200 OK si se eliminó correctamente
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Retornar respuesta con estado 404 Not Found si no se encontró el rol
        }
    }

    // Endpoint para actualizar un rol existente
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateRol(@PathVariable int id, @RequestBody RolDTO rolDTO) {
        var message = rolService.updateRol(id, rolDTO); // Pasa el id al servicio
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    
}
