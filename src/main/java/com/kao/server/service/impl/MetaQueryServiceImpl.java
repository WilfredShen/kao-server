package com.kao.server.service.impl;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import com.kao.server.mapper.MetaQueryMapper;
import com.kao.server.service.MetaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CollegeIdAndName> queryCollegeIdAndName() {
        List<CollegeIdAndName> data = null;
        try {
            data = metaQueryMapper.queryCollegeIdAndName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Discipline> queryDiscipline() {
        List<Discipline> data = null;
        try {
            data = metaQueryMapper.queryDiscipline();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Major> queryMajor() {
        List<Major> data = null;
        try {
            data = metaQueryMapper.queryMajor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Integer> queryRound() {
        List<Integer> data = null;
        try {
            data = metaQueryMapper.queryRound();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}