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

    /**
     * 获取所有院校的编号和名称
     *
     * @return 院校列表
     */
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

    /**
     * 获取所有学科的编号和名称
     *
     * @return 学科列表
     */
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

    /**
     * 获取所有专业的编号、名称和所属学科的编号
     *
     * @return 专业列表
     */
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

    /**
     * 获取所有轮次
     *
     * @return 轮次列表
     */
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
