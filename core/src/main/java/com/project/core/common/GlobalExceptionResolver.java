package com.project.core.common;

import com.project.core.json.JsonData;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author fanhaoming
 * @Date 2018/5/23  16:09
 * @Version 1.0
 **/
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        JsonData jsonData = JsonData.fail("系统错误");
        return new ModelAndView("jsonView",jsonData.toMap());
    }
}
