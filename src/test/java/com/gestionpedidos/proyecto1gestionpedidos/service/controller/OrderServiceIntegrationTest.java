package com.gestionpedidos.proyecto1gestionpedidos.service.controller;

import com.gestionpedidos.proyecto1gestionpedidos.model.Order;
import com.gestionpedidos.proyecto1gestionpedidos.repository.OrderRepository;
import com.gestionpedidos.proyecto1gestionpedidos.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class OrderServiceIntegrationTest {

        @Autowired
        private OrderService orderService;

        @Autowired
        private OrderRepository orderRepository;

        @Test
        public void testRealizarYObtenerPedido() {
            Order order = new Order();
            order.setTitulo("Pedido de prueba");
            order.setPrecio(100.0);
            order.setId(1L);

            // Realizar el pedido
            Order resultado = orderService.createNewOrder(order.getId(),order.getTitulo(),order.getPrecio());

            // Verificar que el pedido se haya guardado
            assertThat(resultado).isNotNull();
            assertThat(resultado.getId()).isNotNull();

            // Obtener el pedido
            Order ordenObtenida = (Order) orderService.findAllOrders(resultado.getId());

            // Verificar que el pedido obtenido sea el mismo que el guardado
            assertThat(ordenObtenida).isNotNull();
            assertThat(ordenObtenida.getTitulo()).isEqualTo("Pedido de prueba");
        }


}
