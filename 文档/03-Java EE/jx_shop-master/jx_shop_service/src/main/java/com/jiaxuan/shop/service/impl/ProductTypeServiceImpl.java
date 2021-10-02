package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.constant.ProductTypeConstant;
import com.jiaxuan.shop.common.exception.ProductTypeExistException;
import com.jiaxuan.shop.dao.ProductTypeDao;
import com.jiaxuan.shop.service.ProductTypeService;
import com.jiaxuan.shop.pojo.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-09 11:36
 * Description:商品类型业务实现类
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<ProductType> findAll() {
        return productTypeDao.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<ProductType> findAllValidStatus() {
        return productTypeDao.selectAllValidByStatus(ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }

    @Override
    public void addProductType(String name) throws ProductTypeExistException {
        ProductType productType = productTypeDao.selectProductTypeByName(name);
        if (productType != null) {
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeDao.insertProductType(name, ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public ProductType findProductTypeById(Integer id) {
        return productTypeDao.selectProductTypeById(id);
    }

    @Override
    public int modifyProductTypeName(int id, String name) throws ProductTypeExistException{
        //判断该商品类型的名称是否存在
        ProductType productType = productTypeDao.selectProductTypeByName(name);
        if (productType != null) {
            throw new ProductTypeExistException("商品类型名称已存在");
        }
        return productTypeDao.updateName(id,name);
    }

    @Override
    public int modifyProductTypeStatus(int id) {
        ProductType productType = productTypeDao.selectProductTypeById(id);
        int status = productType.getStatus();
        if (status == ProductTypeConstant.PRODUCT_TYPE_ENABLE){
            status = ProductTypeConstant.PRODUCT_TYPE_DISENABLE;
        }else{
            status = ProductTypeConstant.PRODUCT_TYPE_ENABLE;
        }
        return productTypeDao.updateStatus(id,status);
    }

    @Override
    public int removeProductTypeById(int id) {
        return productTypeDao.deleteProductTypeById(id);
    }

    @Override
    public List<ProductType> findAllEnableProductTypes() {
        return productTypeDao.findAllEnableProductTypes(ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }
}
