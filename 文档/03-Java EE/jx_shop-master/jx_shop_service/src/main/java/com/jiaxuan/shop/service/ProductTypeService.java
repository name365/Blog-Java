package com.jiaxuan.shop.service;

import com.jiaxuan.shop.common.exception.ProductTypeExistException;
import com.jiaxuan.shop.pojo.ProductType;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-09 11:34
 * Description: 商品类型信息业务层接口
 */
public interface ProductTypeService {

    /**
     *功能描述: 查询所有商品类型信息
     * @Author jx
     * @Date 11:35 2019/03/09
     * @Param []
     * @return java.util.List<ProductType>
     */
    List<ProductType> findAll();

    List<ProductType> findAllValidStatus();

    /**
     *功能描述: 添加商品类型
     * @Author jx
     * @Date 21:47 2019/03/09
     * @Param [name]
     * @return void
     */
    void addProductType(String name) throws ProductTypeExistException;

    /**
     *功能描述: 根据 id 查询商品类型信息
     * @Author jx
     * @Date 21:47 2019/03/09
     * @Param [id]
     * @return ProductType
     */
    ProductType findProductTypeById(Integer id);

    /**
     *功能描述: 修改商品类型的名称
     * @Author jx
     * @Date 22:13 2019/03/09
     * @Param [id, name]
     * @return int
     */
    int modifyProductTypeName(int id , String name);

    /**
     *功能描述: 修改商品类型的状态
     * @Author jx
     * @Date 22:13 2019/03/09
     * @Param [id, status]
     * @return int
     */
    int modifyProductTypeStatus(int id);

    /**
     *功能描述: 根据id删除商品类型信息
     * @Author jx
     * @Date 22:14 2019/03/09
     * @Param [id]
     * @return int
     */
    int removeProductTypeById(int id);

    List<ProductType> findAllEnableProductTypes();
}
