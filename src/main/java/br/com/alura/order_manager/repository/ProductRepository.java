package br.com.alura.order_manager.repository;

import br.com.alura.order_manager.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
