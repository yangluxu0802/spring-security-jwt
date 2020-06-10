package cn.luxu.springsecurityjwt.controller;

import cn.luxu.springsecurityjwt.response.R;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("home")
    public <T> R<T> home() {
        return new R<>(200,"Welcome Home",null);
    }

    @GetMapping("tuichu")
    public <T> R<T> tuichu() {
        R<T> response = new R<>(200,"Logout Success",null);
        SecurityContextHolder.clearContext();
        return response;
    }

    @GetMapping("me")
    public R<String> me() {
        R<String> response = new R<>(200,"Get UserInfo Success",null);
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response.setDate(principal);
        return response;
    }
}
