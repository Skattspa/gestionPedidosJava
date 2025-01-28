package com.gestionpedidos.proyecto1gestionpedidos.model;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    private double precio;

    @ManyToOne
    // Nombre de la columna en la BD que guarda físicamente
    // el ID del usuario con el que está asociado una orden
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructor vacío necesario para JPA/Hibernate.
    // No debe usarse desde la aplicación.
    public Order() {}

    @NotNull
    // Relación muchos-a-uno entre ordenes y usuario
    // Al crear una orden la asociamos automáticamente a un usuario
    public Order(Usuario usuario, String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
        setUsuario(usuario); // Esto añadirá la orden a la lista de ordenes del usuario
    }

    // Getters y setters básicos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getPrecio() {
        return precio;
    }

    // Getters y setters de la relación muchos-a-uno con Usuario

    public Usuario getUsuario() {
        return usuario;
    }

    // Método para establecer la relación con el usuario

    public void setUsuario(Usuario usuario) {
        // Comprueba si el usuario ya está establecido
        if(this.usuario != usuario) {
            this.usuario = usuario;
            // Añade la orden a la lista de ordenes del usuario
            usuario.addOrder(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (id != null && order.id != null)
            // Si tenemos los ID, comparamos por ID
            return Objects.equals(id, order.id);
        // si no comparamos por campos obligatorios
        return titulo.equals(order.titulo) &&
                usuario.equals(order.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, usuario);
    }
}

