package com.xxl.service.impl;

import com.xxl.model.City;
import com.xxl.repository.CityRepository;
import com.xxl.service.CityService;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityESServiceImpl implements CityService {
    /* 分页参数 */
    Integer PAGE_SIZE = 12;          // 每页数量
    Integer DEFAULT_PAGE_NUMBER = 0; // 默认当前页码

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float  MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
    private static final Logger logger = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    private CityRepository cityRepository;


    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {

        // 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        logger.info("\n searchCity: searchContent [" + searchContent + "] \n ");

        // 构建搜索查询
        SearchQuery searchQuery = getCitySearchQuery(pageNumber,pageSize,searchContent);

        logger.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<City> cityPage = cityRepository.search(searchQuery);
        return cityPage.getContent();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    @Override
    public List<City> findByDescription(String description, Pageable pageable) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description, Pageable pageable) {
        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description, Pageable pageable) {
        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

    /**
     * 根据搜索词构造搜索查询语句
     *
     * 代码流程：
     *      - 权重分查询
     *      - 短语匹配
     *      - 设置权重分最小值
     *      - 设置分页参数
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    private SearchQuery getCitySearchQuery(Integer pageNumber, Integer pageSize,String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10


        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(MIN_SCORE);

        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }
}
