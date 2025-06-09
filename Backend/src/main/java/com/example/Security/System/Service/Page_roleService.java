package com.example.Security.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

import com.example.Security.System.Model.Page_role; // Importar la clase Page_role
import com.example.Security.System.Model.Page; // Importar la clase Page
import com.example.Security.System.Model.Rol; // Importar la clase Rol

import com.example.Security.System.Repository.Ipage_role; // Importar la interfaz Ipage_role
import com.example.Security.System.Repository.Ipage; // Importar la interfaz Ipage
import com.example.Security.System.Repository.Irol; // Importar la interfaz Irol

import com.example.Security.System.DTO.Page_roleDTO; // Importar la clase Page_roleDTO

@Service
public class Page_roleService {

    @Autowired
    private Ipage_role page_roleRepository;

    @Autowired
    private Ipage pageRepository;

    @Autowired
    private Irol rolRepository;

    //Insertar un Page_role
    public ResponseDTO insertPage_role(Page_roleDTO page_roleDTO) {
        Page_role page_role = new Page_role();
        page_role = convertirEntidad(page_roleDTO); // Convertir de DTO a entidad
        page_roleRepository.save(page_role);
        return new ResponseDTO("Page_role insertado con éxito",HttpStatus.OK.toString());
    }

    //Lista todos los Page_role
    public List<Page_role> listPage_roles (){
        return page_roleRepository.findAll();
    }

    //Buscar por ID del Page_role 
    public Optional<Page_role> findPageRolByID(int id){
        return page_roleRepository.findById(id);
    }
public ResponseDTO deletePageRol(int id){
    Optional<Page_role> page_roleOptional = page_roleRepository.findById(id);
    if(page_roleOptional.isPresent()){
        pageRepository.deleteById(id); // <-- Aquí está el error
        return new ResponseDTO("PageRole eliminado correctamente", HttpStatus.OK.toString());
    }else{
        return new ResponseDTO("PageRole no encontrado", HttpStatus.NOT_FOUND.toString());
    }
}

    //Actualizar el Page_role mediante el ID
    public ResponseDTO updatePageRol(int id, Page_roleDTO page_roleDTO){
        Optional <Page_role> existingPageRolOpt = page_roleRepository.findById(id);
        if(existingPageRolOpt.isPresent()){
            Page_role existingPageRole =  existingPageRolOpt.get();
            Rol rol = rolRepository.findById(page_roleDTO.getRol_id())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            Page page = pageRepository.findById(page_roleDTO.getPage_id())
                .orElseThrow(() -> new RuntimeException("Page no encontrado"));
            existingPageRole.setRol_id(rol);
            existingPageRole.setPage_id(page);

            page_roleRepository.save(existingPageRole);
            return new ResponseDTO("PageRole actualizado correctamente", HttpStatus.OK.toString());
        }else{
            return new ResponseDTO("PageRole no encontrado", HttpStatus.NOT_FOUND.toString());
        }
    }


    //Convertir de DTO a entidad
    public Page_roleDTO convertirDTO(Page_role page_role) {
        Page_roleDTO page_roleDTO = new Page_roleDTO();
        page_roleDTO.setPage_id(page_role.getPage_id().getPage_id());
        page_roleDTO.setRol_id(page_role.getRol_id().getRol_id());
        return page_roleDTO;
    }

    //Convertir de entidad a DTO
   public Page_role convertirEntidad(Page_roleDTO page_roleDTO) {
    Page_role page_role = new Page_role();
    // Buscar la entidad Page por su ID
    Page page = pageRepository.findById(page_roleDTO.getPage_id())
        .orElseThrow(() -> new RuntimeException("Página no encontrada"));
    // Buscar la entidad Rol por su ID
    Rol rol = rolRepository.findById(page_roleDTO.getRol_id())
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    page_role.setPage_id(page);
    page_role.setRol_id(rol);
    return page_role;
    }
    
}
