package com.github.controller;

import com.github.mapper.ArticleMapper;
import com.github.pojo.Article;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author subeiLY
 * @create 2022-01-29 20:26
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    // DI注入数据源
    @Autowired
    ArticleMapper articleMapper;

    // 查询全部内容
    @GetMapping("/queryArticles")
    public List<Article> queryArticles(){
        return articleMapper.queryArticles();
    }

    // 测试
    @GetMapping("/toEditor")
    public String toEditor(){
        return "editor";
    }

    // 添加文章
    @PostMapping("/addArticle")
    public String addArticle(Article article){
        articleMapper.addArticle(article);
        return "editor";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article",article);
        return "article";
    }

    // 博客图片上传问题
    @RequestMapping("/file/upload")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) throws IOException, JSONException {
        // 上传路径保存设置

        // 获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir")+"/upload/";

        // 按照月份进行分类：
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1)+"月";
        path = path+month;

        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }

        // 上传文件地址
        System.out.println("上传文件保存地址："+realPath);

        // 解决文件名字问题：我们使用uuid;
        String filename = "ks-"+ UUID.randomUUID().toString().replaceAll("-", "");
        // 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new File(realPath +"/"+ filename));

        // 给editormd进行回调
        JSONObject res = new JSONObject();
        res.put("url","/upload/"+month+"/"+ filename);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }

}
