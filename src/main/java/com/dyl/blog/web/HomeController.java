package com.dyl.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description: HomeController
 * Author: DIYILIU
 * Update: 2018-11-08 10:50
 */

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(){


        return "console/login";
    }
}
