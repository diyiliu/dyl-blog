package com.dyl.blog.web.blog;

import com.dyl.blog.support.model.RespBody;
import com.dyl.blog.support.util.DateUtil;
import com.dyl.blog.web.blog.dto.Article;
import com.dyl.blog.web.blog.dto.Tag;
import com.dyl.blog.web.blog.facade.ArticleJpa;
import com.dyl.blog.web.blog.facade.TagJpa;
import com.dyl.blog.web.sys.dto.ResImg;
import com.dyl.blog.web.sys.dto.SysUser;
import com.dyl.blog.web.sys.facade.ResImgJpa;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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

    @Resource
    private TagJpa tagJpa;

    @Resource
    private ResImgJpa resImgJpa;

    @PostMapping("/save")
    public RespBody save(Article article, HttpSession session) throws Exception {

        Article temp;
        String tags = article.getTags();
        if (article.getId() == null) {
            SysUser user = (SysUser) session.getAttribute("user");

            article.setResImg(handleImg(article.getContent(), session));
            article.setUser(user);
            article.setSeeCount(0);
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            temp = articleJpa.save(article);
        } else {
            long id = article.getId();
            temp = articleJpa.findById(id).get();
            Long resImg = handleImg(article.getContent(), session);

            article.setUser(temp.getUser());
            article.setCreateTime(temp.getCreateTime());
            article.setUpdateTime(new Date());
            article.setSeeCount(temp.getSeeCount());
            article.setResImg(resImg == null ? temp.getResImg() : resImg);
            temp = articleJpa.save(article);
        }
        RespBody respBody = new RespBody();
        // 操作失败
        if (temp == null) {
            respBody.setStatus(0);
            return respBody;
        }

        long id = temp.getId();
        handleTags(id, tags);
        respBody.setStatus(1);
        respBody.setData(id);

        return respBody;
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

    @DeleteMapping("/article/{id}")
    public Integer deleteArticle(@PathVariable("id") long id) {
        articleJpa.deleteById(id);
        tagJpa.deleteByArticleId(id);

        return 1;
    }

    @PutMapping("/article/{item}")
    public Integer modify(@PathVariable String item, @RequestParam long id, @RequestParam int val) {
        Article article = articleJpa.findById(id).get();
        if (item.equals("top")){
            article.setIsTop(val);
        }else {
            article.setStatus(val);
        }
        article = articleJpa.save(article);
        if (article == null){

            return 0;
        }

        return 1;
    }

//    @PostMapping("/editor/{id}")
//    public void editor(@PathVariable("id") long id, HttpServletResponse response,
//                       RedirectAttributes redirectAttributes) throws IOException {
//
//        redirectAttributes.addFlashAttribute("article", id);
//        response.sendRedirect("/console/editor");
//    }

    private void handleTags(long id, String tags) {
        tagJpa.deleteByArticleId(id);
        if (StringUtils.isNotEmpty(tags)) {
            String[] tagArray = tags.split(",");
            List<Tag> tagList = new ArrayList();
            int i = 0;
            for (String tag : tagArray) {
                Tag t = new Tag();
                t.setArticleId(id);
                t.setName(tag);
                t.setSort(++i);
                tagList.add(t);
            }
            tagJpa.saveAll(tagList);
        }
    }

    private Long handleImg(String content, HttpSession session) throws Exception {
        List<ResImg> imgList = (List<ResImg>) session.getAttribute("temp_pic");

        Long imgView = null;
        if (CollectionUtils.isNotEmpty(imgList)) {
            for (Iterator<ResImg> iterator = imgList.iterator(); iterator.hasNext(); ) {
                ResImg img = iterator.next();
                String timeStr = DateUtil.dateToString(img.getCreateTime(), "%1$tY%1$tm%1$td %1$tH%1$tM%1$tS");
                String path = "/image/pic/" + timeStr + "/" + img.getId();

                if (!content.contains(path)) {
                    resImgJpa.deleteById(img.getId());

                    org.springframework.core.io.Resource res = new UrlResource("file:" + img.getPath());
                    if (res.exists()) {
                        res.getFile().delete();
                    }
                } else {
                    if (imgView == null) {

                        imgView = img.getId();
                    }
                }

                iterator.remove();
            }
        }

        return imgView;
    }
}
