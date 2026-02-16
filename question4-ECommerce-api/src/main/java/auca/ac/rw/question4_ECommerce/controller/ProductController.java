package auca.ac.rw.question4_ECommerce.controller;

import auca.ac.rw.question4_ECommerce.modal.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private List<Product> products = new ArrayList<>(); 
    private long nextId = 1;

    public ProductController() {
        products.add(createProduct("Laptop", "High performance laptop", 999.99, "Electronics", "Dell", 10));
        products.add(createProduct("Smartphone", "Latest model smartphone", 699.99, "Electronics", "Samsung", 20));
        products.add(createProduct("Headphones", "Noise cancelling headphones", 199.99, "Electronics", "Sony", 15));
        products.add(createProduct("T-Shirt", "Cotton t-shirt", 29.99, "Clothing", "Nike", 50));
        products.add(createProduct("Jeans", "Blue denim jeans", 59.99, "Clothing", "Levi's", 30));
        products.add(createProduct("Running Shoes", "Comfortable running shoes", 89.99, "Footwear", "Adidas", 25));
        products.add(createProduct("Watch", "Smart watch", 299.99, "Electronics", "Apple", 12));
        products.add(createProduct("Backpack", "Travel backpack", 49.99, "Accessories", "Nike", 40));
        products.add(createProduct("Coffee Maker", "Automatic coffee maker", 79.99, "Appliances", "Philips", 8));
        products.add(createProduct("Desk Chair", "Ergonomic office chair", 149.99, "Furniture", "IKEA", 0));
    }

    private Product createProduct(String name, String description, double price, String category, String brand, int stock) {
        Product p = new Product();
        p.setProductId(nextId++);
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setCategory(category);
        p.setBrand(brand);
        p.setStockQuantity(stock);
        return p;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            return ResponseEntity.ok(products.subList(start, end));
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable long productId) {
        return products.stream().filter(p -> p.getProductId() == productId).findFirst()
            .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(products.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList()));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(products.stream().filter(p -> p.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(products.stream().filter(p -> 
            p.getName().toLowerCase().contains(keyword.toLowerCase()) || 
            p.getDescription().toLowerCase().contains(keyword.toLowerCase())
        ).collect(Collectors.toList()));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(products.stream().filter(p -> p.getPrice() >= min && p.getPrice() <= max).collect(Collectors.toList()));
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStock() {
        return ResponseEntity.ok(products.stream().filter(p -> p.getStockQuantity() > 0).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId, @RequestBody Product updated) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId) {
                updated.setProductId(productId);
                products.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable long productId, @RequestParam int quantity) {
        return products.stream().filter(p -> p.getProductId() == productId).findFirst().map(p -> {
            p.setStockQuantity(quantity);
            return ResponseEntity.ok(p);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        return products.removeIf(p -> p.getProductId() == productId) ? 
            ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
