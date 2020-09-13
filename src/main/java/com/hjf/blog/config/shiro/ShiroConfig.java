package com.hjf.blog.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * shiro配置类
 * @author hjf
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Autowired UserRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //关联UserRealm
        manager.setRealm(userRealm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //关联安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("/admin/login","anon"); //登录请求不拦截
        map.put("/admin/**","authc");

        filterFactoryBean.setFilterChainDefinitionMap(map);

        //设置拦截后的登录页面(自定义)
        filterFactoryBean.setLoginUrl("/admin");

        return filterFactoryBean;

    }
}
