package com.dyl.blog.web;

import com.dyl.blog.web.sys.dto.SysAsset;
import com.dyl.blog.web.sys.facade.SysAssetJpa;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description: LoginController
 * Author: DIYILIU
 * Update: 2018-11-09 11:21
 */

@Controller
@RequestMapping("/console")
public class LoginController {


    @Resource
    private SysAssetJpa sysAssetJpa;


    @GetMapping
    public String index(HttpSession session) {
        SysAsset asset = sysAssetJpa.findByCode("index");
        session.setAttribute("active", asset);

        return asset.getView();
    }

    @GetMapping("/login")
    public String login() {

        return "console/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exceptionClassName)
                || IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {

            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/console/login";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {

            redirectAttributes.addFlashAttribute("error", "登录错误次数超限，请稍后再试！");
            return "redirect:/console/login";

        } else if (exceptionClassName != null) {
            redirectAttributes.addFlashAttribute("error", "登录异常：" + exceptionClassName);

            return "redirect:/console/login";
        }
        SysAsset asset = (SysAsset) request.getSession().getAttribute("active");

        return asset.getView();
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();

        return "redirect:/console/login";
    }


    @GetMapping("/{menu}")
    public String display(@PathVariable("menu") String menu, HttpServletRequest request) {

        SysAsset asset = sysAssetJpa.findByController("console/" + menu);
        if (asset == null) {
            return "error/404";
        }

        String view = asset.getView();
        // 设置当前页
        request.getSession().setAttribute("active", asset);

        return view;
    }
}
