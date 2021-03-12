package com.xxl.repository;


import com.xxl.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CityRepository extends ElasticsearchRepository<City, Long> {

    List<City> findByDescriptionAndScore(String describe, Integer score);
    List<City> findByDescriptionOrScore(String describe, Integer score);

    /**
     * 等同于
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"description\" : \"?0\"}}}}")
     * Page<City> findByDescription(String description, Pageable pageable);
     * @param describe
     * @param pageable
     * @return
     */
    Page<City> findByDescription(String describe, Pageable pageable);


    /**
     * LIKE 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<City> findByDescriptionLike(String description, Pageable page);

    Page<City> findByDescriptionNot(String description, Pageable page);
}
