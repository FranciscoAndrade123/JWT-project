package com.example.Security.System.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;


@Entity (name="recovery_request")
public class RecoveryRequest {

      /*
     *  Atributos  o columnas de la tabla user
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recovery_id")
    private int recovery_id;

     // Relación con la tabla user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    // La generación del token de recuperación
    @Column(name="recovery_token", length = 6, nullable = false)
    private String recovery_token;

    //Para saber si la solicitud de recuperación está activa
    @Column(name="is_used", nullable = false ,  columnDefinition = "boolean default false")
    private Boolean is_used;

    //La fecha de expiración de la solicitud de recuperación
    @Column(name="expires_at", nullable = false)
    private LocalDateTime expires_at;

    
    @PrePersist
    public void prePersist() {
    if (this.recovery_token == null) {
        int token = (int)(Math.random() * 900000) + 100000;
        this.recovery_token = String.valueOf(token);
    }
     if (this.expires_at == null) {
        this.expires_at = LocalDateTime.now().plusMinutes(15); // Expira en 15 minutos
    }
    }

    // Constructor por defecto de JPA
    public RecoveryRequest() {
    }

    // Constructor con parámetros
    public RecoveryRequest(int recovery_id, User user_id, String recovery_token, Boolean is_used, LocalDateTime expires_at) {
        this.recovery_id = recovery_id;
        this.user_id = user_id;
        this.recovery_token = recovery_token;
        this.is_used = is_used;
        this.expires_at = expires_at;
    }

    // Getters y Setters
    public int getRecovery_id() {
        return recovery_id;
    }

    public void setRecovery_id(int recovery_id) {
        this.recovery_id = recovery_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getRecovery_token() {
        return recovery_token;
    }

    public void setRecovery_token(String recovery_token) {
        this.recovery_token = recovery_token;
    }

    public Boolean getIs_used() {
        return is_used;
    }

    public void setIs_used(Boolean is_used) {
        this.is_used = is_used;
    }

    public LocalDateTime getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(LocalDateTime expires_at) {
        this.expires_at = expires_at;
    }

}
