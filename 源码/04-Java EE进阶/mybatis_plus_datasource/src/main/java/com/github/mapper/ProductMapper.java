package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pojo.Product;
import org.springframework.stereotype.Repository;

/**
 * @author subeiLY
 * @create 2022-03-04 21:44
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
