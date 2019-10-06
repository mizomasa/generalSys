package com.general.demo.web;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.general.demo.domain.model.User;

public class loginController {

    public String login() {

        return "login";
    }
    public ModelAndView login(@ModelAttribute @Validated User form,
            BindingResult result, ModelAndView mv) {

        if(!result.hasErrors()) {
            mv.addObject("errorMessage", "ログイン情報が間違っています");
        }

        mv.setViewName("login");
        return mv;
    }
    @GetMapping("accessDenied")
    public String tryLogin() {
        return "error/accessDenied";
    }
}
