package com.gestionpedidos.proyecto1gestionpedidos.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import  javax.validation.constraints.NotNull;
import  javax.validation.constraints.Size;
import  javax.validation.constraints.Pattern;
import  javax.validation.constraints.AssertTrue;

// Clase de datos para el formulario de registro
public class RegistroData {
    @Email
    private String eMail;
    private String password;
    private String nombre;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}