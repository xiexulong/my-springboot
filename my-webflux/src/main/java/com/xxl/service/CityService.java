package com.xxl.service;

import com.xxl.entity.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {
    Flux<City> findAll();

    Mono<City> insertByCity(City city);

    Mono<City> update(City city);

    Mono<Void> delete(Long id);

    Mono<City> findById(Long id);

    Mono<City> getByCityName(String cityName);
}
