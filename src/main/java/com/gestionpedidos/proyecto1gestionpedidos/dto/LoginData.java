package com.gestionpedidos.proyecto1gestionpedidos.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



// Clase de datos para el formulario de login
public class LoginData {
    private String eMail;
    private String password;

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
