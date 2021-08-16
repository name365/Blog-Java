package com.github.service.bill;

import com.github.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @Package: com.github.service.bill
 * @ClassName: BillService
 * @Author: subei
 * @CreateTime: 2021/8/15 10:18
 * @Description:
 */
public interface BillService {
    // 增加订单
    public boolean add(Bill bill);

    // 通过查询条件获取供应商列表-模糊查询-getBillList
    public List<Bill> getBillList(Bill bill);

    //通过delId删除Bill
    public boolean deleteBillById(String delId);

    // 通过billId获取Bill
    public Bill getBillById(String id);

    // 修改订单信息
    public boolean modify(Bill bill);

}
