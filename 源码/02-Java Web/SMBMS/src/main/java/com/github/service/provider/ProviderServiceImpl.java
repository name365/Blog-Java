package com.github.service.provider;

import com.github.dao.BaseDao;
import com.github.dao.bill.BillDao;
import com.github.dao.bill.BillDaoImpl;
import com.github.dao.provider.ProviderDao;
import com.github.dao.provider.ProviderDaoImpl;
import com.github.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Package: com.github.service.provider
 * @ClassName: ProvderServiceImpl
 * @Author: subei
 * @CreateTime: 2021/8/15 9:55
 * @Description:
 */
public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;
    private BillDao billDao;

    public ProviderServiceImpl(){
        providerDao = new ProviderDaoImpl();
        billDao = new BillDaoImpl();
    }

    // 增加供应商
    @Override
    public boolean add(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            // 开启JDBC事务管理
            connection.setAutoCommit(false);
            if(providerDao.add(connection,provider) > 0) {
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            // 在service层进行connection连接的关闭
            BaseDao.closeResult(connection, null, null);
        }

        return flag;
    }

    // 通过供应商名称、编码获取供应商列表-模糊查询-providerList
    @Override
    public List<Provider> getProviderList(String proName, String proCode) {
        Connection connection = null;
        List<Provider> providerList = null;

        System.out.println("query proName ---- > " + proName);
        System.out.println("query proCode ---- > " + proCode);

        try {
            connection = BaseDao.getConnection();
            providerList = providerDao.getProviderList(connection, proName,proCode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }

        return providerList;
    }

    // 通过proId删除Provider
    @Override
    public int deleteProviderById(String delId) {
        Connection connection = null;
        int billCount = -1;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            billCount = billDao.getBillCountByProviderId(connection,delId);
            if(billCount == 0){
                providerDao.deleteProviderById(connection, delId);
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            billCount = -1;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            BaseDao.closeResult(connection, null, null);
        }

        return billCount;
    }

    // 通过proId获取Provider
    @Override
    public Provider getProviderById(String id) {
        Provider provider = null;
        Connection connection = null;

        try{
            connection = BaseDao.getConnection();
            provider = providerDao.getProviderById(connection, id);
        }catch (Exception e) {
            e.printStackTrace();
            provider = null;
        }finally{
            BaseDao.closeResult(connection, null, null);
        }

        return provider;
    }

    // 修改用户信息
    @Override
    public boolean modify(Provider provider) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if(providerDao.modify(connection,provider) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }

        return flag;
    }

}
