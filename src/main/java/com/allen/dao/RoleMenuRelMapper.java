package com.allen.dao;

import com.allen.pojo.RoleMenuRel;
import com.allen.pojo.RoleMenuRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuRelMapper {
    int countByExample(RoleMenuRelExample example);

    int deleteByExample(RoleMenuRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenuRel record);

    int insertSelective(RoleMenuRel record);

    List<RoleMenuRel> selectByExample(RoleMenuRelExample example);

    RoleMenuRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMenuRel record, @Param("example") RoleMenuRelExample example);

    int updateByExample(@Param("record") RoleMenuRel record, @Param("example") RoleMenuRelExample example);

    int updateByPrimaryKeySelective(RoleMenuRel record);

    int updateByPrimaryKey(RoleMenuRel record);
}