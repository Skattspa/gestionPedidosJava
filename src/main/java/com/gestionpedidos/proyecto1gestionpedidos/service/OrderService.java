package com.gestionpedidos.proyecto1gestionpedidos.service;
import com.gestionpedidos.proyecto1gestionpedidos.dto.OrderData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gestionpedidos.proyecto1gestionpedidos.model.Order;
import com.gestionpedidos.proyecto1gestionpedidos.model.Usuario;
import com.gestionpedidos.proyecto1gestionpedidos.repository.OrderRepository;
import com.gestionpedidos.proyecto1gestionpedidos.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;




@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Order createNewOrder(Long idUsuario, String nombreOrden, double precio) throws OrderServiceException {

        logger.info("Creating new order" + nombreOrden + "to the user" + idUsuario);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario == null) {
            throw new OrderServiceException("Order"+ nombreOrden+ " not found");
        }
        Order order = new Order(usuario, nombreOrden, precio);
        orderRepository.save(order);
        return modelMapper.map(order, Order.class);
    }

    @Transactional(readOnly = true)
    public List<OrderData> findAllOrders(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        logger.debug("Finding all orders of the user" +idUsuario);

        // orderRepository.findAll();

         List<OrderData> orders = usuario.getOrders().stream()
                .map(order -> modelMapper.map(order, OrderData.class))
                .collect(Collectors.toList());
         Collections.sort(orders, (o1, o2) -> o1.getId() < o2.getId() ? -1 : o1.getId() == o2.getId() ? 0 : 1);
         return orders;
    }

    // Duda: ¿Debería ser @Transactional(rollbackFor = Exception.class)? ¿Que significa?
    @Transactional
    public void updateOrder(Long idOrder, String nuevoNombreOrden) throws OrderServiceException {
        logger.debug("Updating order" +idOrder);
        Order order = orderRepository.findById(idOrder).orElse(null);

        if (order == null) {
            throw new OrderServiceException("Order" + idOrder + "not found");
        }
        order.setTitulo(nuevoNombreOrden);
        order = orderRepository.save(order);
        modelMapper.map(order, OrderData.class);
    }

    private boolean someConditionFails(Long idOrder) {
        Order order = orderRepository.findById(idOrder).orElse(null);
        // Simula una condición que falla
        if(order == null){
            throw new OrderServiceException("Order" + idOrder + "not found");
        }
        return false;
    }
}
