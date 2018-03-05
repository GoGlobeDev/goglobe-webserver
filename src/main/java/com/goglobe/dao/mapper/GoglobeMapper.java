package com.goglobe.dao.mapper;

import com.goglobe.dao.model.Goglobe;
public interface GoglobeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goglobe record);

    int insertSelective(Goglobe record);

    Goglobe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goglobe record);

    int updateByPrimaryKey(Goglobe record);
    
    Goglobe selectByAccount(String account);
    
    int updateGoglobeByAccount(Goglobe record);
    
    int addMoneyByInvitation(Goglobe record);
    
    Integer selectByCode(String code);
    
    Integer selectByKey(String key);
    
}