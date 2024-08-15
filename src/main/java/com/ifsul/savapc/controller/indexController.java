package com.ifsul.savapc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.MXBean;
import java.io.IOException;

@Controller
public class indexController {

    //////////////////// LOGIN////////////////////////////

    @GetMapping("/")
    public String indexController() {
        return "redirect:/index";
    }
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/login")
    public String login() {
        System.out.print(System.getProperty("catalina.base"));
        return "login";
    }

    @RequestMapping("/sucesso")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        System.out.println("Login correto");
        String role =  authResult.getAuthorities().toString();

        if(role.contains("ALUNO")){
            System.out.println("Aluno logou");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/aluno/turmas"));
        }
        else if(role.contains("PROF")) {
            System.out.println("Professor logou");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/auth/painelgeral"));
        }
    }





}
