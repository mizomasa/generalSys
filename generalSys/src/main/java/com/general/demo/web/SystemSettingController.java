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

import com.general.demo.domain.model.system.SystemSetting;
import com.general.demo.domain.service.SystemSettingsService;
import com.general.demo.ex.BusinessException;
import com.general.demo.ex.FatalException;

@Controller
@RequestMapping(value = "/systemSetting")
public class SystemSettingController {

    @Autowired
    private SystemSettingsService systemSettingsService;


    @GetMapping()
    public String index(Model model) {
        List<SystemSetting> systems = systemSettingsService.findAll();
        model.addAttribute("systems",systems);
        return "systemSetting/list";
    }

    @GetMapping(value = "{id}/{subId}")
    public String get(@PathVariable String id,@PathVariable String subId,Model model ) {
        try {
            SystemSetting system = systemSettingsService.findBy(id,subId);
            model.addAttribute("system",system);
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "systemSetting/detail";
    }

    @GetMapping(value = "/new")
    public String get(SystemSetting system,Model model) {
        model.addAttribute("system",system);
        return "systemSetting/new";
    }

    @PostMapping(value = "/new")
    public String  post(@Validated @ModelAttribute SystemSetting system,
            BindingResult resul,
            Model model) {
        if(resul.hasErrors()) {
            model.addAttribute("errorMessage", "入力チェックエラー");
            return "systemSetting/new";
        }
        try {
            model.addAttribute("user", systemSettingsService.saveFor(system));
            model.addAttribute("successMessage","新規登録が完了しました。");
        } catch (FatalException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "systemSetting/detail";
    }

    /**
     * データの更新
     * @param userId
     * @return
     */
    @PutMapping(value = "{id}/{subId}")
    public String update(@PathVariable String id,@PathVariable String subId,
            @Validated @ModelAttribute SystemSetting system,
            BindingResult resul,
            Model model) {
        if(resul.hasErrors()) {
            model.addAttribute("errorMessage","入力チェックエラー。");
            return "systemSetting/detail";
        }
        try {
            model.addAttribute("user", systemSettingsService.saveFor(system));
            model.addAttribute("successMessage","更新が完了しました。");
        } catch (FatalException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "systemSetting/detail";
    }

    /***
     * データの削除
     * @param userId
     * @return
     */
    @DeleteMapping(value =  "{id}/{subId}")
    public String  delete(@PathVariable String id,@PathVariable String subId,
            Model model) {
        try {
            systemSettingsService.delete(id,subId);
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "systemSetting/list";
    }

}
