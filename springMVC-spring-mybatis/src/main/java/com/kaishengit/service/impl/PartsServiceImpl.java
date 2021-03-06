package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Parts;
import com.kaishengit.entity.Type;
import com.kaishengit.entity.TypeExample;
import com.kaishengit.mapper.PartsMapper;
import com.kaishengit.mapper.TypeMapper;
import com.kaishengit.service.PartsService;
import com.kaishengit.uril.Constant;
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
        partsMapper.insertSelective(parts);
        logger.debug("新增入库配件: {}", parts);
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
}
