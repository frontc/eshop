package cn.lefer.eshop.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @GetMapping(path = "/anon")
    public String anon(){
        return "anon";
    }

    @GetMapping(path = "/customer")
    public String customer(){
        return "customer";
    }

    @GetMapping(path = "/authc")
    public String authc(){
        return "authc";
    }
}
