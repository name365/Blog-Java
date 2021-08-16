package com.github.dao.provider;

import com.github.dao.BaseDao;
import com.github.pojo.Provider;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.github.dao.provider
 * @ClassName: ProviderDaoImpl
 * @Author: subei
 * @CreateTime: 2021/8/15 8:53
 * @Description: 供应商管理接口实现类
 */
public class ProviderDaoImpl implements ProviderDao{
    // 增加供应商
    public int add(Connection connection, Provider provider) throws Exception {

        PreparedStatement pstm = null;
        int flag = 0;

        if(null != connection){
            String sql = "insert into smbms_provider (proCode,proName,proDesc," +
                    "proContact,proPhone,proAddress,proFax,createdBy,creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {provider.getProCode(),provider.getProName(),provider.getProDesc(),
                    provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                    provider.getProFax(),provider.getCreatedBy(),provider.getCreationDate()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResult(null, pstm, null);
        }

        return flag;
    }

    // 通过供应商名称、编码获取供应商列表-模糊查询-providerList
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws Exception {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Provider> providerList = new ArrayList<Provider>();

        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_provider where 1=1 ");
            List<Object> list = new ArrayList<Object>();

            if(!StringUtils.isNullOrEmpty(proName)){
                sql.append(" and proName like ?");
                list.add("%"+proName+"%");
            }

            if(!StringUtils.isNullOrEmpty(proCode)){
                sql.append(" and proCode like ?");
                list.add("%"+proCode+"%");
            }

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

            while(rs.next()){
                Provider _provider = new Provider();
                _provider.setId(rs.getInt("id"));
                _provider.setProCode(rs.getString("proCode"));
                _provider.setProName(rs.getString("proName"));
                _provider.setProDesc(rs.getString("proDesc"));
                _provider.setProContact(rs.getString("proContact"));
                _provider.setProPhone(rs.getString("proPhone"));
                _provider.setProAddress(rs.getString("proAddress"));
                _provider.setProFax(rs.getString("proFax"));
                _provider.setCreationDate(rs.getTimestamp("creationDate"));
                providerList.add(_provider);
            }
            BaseDao.closeResult(null, pstm, rs);
        }

        return providerList;
    }

    // 通过proId删除Provider
    public int deleteProviderById(Connection connection, String delId) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;

        if(null != connection){
            String sql = "delete from smbms_provider where id=?";
            Object[] params = {delId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResult(null, pstm, null);
        }

        return flag;
    }

    // 通过proId获取Provider
    public Provider getProviderById(Connection connection, String id) throws Exception {

        Provider provider = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if(null != connection){
            String sql = "select * from smbms_provider where id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getTimestamp("creationDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
                provider.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResult(null, pstm, rs);
        }

        return provider;
    }

    // 修改用户信息
    public int modify(Connection connection, Provider provider) throws Exception {

        int flag = 0;
        PreparedStatement pstm = null;

        if(null != connection){
            String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
                    "proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                    provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResult(null, pstm, null);
        }

        return flag;
    }
}
