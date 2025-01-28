package com.gestionpedidos.proyecto1gestionpedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionpedidos.proyecto1gestionpedidos.model.Order;


//Propuesta anterior //public interface OrderRepository extends CrudRepository<Order, Long> {
//}
public interface OrderRepository extends JpaRepository<Order, Long> {
}
