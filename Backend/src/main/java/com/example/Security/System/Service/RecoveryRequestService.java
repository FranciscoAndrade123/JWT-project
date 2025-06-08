package com.example.Security.System.Service;

//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

import com.example.Security.System.Model.RecoveryRequest; // Importar la clase RecoveryRequest
import com.example.Security.System.Model.User; // Importar la clase User

import com.example.Security.System.DTO.RecoveryRequestDTO; // Importar la clase RecoveryRequestDTO

import com.example.Security.System.Repository.Iuser; // Importar la interface Iuser
import com.example.Security.System.Repository.IrecoveryRequest; // Importar la interface IrecoveryRequest


@Service
public class RecoveryRequestService {

    @Autowired
    private Iuser userRepository; // Inyección del repositorio de usuarios

    @Autowired
    private IrecoveryRequest recoveryRequestRepository; // Inyección del repositorio de RecoveryRequest

    //Insertar un RecoveryRequest
    public ResponseDTO insertRecoveryRequest(RecoveryRequestDTO recoveryRequestDTO) {
        RecoveryRequest recoveryRequest = new RecoveryRequest();
        recoveryRequest = convRecoveryReques(recoveryRequestDTO);
        recoveryRequestRepository.save(recoveryRequest);

        return new ResponseDTO ("Se guardo correctamente", HttpStatus.OK.toString());
    }

    //convertir de DTO a entidad
    public RecoveryRequestDTO convRecoveryRequestDTO (RecoveryRequest recoveryRequest){
        RecoveryRequestDTO recoveryRequestDTO = new RecoveryRequestDTO();
        recoveryRequestDTO.setUser_id(recoveryRequest.getUser_id().getUser_id());
        return recoveryRequestDTO;
    }

    //Convertir de entidad a DTO
    public RecoveryRequest convRecoveryReques (RecoveryRequestDTO recoveryRequestDTO){
    RecoveryRequest recoveryRequest = new RecoveryRequest();
    User user = userRepository.findById(recoveryRequestDTO.getUser_id())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    recoveryRequest.setUser_id(user);
    recoveryRequest.setIs_used(false); // <-- Asigna valor por defecto
    // No necesitas asignar recovery_token ni expires_at, se generan automáticamente en el modelo
    return recoveryRequest; 
}
    
}
