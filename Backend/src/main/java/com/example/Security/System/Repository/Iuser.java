package com.example.Security.System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.Security.System.Model.User;

public interface Iuser  extends JpaRepository <User, Integer> {

    /*
     * C
     * R
     * U
     * D
     */

    //Busca clientes cuyo nombre contenga un filtro específico.
    @Query("SELECT us FROM user us WHERE us.user_name LIKE %?1%")
    List<User> getListUserForName(String filter);

    //Esta funcion es para obtener todos los clientes que esten activos 
    @Query("SELECT us FROM user us WHERE us.is_active != false")
    List<User> getListUserActive();

    //Desactiva un cliente estableciendo su campo status a false según su ID.
    @Modifying
    @Transactional
    @Query("UPDATE user u SET u.is_active = false WHERE u.user_id = :user_id")
    void desactivateUser(@Param("user_id") int user_id);

    //Filtra clientes por su estado (status), que puede ser true (activos) o false (inactivos).
    //@Query("SELECT u FROM user u WHERE u.is_active = :is_active")
    //List<User> findByStatus(@Param("is_active") boolean is_activek);
    
}
