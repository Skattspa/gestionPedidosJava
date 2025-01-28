package com.gestionpedidos.proyecto1gestionpedidos.service.controller;

import com.gestionpedidos.proyecto1gestionpedidos.model.Order;
import com.gestionpedidos.proyecto1gestionpedidos.repository.OrderRepository;
import com.gestionpedidos.proyecto1gestionpedidos.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class OrderServiceTest {

        @Mock
        private OrderRepository orderRepository;

        @InjectMocks
        private OrderService orderService;

        private Order order;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            order = new Order();
            order.setTitulo("Pedido de prueba");
            order.setPrecio(100.0);
            order.setId(1L);
        }

        @Test
        public void testRealizarOrden() {
            when(orderRepository.save(any(Order.class))).thenReturn(order);

            Order resultado = orderService.createNewOrder(2L, "Pedido de prueba", 100);

            assertNotNull(resultado);
            assertEquals("Orden de prueba", resultado.getTitulo(),resultado.getId());
            verify(orderRepository, times(1)).save(order);
        }

        @Test
        public void testObtenerPedido() {
            when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

            Order resultado = (Order) orderService.findAllOrders(1L);

            assertNotNull(resultado);
            assertEquals("Pedido de prueba", resultado.getTitulo(), resultado.getId());
            verify(orderRepository, times(1)).findById(1L);
        }

}
