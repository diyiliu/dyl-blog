package com.dyl.blog.web.blog.facade;

import com.dyl.blog.web.blog.dto.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: ArticleJpa
 * Author: DIYILIU
 * Update: 2018-11-14 15:36
 */
public interface ArticleJpa extends JpaRepository<Article, Long> {

    List<Article> findByStatus(int status);

    Page<Article> findByStatus(int status, Pageable pageable);

    Page<Article> findByStatusAndClassify_Id(int status, long id, Pageable pageable);

    List<Article> findByStatusAndClassify_Id(int status, long id);

    Page<Article> findByStatusAndResImgIsNotNull(int status, Pageable pageable);
}
