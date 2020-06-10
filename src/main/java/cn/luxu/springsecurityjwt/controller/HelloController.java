package cn.luxu.springsecurityjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class HelloController {

    @RequestMapping(value="/hello1", method= RequestMethod.GET)
    @RolesAllowed({"USER", "ADMIN"})
    public String hello1() {
        return "Hello1!";
    }

    @RequestMapping(value="/hello2", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String hello2() {
        return "Hello2!";
    }

    @RequestMapping(value="/hello3", method=RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String hello3() {
        return "Hello3!";
    }
}
