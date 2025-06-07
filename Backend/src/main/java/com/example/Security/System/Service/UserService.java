package com.example.Security.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

import com.example.Security.System.Model.User; // Importar la clase User
import com.example.Security.System.Repository.Iuser; // Importar el repositorio de usuarios
import com.example.Security.System.DTO.UserDTO; // Importar la clase DTO
import com.example.Security.System.Repository.Irol;
import com.example.Security.System.Model.Rol; // Importar la clase Rol

@Service
public class UserService {

    @Autowired
    private Iuser userRepository; // Inyección del repositorio de usuarios

    @Autowired
    private Irol rolRepository; // Repositorio de roles

    // Insertar un nuevo usuario
    public ResponseDTO insertUser(UserDTO userDTO) {
        User user = new User();
        user = convUser(userDTO); // Convertir el DTO a entidad
        userRepository.save(user); // Guardar la entidad en la base de datos
        return new ResponseDTO("Se guardó correctamente", HttpStatus.OK.toString()); // Retornar una respuesta 
    }

    // Listar todos los usuarios activos
    public List<User> listUsers() {
        return  userRepository.getListUserActive(); // Retornar la lista de usuarios activos
    }

    // Buscar un usuario por su ID
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id); // Retornar el usuario si existe
    }

    // Eliminar un usuario por su ID
    public ResponseDTO deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id); // Buscar el usuario por ID
        if (userOptional.isPresent()) { // Verificar si el usuario existe
            userRepository.desactivateUser(id); // Desactivar el usuario
            return new ResponseDTO("Usuario desactivado correctamente", HttpStatus.OK.toString()); // Retornar respuesta de éxito
        } else {
            return new ResponseDTO("Usuario no encontrado", HttpStatus.NOT_FOUND.toString()); // Retornar respuesta de error si no se encuentra el usuario
        }
    }

    // Actualizar un usuario existente
    public ResponseDTO updateUser(int id, UserDTO userDTO) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            Rol rol = rolRepository.findById(userDTO.getRol_id())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            existingUser.setRol_id(rol);
            existingUser.setUser_email(userDTO.getUser_email());
            existingUser.setUser_name(userDTO.getUser_name());
            existingUser.setPassword_hash(userDTO.getPassword_hash());
            // NO modificar created_at, se mantiene el original
            userRepository.save(existingUser);
            return new ResponseDTO("Usuario actualizado correctamente", HttpStatus.OK.toString());
        } else {
            return new ResponseDTO("Usuario no encontrado", HttpStatus.NOT_FOUND.toString());
        }
    }

    //Filtrar usuarios por nombre
    public List<User> getListUserForName(String filter) {
        return userRepository.getListUserForName(filter); // Retornar la lista de usuarios filtrados por nombre
    }

    
    // Convertir de DTO a entidad
    public UserDTO convUserDTO (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setRol_id(user.getRol_id().getRol_id()); // Obtener el id real del rol
        userDTO.setUser_email(user.getUser_email());
        userDTO.setUser_name(user.getUser_name());
        userDTO.setPassword_hash(user.getPassword_hash());
        return userDTO;
    }

    // Convertir de entidad a DTO
    public User convUser (UserDTO userDTO) {
        User user = new User();
        Rol rol = rolRepository.findById(userDTO.getRol_id())
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.setRol_id(rol);
        user.setUser_email(userDTO.getUser_email());
        user.setUser_name(userDTO.getUser_name());
        user.setPassword_hash(userDTO.getPassword_hash());
        user.setIs_active(true); // Por defecto, el usuario está activo al crearlo
        return user;
    }

    
}
