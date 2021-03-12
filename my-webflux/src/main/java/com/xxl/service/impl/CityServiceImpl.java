package com.xxl.service.impl;

import com.xxl.entity.City;
import com.xxl.repository.CityRepository;
import com.xxl.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Flux<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Mono<City> insertByCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Mono<City> update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return cityRepository.deleteById(id);
    }

    @Override
    public Mono<City> findById(Long id) {
        return cityRepository.findById(id);
    }
    @Override
    public Mono<City> getByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }
}
