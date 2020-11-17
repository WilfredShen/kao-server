package com.kao.server.mapper;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 沈伟峰
 */
@Mapper
@Repository
public interface MetaQueryMapper {
    List<CollegeIdAndName> queryCollegeIdAndName() throws Exception;

    List<Discipline> queryDiscipline() throws Exception;

    List<Major> queryMajor() throws Exception;

    List<Integer> queryRound() throws Exception;
}
