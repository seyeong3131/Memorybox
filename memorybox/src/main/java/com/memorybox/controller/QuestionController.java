package com.memorybox.controller;

import com.memorybox.dto.QueBundleFormDto;
import com.memorybox.dto.QuestionFormDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import com.memorybox.service.QueBundleService;
import com.memorybox.service.QuestionService;
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
public class QuestionController {

    private final QuestionService questionService;
    private final QueBundleService queBundleService;

    @GetMapping(value = "/admin/question/new")
    public String questionForm(Model model){
        model.addAttribute("queBundleNmList",queBundleService.queBundleNmList());
        model.addAttribute("questionFormDto", new QuestionFormDto());
        return "question/questionForm";
    }

    @PostMapping(value = "/admin/question/new")
    public String questionNew(@Valid QuestionFormDto questionFormDto, BindingResult bindingResult, Model model, @RequestParam("questionImgFile") MultipartFile questionImgFile){
        if(bindingResult.hasErrors()){
            return "question/questionForm";
        }

        if (questionImgFile.isEmpty() && questionFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "문제지 이미지는 필수 입력 값입니다.");
            return "question/questionForm";
        }

        try{
            questionService.saveQuestion(questionFormDto, questionImgFile);
        }catch (Exception e){
            model.addAttribute("errorMessage", "문제 등록 중 에러가 발생하였습니다.");
            return "question/questionForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/admin/question/{que_id}")
    public String questionDtl(@PathVariable("que_id")Long questionId, Model model){
        try{
            model.addAttribute("queBundleNmList",queBundleService.queBundleNmList());
            QuestionFormDto questionFormDto = questionService.getQuestionFormDto(questionId);
            model.addAttribute("questionFormDto", questionFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 문제입니다.");
            model.addAttribute("queBundleNmList",queBundleService.queBundleNmList());
            model.addAttribute("questionFormDto", new QuestionFormDto());
            return "question/questionForm";
        }
        return "question/questionForm";
    }

    @PostMapping(value = "/admin/question/{que_Id}")
    public String questionUpdate(@Valid QuestionFormDto questionFormDto, BindingResult bindingResult, Model model,  @RequestParam("questionImgFile") MultipartFile questionImgFile){
        if (bindingResult.hasErrors()) {
            return "question/questionForm";
        }
        if (questionImgFile.isEmpty() && questionFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "문제지 이미지는 필수 입력 값입니다.");
            return "queBundle/queBundleForm";
        }
        try {
            questionService.updateQuestion(questionFormDto, questionImgFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "문제 수정 중 에러가 발생하였습니다.");
            return "question/questionForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"/admin/questions", "/admin/questions/{page}"})
    public String itemManage(QuestionSearchDto questionSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Question> questions = questionService.getAdminQuestionPage(questionSearchDto, pageable);
        model.addAttribute("questions", questions);
        model.addAttribute("questionSearchDto", questionSearchDto);
        model.addAttribute("maxPage", 5);
        return "question/questionMng";
    }

    @GetMapping(value = {"/queBundleDtl/{page}","/queBundleDtl, ","/queBundle/{queBundle_id}"})
    public String queBundleDtl(@PathVariable("queBundle_id") Long queBundle_id, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Question> questions = questionService.getsearchQueBundle(queBundle_id, pageable);
        model.addAttribute("questions", questions);
        model.addAttribute("queBundle_id",queBundle_id);
        model.addAttribute("maxPage", 5);
        return "queBundle/queBundleDtl";
    }



    @DeleteMapping("/admin/question/{que_Id}")
    public @ResponseBody ResponseEntity deleteQuestion(@PathVariable("que_Id") Long questionId, Principal principal){
        if(!questionService.validateQuestion(questionId, principal.getName())){
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<Long>(questionId, HttpStatus.OK);
    }
}
