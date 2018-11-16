package com.dyl.blog.web.blog.facade;

import com.dyl.blog.web.blog.dto.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description: ArticleJpa
 * Author: DIYILIU
 * Update: 2018-11-14 15:36
 */
public interface ArticleJpa extends JpaRepository<Article, Long> {



}
