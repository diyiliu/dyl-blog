package com.dyl.blog.web.blog.dto;

import lombok.Data;

import javax.persistence.*;

/**
 * Description: Tag
 * Author: DIYILIU
 * Update: 2018-11-27 16:40
 */

@Data
@Entity
@Table(name = "blog_tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private String name;

    private Integer sort;
}
