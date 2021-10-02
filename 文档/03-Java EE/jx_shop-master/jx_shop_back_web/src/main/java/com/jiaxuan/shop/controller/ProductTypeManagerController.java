package com.jiaxuan.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxuan.shop.common.constant.PaginationConstant;
import com.jiaxuan.shop.common.exception.ProductTypeExistException;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.pojo.ProductType;
import com.jiaxuan.shop.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-09 9:55
 * Description:<描述>
 */
@Controller
@RequestMapping("/admin/product_type")
@Slf4j
public class ProductTypeManagerController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     *功能描述: 查询所有商品类型类表
     * @Author jx
     * @Date 21:33 2019/03/11
     * @Param [pageNum, model] 分页查询，默认第一页，每一页5条数据
     * @return java.lang.String
     */
    @GetMapping("find_all")
    public String findAllType(Integer pageNum,Model model) {

        if (ObjectUtils.isEmpty(pageNum)) {
            //设置默认值
            pageNum = PaginationConstant.PAGE_NUM;
        }

        //设置分页
        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
        //查询所有的数据
        List<ProductType> productTypeList = productTypeService.findAll();
        /*将查询的结果进行封装，封装到pageHelper中*/
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypeList);

        log.info("pageInfo= {}",pageInfo);

        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }

    /**
     *功能描述: 添加商品类型
     * @Author jx
     * @Date 21:33 2019/03/11
     * @Param [name]
     * @return ResponseResult
     */
    @PostMapping("add")
    @ResponseBody
    public ResponseResult addType(String name) {
        try {
            productTypeService.addProductType(name);
            return ResponseResult.success("添加成功");
        } catch (ProductTypeExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     *功能描述: 获取某一商品类型的信息
     * @Author jx
     * @Date 21:34 2019/03/11
     * @Param [id]
     * @return ResponseResult
     */
    @GetMapping("findProductTypeById")
    @ResponseBody
    public ResponseResult findProductTypeById(int id){
        ProductType productType = productTypeService.findProductTypeById(id);
        return ResponseResult.success(productType);
    }

    /**
     *功能描述: 修改商品类型名称
     * @Author jx
     * @Date 21:34 2019/03/11
     * @Param [id, name]
     * @return ResponseResult
     */
    @RequestMapping("modifyProductTypeName")
    @ResponseBody
    public ResponseResult modifyProductTypeName(int id , String name){

        try{
            int rows = productTypeService.modifyProductTypeName(id, name);
            if (rows >= 1) {
                return ResponseResult.success("修改商品类型成功");
            }else {
                return ResponseResult.fail("修改商品类型失败");
            }
        }catch (ProductTypeExistException e){
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     *功能描述: 删除某一商品类型
     * @Author jx
     * @Date 21:34 2019/03/11
     * @Param [id]
     * @return ResponseResult
     */
    @RequestMapping("removeProductType")
    @ResponseBody
    public ResponseResult removeProductType(int id){
        int rows = productTypeService.removeProductTypeById(id);
        if (rows >= 1) {
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.fail("删除失败");
        }
    }

    /**
     *功能描述: 修改商品类型状态，是否启用该类型商品
     * @Author jx
     * @Date 21:35 2019/03/11
     * @Param [id]
     * @return ResponseResult
     */
    @RequestMapping("modifyProductTypeStatus")
    @ResponseBody
    public ResponseResult modifyProductTypeStatus(int id){
        int rows = productTypeService.modifyProductTypeStatus(id);
        if (rows >= 1) {
            return ResponseResult.success("状态修改成功");
        }else {
            return ResponseResult.fail("状态修改失败");
        }
    }

}
