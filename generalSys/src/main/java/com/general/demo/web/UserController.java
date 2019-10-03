package com.general.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.general.demo.domain.model.User;
import com.general.demo.domain.service.UserService;
import com.general.demo.ex.BusinessException;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) {
        List<User> users;
        try {
            users = userService.findAll();
            model.addAttribute("users",users);

        } catch (BusinessException e) {
            model.addAttribute("errorMessage",e.getMessage());
        }
        return "user/list";
    }

    @GetMapping(value = "{userId}")
    public String get(@PathVariable String userId,Model model) {
        try {
            User user = userService.findByUserId(userId);
            model.addAttribute("user",user);
        } catch (Exception e) {
            model.addAttribute("ErrorMessage", e.getMessage());
        }
        return "user/detail";
    }

    /**
     * 新規ユーザの作成
     * @param userId
     * @return
     */
    @GetMapping(value = "new")
    public String  newUser(Model model) {
        User user = new User(null);
        model.addAttribute("user",user);
        return "user/new";
    }

    /**
     * 新規ユーザ登録
     * @param userId
     * @return
     */
    @PostMapping(value = "new")
    public String  post(@Validated @ModelAttribute User user,
            BindingResult resul,
            Model model) {
        if(resul.hasErrors()) {
            model.addAttribute("errorMessage", "入力チェックエラー");
            return "user/new";
        }
        try {
            model.addAttribute("user", userService.saveFor(user));
            model.addAttribute("successMessage","新規登録が完了しました。");

            return "user/detail";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "user/detail";
    }

    /**
     * データの更新
     * @param userId
     * @return
     */
    @PutMapping(value = "{userId}")
    public String update(@PathVariable String userId,
            @Validated @ModelAttribute User user,
            BindingResult resul,
            Model model) {
        if(resul.hasErrors()) {
            model.addAttribute("errorMessage","入力チェックエラー。");
            return "user/detail";

        }
        User res;
        try {
            res = userService.saveFor(user);
            model.addAttribute("user", res);
            model.addAttribute("successMessage","更新が完了しました。");

        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "user/detail";
    }

    /***
     * データの削除
     * @param userId
     * @return
     */
    @DeleteMapping(value = "{userId}")
    public String  delete(@PathVariable String userId,Model model) {
        try {
            userService.deleteByUserId(userId);
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "user/list";
    }
}
