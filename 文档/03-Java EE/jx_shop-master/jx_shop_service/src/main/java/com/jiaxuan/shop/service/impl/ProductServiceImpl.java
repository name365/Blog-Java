package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.utils.FtpUtils;
import com.jiaxuan.shop.common.utils.StringUtil;
import com.jiaxuan.shop.dao.ProductDao;
import com.jiaxuan.shop.params.ProductParam;
import com.jiaxuan.shop.dto.ProductDto;
import com.jiaxuan.shop.pojo.Product;
import com.jiaxuan.shop.pojo.ProductType;
import com.jiaxuan.shop.service.ProductService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-10 10:18
 * Description:  商品业务层接口实现
 */
@Service
@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private Integer port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.basePath}")
    private String basePath;

    @Value("${ftp.path}")
    private String path;

    @Value("${ftp.baseUrl}")
    private String baseUrl;

    /**
     *功能描述: 添加商品
     * @Author jx
     * @Date 20:24 2019/03/14
     * @Param [productDto]
     * @return int
     */
    @Override
    public int addProduct(ProductDto productDto) throws FileUploadException {
        //将文件上传ftp服务器上
        //按照时间创建文件夹
        String timePath = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String filePath = path + "/" + timePath;
        String fileName = StringUtil.reFileName(productDto.getFileName());
        boolean flag = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, fileName, productDto.getInputStream());
        System.out.println(filePath);
        System.out.println(fileName);
        if (!flag) {
            throw new FileUploadException("文件上传失败");
        }

        //2.保存到数据库,将 dto 转化为 pojo
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        //product.setImage(filePath);
        //地址修改为服务器上的http地址
        //product.setImage(baseUrl+"/" + filePath + "/" + fileName);
        product.setImage(baseUrl+"/" + timePath + "/" + fileName);

        ProductType productType = new ProductType();
        productType.setId(productDto.getProductTypeId());

        product.setProductType(productType);

        return productDao.insertProduct(product);
    }

    /**
     *功能描述: 检测商品名称是否可用
     * @Author jx
     * @Date 14:39 2019/03/10
     * @Param [name]
     * @return java.lang.Boolean true表示可用， false表示不可用
     */
    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Override
    public Boolean checkProductName(String name) {
        Product product = productDao.selectByProductName(name);
        if (product != null) {
            //商品名称已经存在
            return false;
        }
        return true;
    }

    /**
     *功能描述: 查找所有商品
     * @Author jx
     * @Date 20:25 2019/03/14
     * @Param []
     * @return java.util.List<Product>
     */
    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        return productDao.selectAllProducts();
    }

    /**
     *功能描述: 根据 id 查找商品
     * @Author jx
     * @Date 20:25 2019/03/14
     * @Param [id]
     * @return Product
     */
    @Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Override
    public Product findProductById(int id) {
        return productDao.selectProductById(id);
    }

    /**
     *功能描述: 修改商品
     * @Author jx
     * @Date 20:25 2019/03/14
     * @Param [productDto]
     * @return int
     */
    @Override
    public int modifyProduct(ProductDto productDto) throws FileUploadException {
        //1.文件上传
        //String fileName = StringUtil.reFileName(productDto.getFileName());
        //String filePath = productDto.getUploadPath()+"\\"+ fileName;
        //
        //try {
        //    StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        //} catch (IOException e) {
        //    throw new FileUploadException("文件上传失败"+e.getMessage());
        //}

        String timePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String filePath = path + "/" + timePath;
        String fileName = StringUtil.reFileName(productDto.getFileName());

        boolean flag = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, fileName, productDto.getInputStream());

        if (!flag) {
            throw new FileUploadException("文件上传失败");
        }

        //2.保存到数据库,将 dto 转化为 pojo
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        //product.setImage(filePath);
        //https://www.xmlvhy.com/images/20190301512474268.jpg
        product.setImage(baseUrl + "/" + timePath + "/" + fileName);

        ProductType productType = new ProductType();
        productType.setId(productDto.getProductTypeId());

        product.setProductType(productType);
        return productDao.updateProduct(product);
    }

    /**
     *功能描述: 删除商品
     * @Author jx
     * @Date 20:25 2019/03/14
     * @Param [id]
     * @return int
     */
    @Override
    public int removeProductById(int id) {
        return productDao.deleteProductById(id);
    }

    /**
     *功能描述: 获取图片，将图片响应到输出流中，显示图片
     * @Author jx
     * @Date 21:27 2019/03/10
     * @Param [path, outputStream]
     * @return void
     */
    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *功能描述: 多条件查找商品
     * @Author jx
     * @Date 20:26 2019/03/14
     * @Param [productParam]
     * @return java.util.List<Product>
     */
    @Override
    public List<Product> findByProductParams(ProductParam productParam) {
        return productDao.selectByProductParams(productParam);
    }
}
