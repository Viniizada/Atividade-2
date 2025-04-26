package com.exemplo.demo.controller;
import com.exemplo.demo.model.Product;
import com.exemplo.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET: http://localhost:8080/api/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
	// GET: Buscar produto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Optional<Product> product = productService.getProductById(id);
    return product.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
}

	// PUT: Atualizar um produto existente
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

    Optional<Product> product = productService.updateProduct(id, updatedProduct);
    return product.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());

}
	// DELETE: Remover um produto por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    boolean deleted = productService.deleteProduct(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
}

    // POST: http://localhost:8080/api/products
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
