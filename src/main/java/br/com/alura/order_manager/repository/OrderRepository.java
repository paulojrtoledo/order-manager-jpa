package br.com.alura.order_manager.repository;

import br.com.alura.order_manager.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
