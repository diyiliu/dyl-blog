package com.dyl.blog.web.blog.dto;

import com.dyl.blog.support.util.DateUtil;
import com.dyl.blog.web.sys.dto.SysUser;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OrderBy("sort asc")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articleId")
    private List<Tag> tagList;

    @Transient
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

    public String getTags() {
        if (StringUtils.isEmpty(tags) && CollectionUtils.isNotEmpty(tagList)){
            String str = "";
            for (Tag t: tagList){
                str += t.getName() + ",";
            }

            return str;
        }

        return tags;
    }
}
