package com.demo.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SimpleCORSFilter extends WebMvcConfigurationSupport{

    /***
     * SpringBoot跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("+++++++++++++++ addCorsMappings ++++++++++++++");
        // 所有请求，也可配置成特定请求，如/api/**
        registry.addMapping("/**")
                // 所有来源，也可以配置成特定的来源才允许跨域，如http://www.xxxx.com
                .allowedOrigins("*")
                .allowCredentials(true)
                // 所有方法，GET、POST、PUT等
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /***
     * springboot默认页面跳转配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("+++++++++++++++ addViewControllers ++++++++++++++");
        registry.addViewController("/").setViewName("/login");
        super.addViewControllers(registry);
    }

    /**这个方法是用来配置静态资源的，比如html，js，css，等等**/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler请求路径
        //addResourceLocations 在项目中的资源路径
        //setCacheControl 设置静态资源缓存时间
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
        System.out.println("+++++++++++++++ addResourceHandlers ++++++++++++++");
    }

    /**这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效**/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("+++++++++++++++ addInterceptors ++++++++++++++");
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(new InitInterceptor()).addPathPatterns("/**").excludePathPatterns("/**");
        super.addInterceptors(registry);
    }
}
