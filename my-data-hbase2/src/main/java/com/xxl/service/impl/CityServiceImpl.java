package com.xxl.service.impl;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.xxl.dao.CityRowMapper;
import com.xxl.model.City;
import com.xxl.service.CityService;
import org.apache.hadoop.hbase.client.MultiAction;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Override
    public List<City> query(String startRow, String stopRow) {
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        scan.setCaching(5000);
        return hbaseTemplate.find("people_table", scan, new CityRowMapper());
    }

    @Override
    public City query(String row) {
        City city = (City) hbaseTemplate.get("people_table", row, new CityRowMapper());
        return city;
    }

    @Override
    public void saveOrUpdate() {

        List<Mutation> saveOrUpdates = new ArrayList<>();
        Put put = new Put(Bytes.toBytes("158"));
        put.addColumn(Bytes.toBytes("people"), Bytes.toBytes("name"), Bytes.toBytes("test"));
        saveOrUpdates.add(put);

        this.hbaseTemplate.saveOrUpdates("people_table", saveOrUpdates);
    }
}
