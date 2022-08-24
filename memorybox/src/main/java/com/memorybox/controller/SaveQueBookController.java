package com.memorybox.controller;

import com.memorybox.dto.QuestionDto;
import com.memorybox.service.QuestionService;
import com.memorybox.service.SaveQueBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaveQueBookController {
    private final SaveQueBookService saveQueBookService;
    private final QuestionService questionService;

    @GetMapping(value = "/saveQueBook/{saveQueBookId}")
    public String showdtl(Model model, @PathVariable("saveQueBookId") Long saveQueBookId){
        List<QuestionDto> questionDtoList = questionService.getSaveQuestionDtoList(saveQueBookId);

        model.addAttribute("questionDtoList", questionDtoList);
        return "SaveQueBook/SaveQueBookDtl";
    }

}
