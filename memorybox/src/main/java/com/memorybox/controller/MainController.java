package com.memorybox.controller;

import com.memorybox.dto.MainQueBundleDto;
import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.dto.SaveQueAlertDto;
import com.memorybox.dto.SaveQueDto;
import com.memorybox.entity.SaveQue;
import com.memorybox.service.MemberService;
import com.memorybox.service.QueBundleService;
import com.memorybox.service.SaveQueBookService;
import com.memorybox.service.SaveQueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final QueBundleService queBundleService;
    private final MemberService memberService;
    private final SaveQueBookService saveQueBookService;
    private final SaveQueService saveQueService;

    @GetMapping(value = "/")
    public String main(QueBundleSearchDto queBundleSearchDto, Optional<Integer> page, Principal principal, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        if(queBundleSearchDto.getSearchQuery() == null)
        {
            queBundleSearchDto.setSearchQuery("");
        }

        if(principal != null && saveQueBookService.saveQueBookCheck(memberService
                .findMemberId(principal.getName()))) {
            List<SaveQueAlertDto> saveQueDtoList = saveQueService.findMySaveQueList(saveQueBookService.findSaveQueBookId(memberService
                    .findMemberId(principal.getName())));
            model.addAttribute("saveQueAlertDtoList", saveQueDtoList);
        }
        Page<MainQueBundleDto> queBundles = queBundleService.getMainQueBundlePage(queBundleSearchDto, pageable);
        model.addAttribute("queBundles", queBundles);
        model.addAttribute("queBundleSearchDto",queBundleSearchDto);
        model.addAttribute("maxPage",5);
        return "main";

    }

}
