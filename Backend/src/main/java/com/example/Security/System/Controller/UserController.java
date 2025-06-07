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

import com.example.Security.System.DTO.UserDTO; // Importar la clase UserDTO
import com.example.Security.System.Service.UserService; // Importar el servicio de usuarios
import com.example.Security.System.Model.User; // Importar la clase User

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService; // Inyección del servicio de usuarios

    // Endpoint para insertar un nuevo usuario
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> insertUser(@RequestBody UserDTO userDTO) {
        ResponseDTO response = userService.insertUser(userDTO); // Llamar al servicio para insertar el usuario
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Retornar respuesta con estado 201 Created
    }

    // Endpoint para listar todos los usuarios activos
    @GetMapping("/")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers(); // Llamar al servicio para listar usuarios
        return new ResponseEntity<>(users, HttpStatus.OK); // Retornar lista de usuarios con estado 200 OK
    }

    // Endpoint para buscar un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        return userService.findUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK)) // Retornar usuario encontrado con estado 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retornar estado 404 Not Found si no se encuentra el usuario
    }

    // Endpoint para eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int id) {
        ResponseDTO response = userService.deleteUser(id);
        if (response.getMessage().equals("Usuario desactivado correctamente")) {
            return new ResponseEntity<>(response, HttpStatus.OK); // <-- aquí sí envías el response
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        ResponseDTO response = userService.updateUser(id, userDTO); // Llamar al servicio para actualizar el usuario
        if (response.getMessage().equals("Usuario actualizado correctamente")) {
            return new ResponseEntity<>(response, HttpStatus.OK); // Retornar respuesta con estado 200 OK si se actualizó correctamente
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Retornar respuesta con estado 404 Not Found si no se encontró el usuario
        }
    }

    // Endpoint para buscar usuarios por nombre
    @GetMapping("/filter/{filter}")
    public ResponseEntity<List<User>> getListUserForName(@PathVariable String filter) { 
        List<User> users = userService.getListUserForName(filter); // Llamar al servicio para filtrar usuarios por nombre
        return new ResponseEntity<>(users, HttpStatus.OK); // Retornar lista de usuarios filtrados con estado 200 OK
    }
    
}
