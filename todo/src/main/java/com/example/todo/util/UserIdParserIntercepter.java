package com.example.todo.util;


import com.example.todo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class UserIdParserIntercepter
        implements AsyncHandlerInterceptor {

    private final User user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        user.setId(Long.valueOf(request.getHeader("userId")));
        return true;
    }

}
