package com.louis.springboot.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.louis.springboot.demo.model.UserLog;

public interface UserLogMapper {
    int deleteByPrimaryKey(@Param("tableName")String tableName, @Param("id")Long id);

    int insert(@Param("tableName")String tableName, @Param("userLog") UserLog userLog);

    int insertSelective(@Param("tableName")String tableName, @Param("userLog") UserLog userLog);

    UserLog selectByPrimaryKey(@Param("tableName")String tableName, @Param("id")Long id);

    int updateByPrimaryKeySelective(@Param("tableName")String tableName, @Param("userLog") UserLog userLog);

    int updateByPrimaryKey(@Param("tableName")String tableName, @Param("userLog") UserLog userLog);
    
    /**
     * 查找全部
     * @param tableName
     * @return
     */
    List<UserLog> selectAll(@Param("tableName")String tableName);
    
    /**
     * 是否存在表
     * @param tableName
     * @return
     */
    int existTable(@Param("tableName")String tableName);
    /**
     * 删除表
     * @param tableName
     * @return
     */
    int dropTable(@Param("tableName")String tableName);
    /**
     * 创建表
     * @param tableName
     * @return
     */
    int createTable(@Param("tableName")String tableName);
}