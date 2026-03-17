# Order Manager - JPA Relationship Mapping

System developed for in-depth study of entity relationship mapping with JPA/Hibernate in a Spring Boot application.

The project simulates an order management domain, allowing you to register:
- **Categories** (e.g. electronics, games)
- **Suppliers** (e.g. atlantic, game now)
- **Products** (linked to a category and a supplier)
- **Orders** (aggregating multiple products)

The main focus is on the persistence layer, exploring how to correctly map relationships between entities, control data loading and maintain relational database consistency.

---

## 🧩 Entities and Relationships

```java
// Product.java
@ManyToOne
private Category category;

@ManyToOne
@JoinColumn(name = "supplier_id")
private Supplier supplier;

// Category.java
@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Product> products = new ArrayList<>();

// Order.java
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "order_product",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id")
)
private List<Product> products;

// Supplier.java
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
```

---

## 📌 What was implemented

| Concept | Implementation |
|---|---|
| `@ManyToOne` | Product → Category / Product → Supplier |
| `@OneToMany` | Category → Products (bidirectional) |
| `@ManyToMany` | Order ↔ Product (with join table) |
| Cascade | `CascadeType.ALL` on Category → Products |
| Fetch | `EAGER` on Category.products and Order.products |
| Bidirectionality | `setProducts()` updates both sides |
| Foreign key | `@JoinColumn(name = "supplier_id")` |

---

## 🛠️ Technologies

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

---

## ▶️ How to run

1. Configure environment variables:

```properties
DB_OM_URL=jdbc:postgresql://localhost:5432/your_database
DB_OM_USERNAME=your_username
DB_OM_PASSWORD=your_password
```

2. Run the application:

```bash
./mvnw spring-boot:run
```

---

## 📊 Sample output

```
Products on the electronics category:
 * notebook
 * Mouse
 * Audio Interface

Orders and its respective products:
Order 1:
 * notebook
 * Mouse
 * Audio Interface
 * the last of us
 * elden ring
 * playstation 5

Products and its respective suppliers:
Product: notebook, Supplier: atlantic
Product: Mouse, Supplier: atlantic
Product: Audio Interface, Supplier: atlantic
Product: the last of us, Supplier: game now
```

---

## 🧠 Key learnings

- Mapping `@OneToMany`, `@ManyToOne` and `@ManyToMany` relationships
- Using cascade to propagate operations
- Difference between `FetchType.LAZY` and `EAGER`
- Bidirectional relationships and data consistency
- Configuring foreign keys and join tables

---

## 📝 About

Study project — ONE Program (Oracle Next Education + Alura)

**Author:** Paulo Emilio de Toledo Jr
[LinkedIn](https://www.linkedin.com/in/pauloemilio-tech) | [GitHub](https://github.com/paulojrtoledo)
