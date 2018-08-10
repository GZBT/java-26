package com.kaisheng.it.mapper;

import com.kaisheng.it.entity.FixOrder;
import com.kaisheng.it.entity.FixOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FixOrderMapper {
    long countByExample(FixOrderExample example);

    int deleteByExample(FixOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(FixOrder record);

    int insertSelective(FixOrder record);

    List<FixOrder> selectByExample(FixOrderExample example);

    FixOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") FixOrder record, @Param("example") FixOrderExample example);

    int updateByExample(@Param("record") FixOrder record, @Param("example") FixOrderExample example);

    int updateByPrimaryKeySelective(FixOrder record);

    int updateByPrimaryKey(FixOrder record);

    List<FixOrder> findListWithParts();

    FixOrder findWithPartsById(Integer id);
}