
package com.okta.spring.SpringBootOAuthClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class WeiboController  {

    /*
         目前配置的微博的授权回调页：   http://192.168.1.103:8999/v1/weibo/user/login
     */
    @RequestMapping("/securedPage")
    public void securedPage( ) throws IOException {
        System.out.println("Controller层.securedPage");
    }

    @RequestMapping("/login")
    public void login(@RequestParam("code") String code, HttpServletResponse response) throws IOException {

    }


    @RequestMapping("/callback")
    public String wbcallback(@RequestParam("code") String code) throws IOException {
        return "callback";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public JSONObject logout(@CookieValue(value = "aimee-test-token", required = false)String access_token
            , @CookieValue(value = "thirdType", required = false)String thirdType, HttpServletResponse response) {
        System.out.println("access_token = [" + access_token + "], thirdType = [" + thirdType + "], response = [" + response + "]");
        return null;
    }
}
