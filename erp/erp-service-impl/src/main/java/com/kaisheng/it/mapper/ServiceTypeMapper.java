package com.kaisheng.it.mapper;

import com.kaisheng.it.entity.ServiceType;
import com.kaisheng.it.entity.ServiceTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceTypeMapper {
    long countByExample(ServiceTypeExample example);

    int deleteByExample(ServiceTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceType record);

    int insertSelective(ServiceType record);

    List<ServiceType> selectByExample(ServiceTypeExample example);

    ServiceType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceType record, @Param("example") ServiceTypeExample example);

    int updateByExample(@Param("record") ServiceType record, @Param("example") ServiceTypeExample example);

    int updateByPrimaryKeySelective(ServiceType record);

    int updateByPrimaryKey(ServiceType record);
}