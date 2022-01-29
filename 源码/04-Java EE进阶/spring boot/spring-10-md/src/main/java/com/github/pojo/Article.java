package com.github.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  文章类
 * @author subeiLY
 * @create 2022-01-29 20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private int id; // 文章的唯一ID
    private String author; // 作者名
    private String title; // 标题
    private String content; // 文章的内容

}
