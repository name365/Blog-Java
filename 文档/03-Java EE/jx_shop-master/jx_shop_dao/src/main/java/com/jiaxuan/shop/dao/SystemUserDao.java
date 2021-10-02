package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.params.SystemUserParam;
import com.jiaxuan.shop.pojo.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-11 9:56
 * Description:<描述>
 */
public interface SystemUserDao {

    List<SystemUser> selectAllSytemUsers();

    SystemUser selectSystemUserById(int id);

    SystemUser selectSystemUserByLoginName(String loginName);

    int insertSystemUser(SystemUser systemUser);

    int updateSystemUser(SystemUser systemUser);

    int updateSystemUserStatus(@Param("id") int id,@Param("isValid") int isValid);

    List<SystemUser> selectSystemUserByParams(SystemUserParam systemUserParam);

    SystemUser selectSystemUserByLoginNameAndPassword(@Param("loginName") String loginName,
                                                      @Param("password") String password,
                                                      @Param("isValid") int isValid);
}
