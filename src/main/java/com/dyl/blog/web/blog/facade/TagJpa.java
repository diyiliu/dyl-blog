package com.dyl.blog.web.blog.facade;

import com.dyl.blog.web.blog.dto.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description: TagJpa
 * Author: DIYILIU
 * Update: 2018-11-27 21:06
 */
public interface TagJpa extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT t.`name`,COUNT(1) count FROM blog_tag t GROUP BY t.`name` ORDER BY count DESC", nativeQuery = true)
    List findOrderByCount();

    List<Tag> findByName(String name);
}
