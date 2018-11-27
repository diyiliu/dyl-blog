package com.dyl.blog.web.blog.facade;

import com.dyl.blog.web.blog.dto.Classify;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: ClassifyJpa
 * Author: DIYILIU
 * Update: 2018-11-14 15:36
 */
public interface ClassifyJpa extends JpaRepository<Classify, Long> {

    List<Classify> findByPidOrderBySort(Long pid);

    List<Classify> findByType(int type, Sort sort);
}
