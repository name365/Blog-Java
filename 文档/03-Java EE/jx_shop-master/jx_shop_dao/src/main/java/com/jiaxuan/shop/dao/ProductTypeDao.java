package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-09 10:56
 * Description:商品类型dao层接口
 */
public interface ProductTypeDao {

    /**
     *功能描述: 查询所有商品类型
     */
    List<ProductType> selectAll();

    /**
     *功能描述: 根据id查询商品类型
     */
    ProductType selectProductTypeById(Integer id);

    /**
     *功能描述: 根据名称查询商品类型
     */
    ProductType selectProductTypeByName(String name);

    /**
     *功能描述: 插入商品类型
     */
    int insertProductType(@Param("name") String name,@Param("status") int status);

    /**
     *功能描述: 根据id更新商品类型名称
     */
    int updateName(@Param("id") int id, @Param("name") String name);

    /**
     *功能描述: 根据id更新商品类型转态
     */
    int updateStatus(@Param("id") int id,@Param("status") int status);

    /**
     *功能描述: 查询所有商品类型
     */
    int deleteProductTypeById(int id);

    /**
     *功能描述: 查找所有启用的（有效的）商品类型
     */
    List<ProductType> findAllEnableProductTypes(int status);

    List<ProductType> selectAllValidByStatus(int status);
}
