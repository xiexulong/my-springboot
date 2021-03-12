package com.xxl.service.impl;

import com.xxl.model.City;
import com.xxl.service.CityService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService {
    private Map<String, City> cityMap = new HashMap<>();

    public void saveCity(City city) {
        cityMap.put(city.getCityName(), city);
    }

    /**
     *  组件与redis 结合
     */

    @Cacheable(value = "baseCityInfo")
    public City getCityByName(String cityName) {
        return cityMap.get(cityName);
    }

    @CachePut(value = "baseCityInfo")
    public void updateCityDescription(String cityName, String description) {
        City city = cityMap.get(cityName);
        city.setDescription(description);
        cityMap.put(cityName,city);//模拟更新数据库
    }
}
