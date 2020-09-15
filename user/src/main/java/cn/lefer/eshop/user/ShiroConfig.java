package cn.lefer.eshop.user;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private UserService userService;

    /*负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁*/
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "eshopRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public EshopRealm realm() {
        EshopRealm eshopRealm = new EshopRealm();
        eshopRealm.setUserService(userService);
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        eshopRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return eshopRealm;
    }

    @Bean
    public SessionsSecurityManager securityManager(EshopRealm eshopRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(eshopRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/anon", "anon");//匿名访问
        filterMap.put("/customer", "roles[customer]");//限定角色访问
        filterMap.put("/**", "authc");//登录后访问
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
