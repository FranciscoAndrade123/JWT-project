package com.example.Security.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Security.System.DTO.RolDTO; // Importar la clase DTO
import com.example.Security.System.Model.Rol; // Importar la clase Rol
import com.example.Security.System.Repository.Irol; // Importar el repositorio

import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

@Service
public class RolService {

    @Autowired
    private Irol rolRepository; // Inyección del repositorio de roles

    
    // Insertar un nuevo rol
    public ResponseDTO insertRol(RolDTO rolDTO) {
        Rol rol = convertToRol(rolDTO); // Convertir el DTO a entidad
        rolRepository.save(rol); // Guardar la entidad en la base de datos
        return new ResponseDTO("Se guardó correctamente" ,  HttpStatus.OK.toString()); // Retornar una respuesta vacía
    }

    // Listar todos los roles
    public List<Rol> listAllRoles() {
        return rolRepository.findAll(); // Retornar la lista de roles
    }

    // Buscar un rol por su ID
    public Optional<Rol> findRolById(int id) {
        return rolRepository.findById(id); // Retornar el rol si existe
    }

    // Eliminar un rol por su ID
    public ResponseDTO deleteRol(int id) {
        Optional<Rol> rolOptional = rolRepository.findById(id); // Buscar el rol por ID
        if (rolOptional.isPresent()) { // Verificar si el rol existe
            rolRepository.deleteById(id); // Eliminar el rol
            return new ResponseDTO("Rol eliminado correctamente", HttpStatus.OK.toString()); // Retornar respuesta de éxito
        } else {
            return new ResponseDTO("Rol no encontrado", HttpStatus.NOT_FOUND.toString()); // Retornar respuesta de error si no se encuentra el rol
        }
    }

    // Actualizar un rol existente
    public ResponseDTO updateRol(int id, RolDTO rolDTO) {
        Optional<Rol> existingRol = rolRepository.findById(id); // Buscar el rol por ID
        if (existingRol.isPresent()) { // Verificar si el rol existe
            Rol rol = convertToRol(rolDTO); // Convertir el DTO a entidad
            rol.setRol_id(id); // Asignar el ID recibido
            rolRepository.save(rol); // Guardar la entidad actualizada
            return new ResponseDTO("Rol actualizado correctamente", HttpStatus.OK.toString()); // Retornar respuesta de éxito
        } else {
            return new ResponseDTO("Rol no encontrado", HttpStatus.NOT_FOUND.toString()); // Retornar respuesta de error si no se encuentra el rol
        }
    }
        
    // Convertir de Entidad a  DTO
    public RolDTO convertToDTO(Rol rol) {
        RolDTO rolDTO = new RolDTO(); // Crear una instancia de RolDTO
        rolDTO.setRol_name(rol.getRol_name()); // Asignar el nombre del rol
        return rolDTO; // Retornar el DTO
    }

    // Convertir de DTO a Entidad
    public Rol convertToRol(RolDTO rolDTO) {
        Rol rol = new Rol(); // Crear una instancia de Rol
        rol.setRol_name(rolDTO.getRol_name()); // Asignar el nombre del rol desde el DTO
        return rol; // Retornar la entidad
    }




}
