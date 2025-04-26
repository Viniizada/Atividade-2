package com.exemplo.demo; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // <-- Anotação ESSENCIAL do Spring Boot
public class DemoApplication { // <-- Nome da classe principal

    // Método main - Ponto de entrada da aplicação Java
    public static void main(String[] args) {
        // Linha que efetivamente inicia a aplicação Spring Boot
        SpringApplication.run(DemoApplication.class, args);
    }

}