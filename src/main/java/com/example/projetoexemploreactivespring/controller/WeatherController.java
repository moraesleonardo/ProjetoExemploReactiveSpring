package com.example.projetoexemploreactivespring.controller;

import com.example.projetoexemploreactivespring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Mono<String> getWeather(@RequestParam String city) {
        // A chave de API é usada internamente pelo serviço, sem necessidade de passar na requisição
        return weatherService.getWeatherByCity(city);
    }
}
