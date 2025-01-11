package com.gestionpedidos.proyecto1gestionpedidos.service.controller;

import com.gestionpedidos.proyecto1gestionpedidos.dto.OrderData;
import com.gestionpedidos.proyecto1gestionpedidos.dto.UsuarioData;
import com.gestionpedidos.proyecto1gestionpedidos.model.Order;
import com.gestionpedidos.proyecto1gestionpedidos.model.Usuario;
import com.gestionpedidos.proyecto1gestionpedidos.service.OrderService;
import com.gestionpedidos.proyecto1gestionpedidos.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;
    @Autowired
    UsuarioService usuarioService;

    // Método para inicializar los datos de prueba en la BD
    // Devuelve un mapa con los identificadores del usuario y de la primera tarea añadida
    Map<String, Long> addUsuarioTareasBD() {
        UsuarioData usuario = new UsuarioData();
        usuario.setEmail("user@ua");
        usuario.setPassword("123");

        // Añadimos un usuario a la base de datos
        UsuarioData usuarioNuevo = usuarioService.registrar(usuario);

        // Y añadimos dos tareas asociadas a ese usuario
        Order order1 = orderService.createNewOrder(usuarioNuevo.getId(), "Comprar patatas");
                //.nuevaTareaUsuario(usuarioNuevo.getId(), "Lavar coche");
        orderService.createNewOrder(usuarioNuevo.getId(), "Comprar limones");

        // Devolvemos los ids del usuario y de la primera tarea añadida
        Map<String, Long> ids = new HashMap<>();
        ids.put("usuarioId", usuarioNuevo.getId());
        ids.put("tareaId", order1.getId());
        return ids;
    }

    @Test
    public void testCreateOrder() throws Exception {
        String orderJson = "{\"id\": 1, \"description\": \"Test Order\", \"amount\": 100.0}";
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateOrderWithError() {
        UsuarioData usuario = new UsuarioData();
        usuario.setEmail("user@ua");
        usuario.setPassword("123");
        //UsuarioData usuarioNuevo = usuarioService.registrar(usuario);

        // Crear un nuevo pedido
        Order order = orderService.createNewOrder(usuario.getId(), "Test Order");

        // Intentar actualizar el pedido con un objeto OrderData
        OrderData orderData = new OrderData();
        orderData.setId(order.getId());
        orderData.setTitulo("Updated Order");

        assertThrows(Exception.class, () -> {
            orderService.updateOrder(order.getId(), orderData.getTitulo());
        });
    }
}