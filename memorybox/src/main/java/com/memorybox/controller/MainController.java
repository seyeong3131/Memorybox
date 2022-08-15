package com.memorybox.controller;

import com.memorybox.dto.MainQueBundleDto;
import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.service.QueBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final QueBundleService queBundleService;

    @GetMapping(value = "/")
    public String main(QueBundleSearchDto queBundleSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        if(queBundleSearchDto.getSearchQuery() == null)
        {
            queBundleSearchDto.setSearchQuery("");
        }
        Page<MainQueBundleDto> queBundles = queBundleService.getMainQueBundlePage(queBundleSearchDto, pageable);
        model.addAttribute("queBundles", queBundles);
        model.addAttribute("queBundleSearchDto",queBundleSearchDto);
        model.addAttribute("maxPage",5);
        return "main";

    }

}
