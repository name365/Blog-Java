package com.jiaxuan.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxuan.shop.vo.ProductVo;
import com.jiaxuan.shop.common.constant.PaginationConstant;
import com.jiaxuan.shop.common.utils.ResponseResult;
import com.jiaxuan.shop.dto.ProductDto;
import com.jiaxuan.shop.pojo.Product;
import com.jiaxuan.shop.pojo.ProductType;
import com.jiaxuan.shop.service.ProductService;
import com.jiaxuan.shop.service.ProductTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-10 8:43
 * Description: 商品管理接口
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    /**
     * 功能描述: 页面数据初始化，所有方法执行前执行，将所有启用的商品类型先拿到
     *
     * @return java.util.List<ProductType>
     * @Author jx
     * @Date 8:56 2019/03/10
     * @Param []
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findAllEnableProductTypes();
        return productTypes;
    }

    /**
     *功能描述: 获取所有商品列表
     * @Author jx
     * @Date 22:59 2019/03/10
     * @Param [pageNum, model]
     * @return java.lang.String
     */
    @RequestMapping("findAllProduct")
    public String findAllProduct(Integer pageNum , Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        //封装分页插件
        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);

        List<Product> productList = productService.findAllProducts();

        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        model.addAttribute("pageInfo",pageInfo);
        return "productManager";
    }

    /**
     *功能描述: 添加商品
     * @Author jx
     * @Date 23:00 2019/03/10
     * @Param [productVo, pageNum, session, model]
     * @return java.lang.String
     */
    @RequestMapping("addProduct")
    public String addProduct(ProductVo productVo, Integer pageNum, HttpSession session, Model model){
        //TODO:通过session拿到上传文件的实际路径，这里注释：原因修改为上传到ftp服务器上，
        //TODO: 图片路径修为可以通过 http访问的
        //String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");

        //TODO:CommonsMultipartFile 文件上传
        /*最好不要直接把web层的 CommonsMultipartFile 对象传给service,这样就会出现 service 层
        调用web层了，需要避免耦合，所以要封装 dto 类，专门用于数据传输封装*/

        try {
            //把Vo 转化为 dto传给 service
            ProductDto productDto = new ProductDto();

            //todo:使用 spring BeanUtils属性拷贝
            BeanUtils.copyProperties(productVo, productDto); //对象间属性的拷贝，可以将两个对象之间相同的属性拷贝
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());

            //productDto.setUploadPath(uploadPath);

            //将数据保存到数据库中
            int rows = productService.addProduct(productDto);
            if (rows >= 1) {
                model.addAttribute("successMsg", "添加成功");
            } else {
                model.addAttribute("failMsg", "添加失败");
            }
        } catch (Exception e) {
            //System.out.println("ProductController.addProduct"+ e.printStackTrace());
            e.printStackTrace();
                model.addAttribute("errorMsg", "文件上传失败");
        }
        //重新加载当前页面数据,接收前端传过来的 页面 pageNum
        return "forward:findAllProduct?pageNum="+pageNum;
    }

    /**
     *功能描述: 前端校验 商品名称是否存在接口
     * @Author jx
     * @Date 23:03 2019/03/10
     * @Param [name, model]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("checkProductName")
    @ResponseBody
    public Map<String , Object> checkProductName(String name , Model model) {
        Map<String,Object> map = new HashMap<>();
        if(productService.checkProductName(name)) {
            map.put("valid",true);
        }else{
            //TODO:返回这两个，bootstrapValidator 校验 插件 remote 校验会自己读取 valid 的值和message的
            map.put("valid",false);
            map.put("message","商品("+name+")已存在");
        }
        return map;
    }

    /**
     *功能描述: 通过 id 获取商品信息
     * @Author jx
     * @Date 23:04 2019/03/10
     * @Param [id]
     * @return ResponseResult
     */
    @RequestMapping("findProductById")
    @ResponseBody
    public ResponseResult findProductById(int id) {
        Product product = productService.findProductById(id);
        if (product != null) {
            return ResponseResult.success(product);
        }else{
            return ResponseResult.fail("该商品信息不存在");
        }
    }

    /**
     *功能描述: 获取图片，修改商品信息预览图片显示
     * @Author jx
     * @Date 23:05 2019/03/10
     * @Param [path, outputStream]
     * @return void
     */
    @RequestMapping("getImage")
    public void getImage(String path, OutputStream outputStream){
        //直接响应写入到输出流中
        productService.getImage(path, outputStream);
    }

    /**
     *功能描述: 修改商品信息，内容跟添加商品信息差不多
     * @Author jx
     * @Date 23:05 2019/03/10
     * @Param [productVo, pageNum, session, model]
     * @return java.lang.String
     */
    @RequestMapping("modifyProduct")
    public String modifyProduct(ProductVo productVo, Integer pageNum, HttpSession session, Model model)  {
        //TODO:通过session拿到上传文件的实际路径 : 注释： 修改上传路径为我的云服务器
        //String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        //TODO:CommonsMultipartFile 文件上传
        /*最好不要直接把web层的 CommonsMultipartFile 对象传给service,这样就会出现 service 层
        调用web层了，需要避免耦合，所以要封装 dto 类，专门用于数据传输封装*/

        try {
            //把Vo 转化为 dto传给 service
            ProductDto productDto = new ProductDto();

            //todo:使用 commons-beanutils工具类拷贝属性
            //PropertyUtils.copyProperties(productVo,productDto);

            //todo:使用 spring BeanUtils属性拷贝
            BeanUtils.copyProperties(productVo, productDto); //对象间属性的拷贝，可以将两个对象之间相同的属性拷贝
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            //productDto.setUploadPath(uploadPath);

            //更新数据
            int rows = productService.modifyProduct(productDto);
            if (rows >= 1) {
                model.addAttribute("successMsg", "修改成功");
            } else {
                model.addAttribute("failMsg", "修改失败");
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "文件上传失败");
        }
        //重新刷新页面加载数据
        return "forward:findAllProduct?pageNum="+pageNum;
    }

    /**
     *功能描述: 根据 id 删除商品的信息
     * @Author jx
     * @Date 23:08 2019/03/10
     * @Param [id]
     * @return ResponseResult
     */
    @RequestMapping("removeProductById")
    @ResponseBody
    public ResponseResult removeProductById(int id){
        int rows = productService.removeProductById(id);
        if (rows >= 1) {
            return ResponseResult.success("商品删除成功");
        }else {
            return ResponseResult.fail("商品删除失败");
        }
    }
}
