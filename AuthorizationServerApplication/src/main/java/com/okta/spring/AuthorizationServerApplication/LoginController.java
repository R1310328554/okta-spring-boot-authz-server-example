
package com.okta.spring.AuthorizationServerApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@Controller
public class LoginController {


    @RequestMapping("/")
    public String idx( ) throws IOException {
        System.out.println("WeiboController. no 404 啊");
        return "4000000xxx";
    }

    @GetMapping("/myLogin")
    public String myLogin( ) throws IOException {
        System.out.println("WeiboController.myLogin");
        return "myLogin";
    }

    @PostMapping("/myLogin")
    public String myLogin2() throws IOException {
        System.out.println("WeiboController.myLogin 2");
        return "myLogin";
    }

    @RequestMapping("/doLogin")
    public String doLogin( ) throws IOException {
        System.out.println("WeiboController.login");
        return "lo";
    }


}
