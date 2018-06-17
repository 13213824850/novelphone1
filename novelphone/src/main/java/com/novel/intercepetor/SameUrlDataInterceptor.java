package com.novel.intercepetor;

import com.alibaba.fastjson.JSON;
import com.novel.annotation.SameUrlData;
import com.novel.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SameUrlDataInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private RedisClient redisClient;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                if(repeatDataValidator(request))//如果重复相同数据
                    return false;
                else
                    return true;
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }


    //判断和上次的url是否相同

    public boolean repeatDataValidator(HttpServletRequest httpServletRequest)
    {
        String params= JSON.toJSONString(httpServletRequest.getParameterMap());
        String url=httpServletRequest.getRequestURI();
        Map<String,String> map=new HashMap<String,String>();
        map.put(url, params);
        String nowUrlParams=map.toString();//

        Object preUrlParams=redisClient.get("repeatData");
        if(preUrlParams==null)//如果上一个数据为null,表示还没有访问页面
        {
            redisClient.set("repeatData",nowUrlParams);
            //过期时间3秒 防止表单重复提交
            redisClient.expire("repeatData",2);
            return false;
        }
        else//否则，已经访问过页面
        {
            if(preUrlParams.toString().equals(nowUrlParams))//如果上次url+数据和本次url+数据相同，则表示城府添加数据
            {

                return true;
            }
            else//如果上次 url+数据 和本次url加数据不同，则不是重复提交
            {
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }

        }
    }
}
