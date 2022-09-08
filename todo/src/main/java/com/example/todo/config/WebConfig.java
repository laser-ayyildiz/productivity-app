package com.example.todo.config;

import com.example.todo.model.User;
import com.example.todo.util.UserIdParserIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdParserIntercepter());
    }

    @Bean(name = "User")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public User user() {
        return new User();
    }

    @Bean
    public UserIdParserIntercepter userIdParserIntercepter() {
        return new UserIdParserIntercepter(user());
    }
}
