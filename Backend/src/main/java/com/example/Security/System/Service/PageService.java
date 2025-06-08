package com.example.Security.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Security.System.DTO.ResponseDTO; // Importar la clase ResponseDTO

import com.example.Security.System.Model.Page; // Importar la clase Page
import com.example.Security.System.DTO.PageDTO; // Importar la clase PageDTO
import com.example.Security.System.Repository.Ipage; // Importar la interfaz Ipage

@Service
public class PageService {
    
    @Autowired
    private Ipage pageRepository;

    //Insertamos un Page
    public ResponseDTO insertPage(PageDTO pageDTO) {
        Page page = new Page();
        page.setPage_name(pageDTO.getPage_name());
        pageRepository.save(page);
        return new ResponseDTO("Se guardó correctamente " ,  HttpStatus.OK.toString());
    }

    //Listar todos los Page
    public List<Page> getAllPage() {
        return pageRepository.findAll();
    }

    //Buscar por ID 
    public Optional <Page> findPageByID (int id){
        return pageRepository.findById(id);
    }     

    // Servicio
public ResponseDTO deletePage(int id) {
    Optional<Page> pageOpt = pageRepository.findById(id);
    if (pageOpt.isPresent()) {
        pageRepository.deleteById(id);
        return new ResponseDTO("Se eliminó correctamente", HttpStatus.OK.toString());
    } else {
        return new ResponseDTO("No se encontró la página", HttpStatus.NOT_FOUND.toString());
    }
}

    //Actualizar un page
    public ResponseDTO updatePagei(int id, PageDTO pageDTO) {
        Optional<Page> pageExisting = pageRepository.findById(id); // Buscar el Page por ID
        if(pageExisting.isPresent()){
            Page page = convertPage(pageDTO);
            page.setPage_id(id);
            pageRepository.save(page);
            return new ResponseDTO("Se actualizó correctamente " ,  HttpStatus.OK.toString());
        }else{
            return new ResponseDTO("No se encontró el Page " ,  HttpStatus.NOT_FOUND.toString());
        }
    }

    //Convertir de entidad a DTO
    public PageDTO convertPageDTO (Page page) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage_name(page.getPage_name());
        return pageDTO;
    }

    //Convertir de DTO a entidad 
    public Page convertPage (PageDTO pageDTO) {
        Page page = new Page();
        page.setPage_name(pageDTO.getPage_name());
        return page;
    }
}
