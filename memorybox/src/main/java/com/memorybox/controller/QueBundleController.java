package com.memorybox.controller;

import com.memorybox.constant.QCategory;
import com.memorybox.dto.QueBundleFormDto;
import com.memorybox.entity.QueBundle;
import com.memorybox.service.QueBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class QueBundleController {

    @Autowired
    private QueBundleService queBundleService;

    @GetMapping(value = "/user/availability/new")
    public String queBundleForm(Model model){
        model.addAttribute("queBundleFormDto", new QueBundleFormDto());
        return "queBundle/queBundleForm";
    }


    @PostMapping(value = "/user/availability/new")
    public String queBundleNew(@Valid QueBundleFormDto queBundleFormDto, BindingResult bindingResult,
                               Model model){
            queBundleService.saveQueBundle(queBundleFormDto);

        return "";
    }

    @ModelAttribute("qCategory")
    private QCategory[] qCategories(){
        return QCategory.values();
    }

    @GetMapping(value = "/user/availability/list")
    public String queBundleList(Model model){

        model.addAttribute("list",queBundleService.queBundleList());
        return "queBundle/queBundleList";
    }

}
