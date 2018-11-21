package com.dyl.blog.web;

import com.dyl.blog.support.model.PageData;
import com.dyl.blog.support.model.RespBody;
import com.dyl.blog.support.util.DateUtil;
import com.dyl.blog.support.util.cache.ICache;
import com.dyl.blog.web.blog.dto.Article;
import com.dyl.blog.web.blog.dto.Classify;
import com.dyl.blog.web.blog.facade.ArticleJpa;
import com.dyl.blog.web.blog.facade.ClassifyJpa;
import com.dyl.blog.web.sys.dto.ResImg;
import com.dyl.blog.web.sys.dto.SysUser;
import com.dyl.blog.web.sys.facade.ResImgJpa;
import org.apache.commons.io.FileUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: HomeController
 * Author: DIYILIU
 * Update: 2018-11-08 10:50
 */

@Controller
public class HomeController {

    @Resource
    private Environment environment;

    @Resource
    private ResImgJpa resImgJpa;

    @Resource
    private ClassifyJpa classifyJpa;

    @Resource
    private ArticleJpa articleJpa;

    @Resource
    private ICache classifyProvider;


    @ModelAttribute
    public void classifyAttribute(Model model){

        model.addAttribute("classifys", classifyProvider.get("classifys"));
    }

    @GetMapping("/")
    public String index(Model model){
        List articles = articleJpa.findAll();
        model.addAttribute("totalNumber", articles.size());
        model.addAttribute("active", 0);

        return "index";
    }

    @GetMapping("/classify/{id}")
    public String classify(@PathVariable long id, Model model){
        if (id == 0){

            return "redirect:/";
        }
        Classify classify = classifyJpa.findById(id).get();
        model.addAttribute(classify);

        List articles = articleJpa.findByClassify_Id(id);
        model.addAttribute("totalNumber", articles.size());
        model.addAttribute("active", classify.getId());

        return "classify";
    }

    @ResponseBody
    @GetMapping("/article/{id}")
    public Article article(@PathVariable long id){

        return articleJpa.findById(id).get();
    }


    @ResponseBody
    @PostMapping("/classify/{id}")
    public PageData classify(PageData pageData, @PathVariable long id){
        Pageable pageable = PageRequest.of(pageData.getPageNo() - 1, pageData.getPageSize(), Sort.by(Sort.Direction.DESC, "updateTime"));

        Page<Article> userPage;
        if (id == 0){
            userPage = articleJpa.findAll(pageable);
        }else {
            userPage = articleJpa.findByClassify_Id(id, pageable);
        }

        pageData.setTotal(userPage.getTotalPages());
        pageData.setData(userPage.getContent());

        return pageData;
    }


    @ResponseBody
    @PostMapping("/image/upload")
    public RespBody imgUpload(MultipartFile file, HttpSession session) throws Exception {
        RespBody respBody = new RespBody();

        String date = String.format("%1$tY%1$tm", new Date());
        String picDir = environment.getProperty("upload.pic") + date + "/";
        org.springframework.core.io.Resource resDir = new UrlResource(picDir);
        if (!resDir.getFile().exists()) {
            resDir.getFile().mkdir();
        }

        String fileName = file.getOriginalFilename();
        // 创建临时文件
        File tempFile = File.createTempFile("pic", fileName.substring(fileName.lastIndexOf(".")).toLowerCase(), resDir.getFile());
        FileCopyUtils.copy(file.getBytes(), tempFile);

        SysUser user = (SysUser) session.getAttribute("user");
        ResImg img = new ResImg();
        img.setPath(tempFile.getPath());
        img.setUserId(user.getId());
        img.setCreateTime(new Date());
        // 保存图片路径到数据库
        img = resImgJpa.save(img);

        if (img != null){
            List imgList = (List) session.getAttribute("temp_pic");
            if (imgList == null) {
                imgList = new ArrayList();
                session.setAttribute("temp_pic", imgList);
            }
            imgList.add(img);

            String timeStr = DateUtil.dateToString(img.getCreateTime(), "%1$tY%1$tm%1$td %1$tH%1$tM%1$tS");

            respBody.setStatus(1);
            respBody.setData("/image/pic/"  + timeStr + "/" + img.getId());
        }else {
            respBody.setStatus(0);
            respBody.setMessage("图片保存失败");
        }

        return respBody;
    }

    @ResponseBody
    @GetMapping("/image/pic/{time}/{id}")
    public ResponseEntity showPicture(@PathVariable long id, @PathVariable String time) throws Exception {
        ResImg img = resImgJpa.findById(id).get();
        if (img != null){
            org.springframework.core.io.Resource imgRes = new UrlResource("file:" + img.getPath());

            return ResponseEntity.ok(FileUtils.readFileToByteArray(imgRes.getFile()));
        }

        return null;
    }
}
