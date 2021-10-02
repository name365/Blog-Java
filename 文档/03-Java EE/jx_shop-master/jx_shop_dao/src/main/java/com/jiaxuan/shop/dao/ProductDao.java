package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.params.ProductParam;
import com.jiaxuan.shop.pojo.Product;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-10 10:39
 * Description: 商品数据操作层接口
 */
public interface ProductDao {

    int insertProduct(Product product);

    Product selectByProductName(String name);

    List<Product> selectAllProducts();

    Product selectProductById(int id);

    int updateProduct(Product product);

    int deleteProductById(int id);

    List<Product> selectByProductParams(ProductParam productParam);
}
