package com.kaisheng.it.service;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.FixOrder;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/9 0009
 */
public interface FixOrderService {
    /**
     *将队列中的数据解析生成维修订单
     * @param json
     */
    void createFixOrder(String json);

    /**
     * 查询待维修列表
     * @return
     */
    List<FixOrder> findFixOrderListWithParts();

    /**
     * 接收任务
     * @param id
     * @param employee
     */
    void tackReceive(Integer id, Employee employee);

    /**
     * 获取维修订单详情
     * @param id
     * @return
     */
    FixOrder findFixOrderById(Integer id);

    /**
     * 完成顶单维修
     * @param id
     */
    void tackDone(Integer id);

    /**
     * 查询待质检
     * @return
     */
    List<FixOrder> findFixOrderDetect();

    /**
     * 质检接收
     * @param id
     * @param employee
     */
    void receiveDetect(Integer id, Employee employee);

    /**
     * 完成质检
     * @param id
     */
    void findDetectOrderById(Integer id);

    /**
     * 添加超时数据
     * @param jobName
     */
    void addFixOrderTimeout(String jobName);
}
