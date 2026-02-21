package br.com.alura.order_manager.repository;

import br.com.alura.order_manager.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
