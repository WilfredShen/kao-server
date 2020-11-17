package com.kao.server.service;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface MetaQueryService {
    List<CollegeIdAndName> queryCollegeIdAndName();

    List<Discipline> queryDiscipline();

    List<Major> queryMajor();

    List<Integer> queryRound();
}
