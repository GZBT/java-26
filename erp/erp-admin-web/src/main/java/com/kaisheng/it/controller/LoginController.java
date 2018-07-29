package com.kaisheng.it.controller;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/7/26 0026
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/home")
    public String home(){

        return "home";
    }

    @GetMapping("/login")
    public String login(@CookieValue(required =false) String employeeTel, Model model){

        model.addAttribute("employeeTel", employeeTel);

        return "login";
    }

    @PostMapping("/login")
    public String login(String employeeTel,
                        String password,
                        String remember,
                        HttpServletRequest req,
                        HttpServletResponse resp,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){

        // 获得请求的ip
        String loginIP = req.getRemoteAddr();

        try {
            Employee employee = loginService.login(employeeTel,password, loginIP);
            session.setAttribute("employee",employee);

            if(StringUtils.isNotEmpty(remember)){
                Cookie cookie = new Cookie("employeeTel", employeeTel);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 *7);
                cookie.setHttpOnly(true);

                resp.addCookie(cookie);
            }else {
                Cookie[] cookies = req.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if("employeeTel".equals(cookie.getName())){
                            cookie.setDomain("localhost");
                            cookie.setPath("/");
                            cookie.setMaxAge(0);

                            resp.addCookie(cookie);
                            break;
                        }
                    }
                }
            }
            return "redirect:/home";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp)throws IOException {

        // false  防止创建session
        HttpSession session = req.getSession(false);

        if(session == null){
            return "redirect:/login";
        }
        session.removeAttribute("employee");
        return "redirect:/login";


    }

}