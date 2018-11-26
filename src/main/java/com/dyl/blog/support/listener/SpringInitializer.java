package com.dyl.blog.support.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: SpringInitializer
 * Author: DIYILIU
 * Update: 2018-11-21 13:42
 */

@Component
public class SpringInitializer implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationReadyEvent){

        }
    }



}
