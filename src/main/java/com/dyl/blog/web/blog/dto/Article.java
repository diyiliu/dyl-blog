package com.dyl.blog.web.blog.dto;

import com.dyl.blog.support.util.DateUtil;
import com.dyl.blog.web.sys.dto.SysUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Description: Article
 * Author: DIYILIU
 * Update: 2018-11-14 15:29
 */

@Data
@Entity
@Table(name = "blog_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SysUser user;

    private String title;

    private String content;

    private String overview;

    private Long resImg;

    private Integer type;

    @ManyToOne
    @JoinColumn(name = "classify", referencedColumnName = "id")
    private Classify classify;

    private String tags;

    private Integer open;

    private Date createTime;

    private Date updateTime;

    private Integer isTop;

    private Integer seeCount;

    private Integer status;

    public String getCreateTimeStr() {

        return DateUtil.dateToString(createTime, "%1$tY-%1$tm-%1$td");
    }
}
