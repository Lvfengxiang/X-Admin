package com.login.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.login.bean.User;
import com.login.common.cache.RedisUtil;
import com.login.common.helper.$;
import com.login.common.helper.ApiCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoginInterceptorBySpring extends HandlerInterceptorAdapter {
    @Autowired
    RedisUtil redisUtil;
    // 在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从header中得到token
        String token = request.getHeader("token");
        //判断获取的值时候为空
        if (token == null) {
            renderJson(JSONObject.toJSONString($.error(ApiCode.auth,"不允许的访问,未获取token")));
            return false;
        }
        String  value=null;
        value=redisUtil.get("user:"+token);
        if (value == null) {
            renderJson(JSONObject.toJSONString($.error(ApiCode.auth,"不允许的访问,Token已过期")));
            return false;
        }
        //从redis获取token，转换成 User 对象 ，后台可以拿 User这个对象获取当前用户id
        User user = JSONObject.parseObject(value,User.class);
        //判断是否可以转成对象，对象为空则登陆失效
        if (user == null) {
            renderJson(JSONObject.toJSONString($.error(ApiCode.auth, ApiCode.auth_msg)));
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }


    // 在业务处理器处理请求完成之后，生成视图之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    /**
     * 获取当前请求
     *
     * @return response
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private void render(String text, String contentType) {
        HttpServletResponse response;
        try {

            response = getResponse();
            response.setContentType(contentType);

            response.getWriter().write(text);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected void renderJson(String text) {
        render(text, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
