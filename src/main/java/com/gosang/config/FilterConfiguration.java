package com.gosang.config;

import com.gosang.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/23 17:20
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/orders/*");
        filterRegistrationBean.addUrlPatterns("/orderDetail/*");
        return filterRegistrationBean;
    }
}
