package com.okta.spring.SpringBootOAuthClient;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("failureAuthentication")
public class OoFailureAuthentication extends SimpleUrlAuthenticationFailureHandler {


    /**
     * 如果是 用户名密码错误，不会进这里来；
     *
     * 这里通常是 authorization_request_not_found 异常的时候才进来！
     *
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("onAuthenticationFailure request = [" + request + "], response = [" + response + "], exception = [" + exception + "]");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        JSONObject result = new JSONObject();
        result.put("code",500);
        result.put("msg","fail");
        result.put("ex",exception);
        result.put("msg2","登录失败");// 为什么出现问号乱码？
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
