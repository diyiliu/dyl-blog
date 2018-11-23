package com.dyl.blog.support.model;

import lombok.Data;

import java.util.List;

/**
 * Description: PageData
 * Author: DIYILIU
 * Update: 2018-11-16 14:00
 */

@Data
public class PageData {

    private int pageNo;

    private int pageSize;

    private int total;

    private List data;
}
