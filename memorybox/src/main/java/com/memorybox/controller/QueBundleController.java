package com.memorybox.controller;

import com.memorybox.constant.QCategory;
import com.memorybox.dto.QueBundleFormDto;
import com.memorybox.dto.QueBundleSearchDto;
import com.memorybox.dto.QuestionFormDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import com.memorybox.service.QueBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class QueBundleController {

    private final QueBundleService queBundleService;

    @GetMapping(value = "/admin/queBundle/new")
    public String queBundleForm(Model model){
        model.addAttribute("queBundleFormDto", new QueBundleFormDto());
        return "queBundle/queBundleForm";
    }


    @PostMapping(value = "/admin/queBundle/new")

    public String queBundleNew(@Valid QueBundleFormDto queBundleFormDto, BindingResult bindingResult, Model model,
                               @RequestParam("queBundleImgFile") MultipartFile queBundleImgFile){
        if(bindingResult.hasErrors()){
            return "queBundle/queBundleForm";
        }

        if (queBundleImgFile.isEmpty() && queBundleFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "문제지 이미지는 필수 입력 값입니다.");
            return "queBundle/queBundleForm";
        }

        try{
            queBundleService.saveQueBundle(queBundleFormDto, queBundleImgFile);
        }catch (Exception e){
            model.addAttribute("errorMessage", "문제 등록 중 에러가 발생하였습니다.");
            return "queBundle/queBundleForm";
        }

        return "redirect:/";
    }

    @ModelAttribute("qCategory")
    private QCategory[] qCategories(){
        return QCategory.values();
    }

    @GetMapping(value = "/admin/queBundle/{que_bundle_id}")
    public String queBundleDtl(@PathVariable("que_bundle_id")Long queBundleId, Model model){
        try{
            QueBundleFormDto queBundleFormDto = queBundleService.getQueBundleDto(queBundleId);
            model.addAttribute("queBundleFormDto", queBundleFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 문제입니다.");
            model.addAttribute("queBundleFormDto", new QueBundleFormDto());
            return "queBundle/queBundleForm";
        }
        return "queBundle/queBundleForm";
    }

    @PostMapping(value = "/admin/queBundle/{que_bundle_Id}")
    public String queBundleUpdate(@Valid QueBundleFormDto queBundleFormDto, BindingResult bindingResult,
                                  @RequestParam("queBundleImgFile") MultipartFile queBundleImgFile,
                                  Model model){
        if (bindingResult.hasErrors()) {
            return "queBundle/queBundleForm";
        }
        if (queBundleImgFile.isEmpty() && queBundleFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "문제지 이미지는 필수 입력 값입니다.");
            return "queBundle/queBundleForm";
        }
        try {
            queBundleService.updateQueBundle(queBundleFormDto, queBundleImgFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "문제 수정 중 에러가 발생하였습니다.");
            return "queBundle/queBundleForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"/admin/queBundles", "/admin/queBundles/{page}"})
    public String QueBundleManage(QueBundleSearchDto queBundleSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<QueBundle> queBundles = queBundleService.getAdminQueBundlePage(queBundleSearchDto, pageable);
        model.addAttribute("queBundles", queBundles);
        model.addAttribute("queBundleSearchDto", queBundleSearchDto);
        model.addAttribute("maxPage", 5);
        return "queBundle/queBundleMng";
    }

    @DeleteMapping("/admin/queBundle/{que_Id}")
    public @ResponseBody ResponseEntity deleteQueBundle(@PathVariable("queBundle_Id") Long queBundleId, Principal principal){
        queBundleService.deleteQueBundle(queBundleId);
        return new ResponseEntity<Long>(queBundleId, HttpStatus.OK);
    }

//    @GetMapping(value = "/user/availability/list")
//    public String queBundleList(Model model){
//
//        model.addAttribute("list",queBundleService.queBundleList());
//        return "queBundle/queBundleList";
//    }

}
