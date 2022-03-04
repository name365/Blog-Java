package com.github.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mapper.ProductMapper;
import com.github.pojo.Product;
import org.springframework.stereotype.Service;

/**
 * @author subeiLY
 * @create 2022-03-04 21:48
 */
@DS("slave_1")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
