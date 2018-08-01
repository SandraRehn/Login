package com.example.Login;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@Controller
public class InlogController {


    @GetMapping("/")
    public String getIndex(){
        return "redirect:form";
    }



    @GetMapping("/secret")
    public String secret(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("loggedin") != null)
            return "secret";
        else
            return "redirect:form";

    }

    @GetMapping("/logout")
    public String getLogout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cookie sessionCookie = new Cookie("JSESSIONID", "");
        session.invalidate();
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        return "redirect:form";
    }

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }

    @PostMapping("/form")
    public String postForm(@RequestParam(name = "username", required = true, defaultValue = "") String username,
                           @RequestParam(name = "password", required = true, defaultValue = "") String password,
                           HttpServletRequest request) {


        if (veriferad(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedin", true);

            return "redirect:secret";

        }
        return "redirect:form";
    }

    public Boolean veriferad(String username, String password) {
        return (username.equals("sandra") && password.equals("sandra"));
    }
}

