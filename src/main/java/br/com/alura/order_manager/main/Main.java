package br.com.alura.order_manager.main;

import br.com.alura.order_manager.Category;
import br.com.alura.order_manager.Order;
import br.com.alura.order_manager.Product;
import br.com.alura.order_manager.Supplier;
import br.com.alura.order_manager.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.alura.order_manager.repository.CategoryRepository;
import br.com.alura.order_manager.repository.OrderRepository;
import br.com.alura.order_manager.repository.ProductRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class Main {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public void run() {
        List<Category> categories = Arrays.asList(
                new Category("electronics"),
                new Category("games"),
                new Category("console")
        );
        categoryRepository.saveAll(categories);

        Category electronics = categories.get(0);
        Category games = categories.get(1);
        Category console = categories.get(2);

        List<Supplier> suppliers = Arrays.asList(
                new Supplier("atlantic"),
                new Supplier("game now")
        );
        supplierRepository.saveAll(suppliers);

        Supplier atlantic = suppliers.get(0);
        Supplier gameNow = suppliers.get(1);

        List<Product> products = Arrays.asList(
                new Product("notebook", 2500.00, electronics, atlantic),
                new Product("Mouse", 560.00, electronics, atlantic),
                new Product("Audio Interface", 899.00, electronics, atlantic),
                new Product("the last of us", 220.00, games, gameNow),
                new Product("elden ring", 230.00, games, gameNow),
                new Product("playstation 5", 3000.00, console, gameNow)
        );
        productRepository.saveAll(products);

        Order order = new Order(LocalDate.now());
        order.setProducts(products);
        orderRepository.save(order);

        System.out.println("Products on the electronics category: ");
        categoryRepository.findById(1L).ifPresent(c ->
                c.getProducts().forEach(p ->
                        System.out.println(" - " + p.getName())
                )
        );

        System.out.println("\nOrders and its respective products: ");
        orderRepository.findAll().forEach(o  -> {
            System.out.println("Order " + o.getId() + ":");
            o.getProducts().forEach(p ->
                    System.out.println(" - " + p.getName())
            );
        });

        System.out.println("\nProducts and its respective suppliers: ");
        productRepository.findAll().forEach(p ->
                System.out.println("Product: " + p.getName() +
                        ", Supplier: " + p.getSupplier().getName())
        );
    }
}
