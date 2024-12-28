package com.gestionpedidos.proyecto1gestionpedidos.repository;
import org.springframework.data.repository.CrudRepository;
import com.gestionpedidos.proyecto1gestionpedidos.model.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {
}
