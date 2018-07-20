package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author guojiabang
 * @date 2018/7/20 0020
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/addUser")
    public String addUser(){

        return "user/new";
    }

    @PostMapping("/addUser")
    public String user(User user, String tel){
        System.out.println("userName:" + user.getUserName());
        System.out.println("password:" + user.getPassword());
        System.out.println("tel:" + tel);
        return "redirect:/user/home";
    }

    @GetMapping("/home")
    public String home() {
        // xxx...
        return "user/home";
    }

/*
    @GetMapping("/addUser")
    public String User(String userName ,String password){
        System.out.println("userName" + userName);
        System.out.println("password" + password);

        return "User/new";
    }
*/

    // 约束变量类型url一个参数
    @GetMapping("/{id:\\d+}")
    public String addUser(
            @RequestParam(defaultValue = "1") Integer p,
            @PathVariable Integer id){

        System.out.println("id:" + id);
        System.out.println("p:" + p);
        return "user/home";

    }

    // 通过model对象将值传递到视图中
    @PostMapping("/{id:\\d+}")
    public String addUser(
            @PathVariable Integer id,
            Model model){
        model.addAttribute("id", id);
        System.out.println("id:" + id);

        return "user/home";

    }

    // url多个参数
    @GetMapping("/{type:v-\\d+}/{id:\\d+}")
    public ModelAndView addUser(
            @PathVariable String type,
            @PathVariable("id") Integer userId) {

       /* ModelAndView modelAndView = new ModelAndView();
        // 视图设置
        modelAndView.setViewName("User/home");*/
        ModelAndView modelAndView = new ModelAndView("user/home");
        modelAndView.addObject("userId:", userId);
        modelAndView.addObject("type:", type);

        System.out.println("id:" + userId);
        System.out.println("type:" + type);

        return modelAndView;
    }



}