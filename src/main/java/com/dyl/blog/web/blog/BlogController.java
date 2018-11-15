package com.dyl.blog.web.blog;

import com.dyl.blog.web.blog.dto.Article;
import com.dyl.blog.web.blog.facade.ArticleJpa;
import com.dyl.blog.web.sys.dto.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public Integer save(Article article, HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");

        article.setUser(user);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article = articleJpa.save(article);
        if (article == null){

            return 0;
        }

        return 1;
    }

    @PostMapping("/articles")
    public Map articleList(@RequestParam int pageNo, @RequestParam int pageSize,
                        @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));
        Page<Article> userPage = articleJpa.findAll(pageable);

        Map respMap = new HashMap();
        respMap.put("data", userPage.getContent());
        respMap.put("total", userPage.getTotalElements());

        return respMap;
    }

}
