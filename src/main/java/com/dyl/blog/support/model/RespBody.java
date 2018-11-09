package com.dyl.blog.support.model;

import lombok.Data;

/**
 * Description: RespBody
 * Author: DIYILIU
 * Update: 2018-11-09 11:19
 */

@Data
public class RespBody<T> {

    /** 0: 失败; 1: 成功; **/
    private Integer success;

    private String message;

    private T data;
}
