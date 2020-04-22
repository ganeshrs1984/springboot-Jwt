package com.mkyong.controller;

import com.mkyong.jwt.config.JwtTokenUtil;
import com.mkyong.jwt.service.JwtUserDetailsService;
import com.mkyong.jwt.service.SingletonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    SingletonComponent singletonComponent;

    @GetMapping("/")
    public String home1(HttpServletResponse response) {
        System.out.println(singletonComponent.getName());
        return "/home";
    }

    @GetMapping("/home")
    public String home(@CookieValue(name = "platform1", defaultValue = "platformDefault") String cookieName)
    {
        try {
            System.out.println(cookieName);
            Cookie cookie = new Cookie("platform2","ganesh testing jwt2");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(currentPrincipalName);
            System.out.println(jwtTokenUtil.generateToken(userDetails));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {

        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
