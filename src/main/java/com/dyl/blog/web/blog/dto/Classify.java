package com.dyl.blog.web.blog.dto;

import lombok.Data;

import javax.persistence.*;

/**
 * Description: Classify
 * Author: DIYILIU
 * Update: 2018-11-14 15:33
 */

@Data
@Entity
@Table(name = "blog_classify")
public class Classify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pid;

    private Long userId;

    private String name;

    private String note;

    private Integer sort;
}