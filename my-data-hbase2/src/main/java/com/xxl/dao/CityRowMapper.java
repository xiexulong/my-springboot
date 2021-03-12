package com.xxl.dao;

import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import com.xxl.model.City;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class CityRowMapper implements RowMapper {

    private static byte[] COLUME_FAMILY = "f".getBytes();
    private static byte[] NAME = "name".getBytes();
    private static byte[] AGE = "age".getBytes();

    @Override
    public City mapRow(Result result, int i) throws Exception {
        String name = Bytes.toString(result.getValue(COLUME_FAMILY, NAME));
        int age = Bytes.toInt(result.getValue(COLUME_FAMILY, AGE));
        City city = new City();
        city.setAge(age);
        city.setCityName(name);
        return city;
    }
}
