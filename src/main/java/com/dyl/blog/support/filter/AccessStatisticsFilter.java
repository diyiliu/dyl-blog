package com.dyl.blog.support.filter;

import com.dyl.blog.support.util.WebUtils;
import com.dyl.blog.web.blog.dto.Article;
import com.dyl.blog.web.blog.facade.ArticleJpa;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: AccessStatisticsFilter
 * Author: DIYILIU
 * Update: 2018-11-26 20:58
 */

@Slf4j
@Aspect
@Component
public class AccessStatisticsFilter {

    @Resource
    private WebUtils webUtils;

    @Resource
    private ArticleJpa articleJpa;

    @After("execution(* com.dyl.blog.web.HomeController.article(..))")
    public void doAfter(JoinPoint joinPoint) {
        String ip = webUtils.getRemoteHost();
        log.info("[{}]阅读文章 ... ", ip);

        Object[] args = joinPoint.getArgs();
        long articleId = (long) args[0];

        Article article = articleJpa.findById(articleId).get();
        article.setSeeCount(article.getSeeCount() + 1);
        articleJpa.save(article);
    }
}
