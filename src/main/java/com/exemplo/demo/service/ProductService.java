package com.exemplo.demo.service;

import com.exemplo.demo.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Import ainda necessário

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    // GET: Retorna todos os produtos
    public List<Product> getAllProducts() {
        return products;
    }

    // GET: Retorna um produto pelo ID
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                       .filter(p -> p.getId().equals(id)) // Usa getId() que existe
                       .findFirst();
    }

    // POST: Adiciona um novo produto
    public Product addProduct(Product product) {
        // Define apenas o ID e o Name que são recebidos e existem no modelo
        product.setId(nextId++); // Usa setId() que existe
        // Assume que o 'name' já vem no objeto 'product' recebido
        products.add(product);
        return product;
    }

    // PUT: Atualiza um produto existente (AJUSTADO)
    public Optional<Product> updateProduct(Long id, Product productDetails) {
        // Encontra o produto existente
        Optional<Product> optionalProduct = getProductById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // ATUALIZA APENAS O CAMPO 'NAME', pois 'price' não existe no modelo Product
            existingProduct.setName(productDetails.getName()); // Usa getName() e setName() que existem

            // A linha abaixo foi REMOVIDA porque getPrice() e setPrice() não existem em Product:
            // existingProduct.setPrice(productDetails.getPrice());

            return Optional.of(existingProduct); // Retorna o produto atualizado (apenas com nome potencialmente diferente)
        } else {
            return Optional.empty(); // Retorna vazio se não encontrou o produto
        }
    }

    // DELETE: Remove um produto por ID
    public boolean deleteProduct(Long id) {
        // Usa removeIf com getId() que existe
        return products.removeIf(product -> product.getId().equals(id));
    }

}