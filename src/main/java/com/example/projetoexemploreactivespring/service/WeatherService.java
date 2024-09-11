package com.example.projetoexemploreactivespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final String apiKey;

    // Injetando a chave da API do arquivo de configuração application.properties
    @Autowired
    public WeatherService(WebClient.Builder webClientBuilder, @Value("${weather.api.key}") String apiKey) {
        this.webClient = webClientBuilder
                .baseUrl("http://api.openweathermap.org/data/2.5")
                .build();
        this.apiKey = apiKey;
    }

    public Mono<String> getWeatherByCity(String cityName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", cityName)
                        .queryParam("appid", apiKey) // Usando a chave da API injetada
                        .queryParam("units", "metric") // Para temperaturas em Celsius
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> {
                    System.err.println("Erro ao buscar informações de clima: " + e.getMessage());
                });
    }
}
