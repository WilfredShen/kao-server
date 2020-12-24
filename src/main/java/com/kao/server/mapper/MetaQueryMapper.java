package com.kao.server.mapper;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.dto.RankBase;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface MetaQueryMapper {

    /**
     * 获取所有院校的编号和名称
     *
     * @return 院校列表
     */
    List<CollegeIdAndName> queryCollegeIdAndName() throws Exception;

    /**
     * 获取所有学科的编号和名称
     *
     * @return 学科列表
     */
    List<Discipline> queryDiscipline() throws Exception;

    /**
     * 获取所有专业的编号、名称和所属学科的编号
     *
     * @return 专业列表
     */
    List<Major> queryMajor() throws Exception;

    /**
     * 获取所有轮次
     *
     * @return 轮次列表
     */
    List<Integer> queryRound() throws Exception;

    List<RankBase> queryRank() throws Exception;
}
