package com.xxl.service;

import com.xxl.model.City;

import java.util.List;

public interface CityService {

    List<City> query(String startRow, String stopRow);
    public City query(String row);
    void saveOrUpdate();
}
