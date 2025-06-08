package com.example.Security.System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

import com.example.Security.System.DTO.RecoveryRequestDTO;
import com.example.Security.System.Service.RecoveryRequestService;
//import com.example.Security.System.Model.RecoveryRequest;

//import java.util.List;

@RestController
@RequestMapping("/api/v1/recovery")
public class RecoveryRequesteController {

    @Autowired
    private RecoveryRequestService recoveryRequestService;

    //Endopoint para insertar un RecoveryRequest
    @PostMapping("/")
    public ResponseEntity<ResponseDTO>  insertRecovery (@RequestBody RecoveryRequestDTO recoveryRequestDTO) {
        ResponseDTO response = recoveryRequestService.insertRecoveryRequest(recoveryRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    
}
