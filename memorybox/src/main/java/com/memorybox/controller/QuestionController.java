package com.memorybox.controller;

import com.memorybox.dto.QuestionFormDto;
import com.memorybox.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping(value = "/admin/question/new")
    public String questionForm(Model model){
        model.addAttribute("questionFormDto", new QuestionFormDto());
        return "question/questionForm";
    }

    @PostMapping(value = "/admin/question/new")
    public String questionNew(@Valid QuestionFormDto questionFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "question/questionForm";
        }

        try{
            questionService.saveQuestion(questionFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", "문제 등록 중 에러가 발생하였습니다.");
            return "question/questionForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/admin/question/{que_id}")
    public String questionDtl(@PathVariable("que_id")Long questionId, Model model){
        try{
            QuestionFormDto questionFormDto = questionService.getQuestionDto(questionId);
            model.addAttribute("questionFormDto", questionFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 문제입니다.");
            model.addAttribute("questionFormDto", new QuestionFormDto());
            return "question/questionForm";
        }
        return "question/questionForm";
    }

    @PostMapping(value = "/admin/question{que_Id}")
    public String questionUpdate(@Valid QuestionFormDto questionFormDtoFormDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "question/questionForm";
        }
        try {
            questionService.updateQuestion(questionFormDtoFormDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "문제 수정 중 에러가 발생하였습니다.");
            return "question/questionForm";
        }
        return "redirect:/";
    }
}
