package com.general.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.general.demo.domain.model.system.Logging;
import com.general.demo.domain.service.LogService;



@Controller
@RequestMapping(value="/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping()
    public String list(Model model, Pageable pageable) {

        List<Logging> loggings = logService.findByLevel("INFO");
        model.addAttribute("loggings", loggings);

        return "log/list";
    }
}
