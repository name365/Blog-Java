package com.github.dao.bill;

import com.github.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @Package: com.github.dao.bill
 * @ClassName: BillDao
 * @Author: subei
 * @CreateTime: 2021/8/15 10:01
 * @Description:
 */
public interface BillDao {
    // 增加订单
    public int add(Connection connection, Bill bill)throws Exception;

    // 通过查询条件获取供应商列表-模糊查询-getBillList
    public List<Bill> getBillList(Connection connection, Bill bill)throws Exception;

    //通过delId删除Bill
    public int deleteBillById(Connection connection, String delId)throws Exception;

    // 通过billId获取Bill
    public Bill getBillById(Connection connection, String id)throws Exception;

    // 修改订单信息
    public int modify(Connection connection, Bill bill)throws Exception;

    // 根据供应商ID查询订单数量
    public int getBillCountByProviderId(Connection connection, String providerId)throws Exception;

}
