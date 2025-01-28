package com.gestionpedidos.proyecto1gestionpedidos.service;

import com.gestionpedidos.proyecto1gestionpedidos.model.Order;
import com.gestionpedidos.proyecto1gestionpedidos.model.Usuario;
import com.gestionpedidos.proyecto1gestionpedidos.repository.OrderRepository;
import com.gestionpedidos.proyecto1gestionpedidos.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
// Se ejecuta solo si el perfil activo es 'dev'
@Profile("dev")
public class InitDbService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrderRepository orderRepository;

    // Se ejecuta tras crear el contexto de la aplicación
    // para inicializar la base de datos
    @PostConstruct
    public void initDatabase() {
        Usuario usuario = new Usuario("user@ua");
        usuario.setNombre("Usuario Ejemplo");
        usuario.setPassword("123");
        usuarioRepository.save(usuario);

        Order order1 = new Order(usuario, "Lavar coche", 30);
        orderRepository.save(order1);

        Order order2 = new Order(usuario, "Renovar DNI",14);
        orderRepository.save(order2);
    }

}
