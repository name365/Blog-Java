package com.jiaxuan.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Author: jx
 * Date: 2019-03-10 9:31
 * Description: 商品Vo实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
    /*商品id*/
    private Integer id;
    /*商品名称*/
    private String name;
    /*商品价格*/
    private Double price;
    /*上传的图片*/
    private CommonsMultipartFile file;
    /*商品类型的id*/
    private Integer productTypeId;
    /*商品的描述*/
    private String info;
}
