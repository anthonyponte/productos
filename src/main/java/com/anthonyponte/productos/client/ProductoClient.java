package com.anthonyponte.productos.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.anthonyponte.productos.model.Producto;

@Service
public class ProductoClient {
    private final WebClient client;

    public ProductoClient(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder.baseUrl("http://localhost:9095/api/productos").build();
    }

    public List<Producto> readAll() {
        return client.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(Producto.class)
                .collectList()
                .block();
    }

    public Producto readById(Long id) {
        return client.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class)
                .block();
    }
}
