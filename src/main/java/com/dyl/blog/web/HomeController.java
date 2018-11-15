package com.dyl.blog.web;

import com.dyl.blog.support.model.RespBody;
import com.dyl.blog.web.blog.dto.Classify;
import com.dyl.blog.web.blog.facade.ClassifyJpa;
import com.dyl.blog.web.sys.dto.ResImg;
import com.dyl.blog.web.sys.dto.SysAsset;
import com.dyl.blog.web.sys.dto.SysUser;
import com.dyl.blog.web.sys.facade.ResImgJpa;
import org.apache.commons.io.FileUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/")
    public String index(Model model){

        List<Classify> classifyList = classifyJpa.findAll(Sort.by(new String[]{"pid", "sort"}));

        // 根节点
        List<Classify> rootList = classifyList.stream().filter(a -> a.getPid() == 0).collect(Collectors.toList());
        // 子节点
        Map<Long, List<Classify>> listMap = classifyList.stream().filter(a -> a.getPid() > 0)
                .collect(Collectors.groupingBy(Classify::getPid));
        for (Classify clz : rootList) {
            Long id = clz.getId();
            clz.setChildren(listMap.get(id));
        }
        model.addAttribute("active", 0);
        model.addAttribute("classifys", rootList);

        return "index";
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
            List<Long> pictures = (List<Long>) session.getAttribute("temp_pic");
            if (pictures == null) {
                pictures = new ArrayList();
                session.setAttribute("temp_pic", pictures);
            }
            pictures.add(img.getId());

            respBody.setStatus(1);
            respBody.setData("/image/pic/"  + img.getId());
        }else {
            respBody.setStatus(0);
            respBody.setMessage("图片保存失败");
        }

        return respBody;
    }

    @ResponseBody
    @GetMapping("/image/pic/{id}")
    public ResponseEntity showPicture(@PathVariable long id) throws Exception {
        ResImg img = resImgJpa.findById(id).get();
        if (img != null){
            org.springframework.core.io.Resource imgRes = new UrlResource("file:" + img.getPath());

            return ResponseEntity.ok(FileUtils.readFileToByteArray(imgRes.getFile()));
        }

        return null;
    }
}
