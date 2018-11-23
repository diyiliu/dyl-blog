package com.dyl.blog.support.listener;

import com.dyl.blog.support.util.cache.ICache;
import com.dyl.blog.web.blog.dto.Classify;
import com.dyl.blog.web.blog.facade.ClassifyJpa;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: SpringInitializer
 * Author: DIYILIU
 * Update: 2018-11-21 13:42
 */

@Component
public class SpringInitializer implements ApplicationListener {

    @Resource
    private ICache classifyProvider;

    @Resource
    private ClassifyJpa classifyJpa;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationReadyEvent){

            List<Classify> classifyList = classifyJpa.findAll(Sort.by(new String[]{"pid", "sort"}));
            // 根节点
            List<Classify> rootList = classifyList.stream().filter(a -> a.getPid() == 0).collect(Collectors.toList());
            // 子节点
            Map<Long, List<Classify>> listMap = classifyList.stream().filter(a -> a.getPid() > 0)
                    .collect(Collectors.groupingBy(Classify::getPid));
            for (Classify clz : rootList) {
                Long id = clz.getId();
                clz.setChildren(listMap.get(id));
            }

            classifyProvider.put("classifys", rootList);
        }
    }
}
