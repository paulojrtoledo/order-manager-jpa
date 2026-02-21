package br.com.alura.order_manager.repository;

import br.com.alura.order_manager.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
