package com.dyl.blog.web.blog.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    /** 0: 节点; 1: 类别;**/
    private Integer type;

    private Integer sort;

    @Transient
    private List<Classify> children;

    @Transient
    private Integer count;
}
