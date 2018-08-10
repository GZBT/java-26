package com.kaisheng.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaisheng.it.dto.FixOrderPartsVo;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.PartsMapper;
import com.kaisheng.it.mapper.PartsStreamMapper;
import com.kaisheng.it.mapper.TypeMapper;
import com.kaisheng.it.service.PartsService;
import com.kaisheng.it.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
@Service
public class PartsServiceImpl implements PartsService {

    Logger logger = LoggerFactory.getLogger(PartsServiceImpl.class);

    @Autowired
    private PartsMapper partsMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private PartsStreamMapper partsStreamMapper;


    /**
     * 根据id查询对应的配件对象
     *
     * @param id
     * @return
     */
    @Override
    public Parts findById(Integer id) {
        return partsMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据页码获得对应的数据集合
     * @param pageNo 页码 1 2 3
     * @return pageInfo 对象
     */
    @Override
    public PageInfo<Parts> findPage(Integer pageNo) {

        // 分页
        PageHelper.startPage(pageNo,Constant.DEFAULT_PAGE_SIZE);

        List<Parts> partsList = partsMapper.findPageWithType();
        // 封装分页对象
        PageInfo<Parts> pageInfo =  new PageInfo<>(partsList);

        return pageInfo;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        Parts parts = findById(id);
        if(parts != null){
           partsMapper.deleteByPrimaryKey(id);
        }

        logger.debug("删除配件:{}",parts);
    }

    /**
     * 查询所有的配件类型列表
     * @return
     */
    @Override
    public List<Type> findTypeList() {
        TypeExample typeExample = new TypeExample();
        return typeMapper.selectByExample(typeExample);
    }

    /**
     * 更新
     * @param parts
     */
    @Override
    public void partsEdit(Parts parts) {
        partsMapper.updateByPrimaryKeySelective(parts);
        logger.debug("更新成功:{}",parts);
    }

    /**
     * 配件入库
     * @param parts
     */
    @Override
    public void saveParts(Parts parts) {
        // 判断入库是否大于0
        if(parts.getInventory() > 0){
            partsMapper.insertSelective(parts);
            logger.debug("新增入库配件: {}", parts);
        } else{
            throw new ServiceException("请正确填写入库数量");
        }

    }

    /**
     * 根据页码和筛选条件map集合查询对应的配件列表
     *
     * @param pageNo
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo<Parts> findPageByPageNoAndQueryMap(Integer pageNo, Map<String, Object> queryMap) {

        // 分页
        PageHelper.startPage(pageNo, Constant.DEFAULT_PAGE_SIZE);

        List<Parts> partsList = partsMapper.findPageByPageNoAndQueryMap(queryMap);

        PageInfo<Parts> pageInfo = new PageInfo<>(partsList);
        return pageInfo;
    }

    /**
     * 根据id查找配件列表
     * @param id
     * @return
     */
    @Override
    public List<Parts> findPartsByType(Integer id) {
        PartsExample partsExample = new PartsExample();
        partsExample.createCriteria().andTypeIdEqualTo(id);
        return partsMapper.selectByExample(partsExample);

    }

    /**
     * 根据订单号查询对应的配件列表
     * @param id
     * @return
     */
    @Override
    public List<Parts> findPartsByOrderId(Integer id) {

        List<Parts> partsList = partsMapper.findPartsByOrderId(id);
        return partsList;
    }

    /**
     * 减少库存
     *
     * @param json
     */
    @Override
    public void subInventory(String json) {

        FixOrderPartsVo fixOrderPartsVo = new Gson().fromJson(json,FixOrderPartsVo.class);
        for (FixOrderParts fixOrderParts : fixOrderPartsVo.getFixOrderPartsList()){

            // 更新库存
            Parts parts = partsMapper.selectByPrimaryKey(fixOrderParts.getPartsId());
            parts.setInventory(parts.getInventory() - fixOrderParts.getPartsNum());

            partsMapper.updateByPrimaryKeySelective(parts);

            // 生成出库流水
            PartsStream partsStream = new PartsStream();
            partsStream.setOrderId(fixOrderParts.getOrderId());
            partsStream.setPartsId(fixOrderParts.getPartsId());
            partsStream.setEmployeeId(fixOrderPartsVo.getEmployeeId());
            partsStream.setNum(fixOrderParts.getPartsNum());
            partsStream.setType(PartsStream.PARTS_STREAM_TYPE_OUT);

            partsStreamMapper.insertSelective(partsStream);
            logger.info("{} 配件出库", partsStream);

        }

    }
}
