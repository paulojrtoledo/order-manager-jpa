Order Manager - Sistema de Pedidos com JPA
Sistema desenvolvido para estudo aprofundado de mapeamento de relacionamentos com JPA/Hibernate em uma aplicação Spring Boot.

O projeto simula um domínio de gestão de pedidos, onde é possível cadastrar:

Categorias (ex: eletrônicos, games)

Fornecedores (ex: atlantic, game now)

Produtos (vinculados a uma categoria e um fornecedor)

Pedidos (que agregam múltiplos produtos)

O foco principal está na camada de persistência, explorando como mapear corretamente os relacionamentos entre entidades, controlar carregamento de dados e manter a consistência do banco relacional.


🧩 Entidades e Relacionamentos

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
    @Id @GeneratedValue
    private Long id;
    private String name;
}

📌 O que foi implementado
Conceito	Implementação
@ManyToOne	Product → Category / Product → Supplier
@OneToMany	Category → Products (bidirecional)
@ManyToMany	Order ↔ Product (com tabela intermediária)
Cascade	CascadeType.ALL em Category → Products
Fetch	EAGER em Category.products e Order.products
Bidirecionalidade	setProducts() atualiza ambos os lados
Chave estrangeira	@JoinColumn(name = "supplier_id")

🛠️ Tecnologias
Java 17
Spring Boot
Spring Data JPA
PostgreSQL
Maven

▶️ Como executar
1. Configure as variáveis de ambiente:

properties
DB_OM_URL=jdbc:postgresql://localhost:5432/seu_banco
DB_OM_USERNAME=seu_usuario
DB_OM_PASSWORD=sua_senha

2. Execute a aplicação:

bash
./mvnw spring-boot:run

📊 Exemplo de saída
text
Products on the electronics category: 
 - notebook
 - Mouse
 - Audio Interface

Orders and its respective products: 
Order 1:
 - notebook
 - Mouse
 - Audio Interface
 - the last of us
 - elden ring
 - playstation 5

Products and its respective suppliers: 
Product: notebook, Supplier: atlantic
Product: Mouse, Supplier: atlantic
Product: Audio Interface, Supplier: atlantic
Product: the last of us, Supplier: game now

🧠 Aprendizados
Mapeamento de relacionamentos @OneToMany, @ManyToOne e @ManyToMany
Uso de cascade para propagar operações
Diferença entre FetchType.LAZY e EAGER
Relacionamentos bidirecionais e consistência de dados
Configuração de chaves estrangeiras e tabelas de junção

Projeto de estudo - Programa ONE (Oracle + Alura)
