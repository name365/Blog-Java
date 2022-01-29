package com.github.mapper;

import com.github.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-01-29 20:15
 */
@Mapper
@Repository
public interface ArticleMapper {
    // 查询所有的文章
    List<Article> queryArticles();

    // 新增一个文章
    int addArticle(Article article);

    // 根据文章id查询文章
    Article getArticleById(int id);

    // 根据文章id删除文章
    int deleteArticleById(int id);

}
