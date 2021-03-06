package com.kaisheng.it.mapper;

import com.kaisheng.it.entity.Order;
import com.kaisheng.it.entity.OrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findUndonePageByParam(Map<String, Object> queryMap);

    Order findOrderWithCarByCustomerById(Integer id);

    List<Order> findDailyOrderBySate(@Param("state") String state, @Param("dateTime") String dateTime);
}