
package com.okta.spring.AuthorizationServerApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
@RestController
public class UserController {

    @GetMapping("/user/me") // 需要返回 application/json， 而不是 text/html
    public Principal user(Principal principal) {
        System.out.println("UserController.user " + principal);
        return principal;
    }

    @RequestMapping("/securedPage")
    public void securedPage( ) throws IOException {
        System.out.println("WeiboController.securedPage");
    }


    @RequestMapping("/aa")
    public void aa( ) throws IOException {
        System.out.println("WeiboController.aa");
    }

    @RequestMapping("/bb")
    public void bb( ) throws IOException {
        System.out.println("WeiboController.bb");
    }




}
