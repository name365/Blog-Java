package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.constant.ShippingConstant;
import com.jiaxuan.shop.common.exception.ShippingException;
import com.jiaxuan.shop.dao.ShippingDao;
import com.jiaxuan.shop.service.ShippingService;
import com.jiaxuan.shop.pojo.Shipping;
import com.jiaxuan.shop.vo.ShippingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-23 10:49
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingDao shippingDao;

    /**
     *功能描述: 查找某个用户的某个收货地址
     * @Author jx
     * @Date 12:03 2019/03/23
     * @Param [customerId, shippingId]
     * @return Shipping
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Shipping findShippingByCustomerIdAndShippingId(Integer customerId, Integer shippingId) throws ShippingException {
        Shipping shipping = shippingDao.selectShippingByCustomerIdAndShippingId(customerId, shippingId);
        if (shipping == null) {
            throw new ShippingException("该地址不存在");
        }
        return shipping;
    }

    /**
     *功能描述: 查询用户的所有收货地址
     * @Author jx
     * @Date 12:06 2019/03/23
     * @Param [customerId]
     * @return java.util.List<Shipping>
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Shipping> findCustomerAllShippings(Integer customerId){
        List<Shipping> shippingList = shippingDao.selectAllShippings(customerId, ShippingConstant.SHIPPING_COMMON_STATUS);
        if (shippingList.size() == 0) {
           return null;
        }
        return shippingList;
    }

    /**
     *功能描述: 保存收货地址
     * @Author jx
     * @Date 11:51 2019/03/23
     * @Param [shippingVo, customerId]
     * @return int 地址id
     */
    @Override
    public int saveShipping(ShippingVo shippingVo, Integer customerId) throws ShippingException{
        Shipping shipping = new Shipping();

        BeanUtils.copyProperties(shippingVo,shipping);
        //设置客户id
        shipping.setCustomerId(customerId);
        //创建时间
        shipping.setCreateTime(new Date());
        //更新时间，开始默认和创建时间一致
        shipping.setUpdateTime(new Date());
        /*普通状态是0*/
        shipping.setStatus(ShippingConstant.SHIPPING_COMMON_STATUS);
        int rows = shippingDao.insertShipping(shipping);
        if(rows >= 1){
            return shipping.getId();
        }
        throw new ShippingException("收货地址添加失败");
    }

    /**
     *功能描述: 修改地址信息
     * @Author jx
     * @Date 10:04 2019/03/24
     * @Param [shippingVo, customerId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyShipping(ShippingVo shippingVo, Integer customerId) {
        Shipping shipping = shippingDao.selectShippingByCustomerIdAndShippingId(customerId, shippingVo.getId());
        if (shipping == null) {
            //TODO:这里可以设计抛出有一个异常
            throw new ShippingException("改地址信息不存在");
        }
        //属性拷贝
        BeanUtils.copyProperties(shippingVo,shipping);
        //更新更新时间
        shipping.setUpdateTime(new Date());
        int rows = shippingDao.updateByShipping(shipping);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 删除一个地址信息，这里实际上不删除，改变一下地址状态为status 0
     * @Author jx
     * @Date 21:26 2019/03/23
     * @Param [shippingId, customerId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean removeShipping(Integer shippingId, Integer customerId) {
        //删除时间也要更新一下
        Date updateTime = new Date();
        int rows = shippingDao.deleteShippingByIdAndCustomerId(shippingId, customerId, ShippingConstant.SHIPPING_ISVALID_STATUS,updateTime);
        if (rows >= 1) {
            return true;
        }
        return false;
    }
}
