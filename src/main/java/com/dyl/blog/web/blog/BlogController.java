package com.dyl.blog.web.blog;

import com.dyl.blog.web.blog.dto.Article;
import com.dyl.blog.web.blog.facade.ArticleJpa;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: BlogController
 * Author: DIYILIU
 * Update: 2018-11-14 15:28
 */

@RestController
@RequestMapping("/console/blog")
public class BlogController {

    @Resource
    private ArticleJpa articleJpa;

    @PostMapping("/save")
    public Integer save(Article article){
        article = articleJpa.save(article);
        if (article == null){

            return 0;
        }

        return 1;
    }

}
