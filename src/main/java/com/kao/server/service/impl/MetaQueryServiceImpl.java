package com.kao.server.service.impl;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.dto.RankBase;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import com.kao.server.mapper.MetaQueryMapper;
import com.kao.server.service.MetaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沈伟峰
 */
@Service
public class MetaQueryServiceImpl implements MetaQueryService {

    @Autowired
    private MetaQueryMapper metaQueryMapper;

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<CollegeIdAndName> queryCollegeIdAndName() {
        List<CollegeIdAndName> data = null;
        try {
            System.err.println("queryCollegeIdAndName");
            data = metaQueryMapper.queryCollegeIdAndName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<Discipline> queryDiscipline() {
        List<Discipline> data = null;
        try {
            System.err.println("queryDiscipline");
            data = metaQueryMapper.queryDiscipline();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<Major> queryMajor() {
        List<Major> data = null;
        try {
            System.err.println("queryMajor");
            data = metaQueryMapper.queryMajor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<Integer> queryRound() {
        List<Integer> data = null;
        try {
            System.err.println("queryRound");
            data = metaQueryMapper.queryRound();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<RankBase> queryRank() {
        try {
            System.err.println("queryRank");
            return metaQueryMapper.queryRank();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
