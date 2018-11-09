package com.dyl.blog.support.shiro.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: ShiroSessionListener
 * Author: DIYILIU
 * Update: 2018-09-20 09:42
 */

@Slf4j
@Component
public class ShiroSessionListener implements SessionListener {

    @Resource
    private Environment environment;


    @Override
    public void onStart(Session session) {

        //log.info("会话创建 ... ");
    }

    @Override
    public void onStop(Session session) {
        //log.info("会话退出 ... ");

        try {
            clearTemp(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExpiration(Session session) {
        //log.info("会话过期 ... ");

        try {
            clearTemp(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTemp(Session session) throws Exception {

    }
}
