package com.memorybox.controller;

import com.memorybox.dto.MyQueBoxItemDto;
import com.memorybox.dto.QuestionDto;
import com.memorybox.dto.SaveQueDto;
import com.memorybox.service.QuestionService;
import com.memorybox.service.SaveQueBookService;
import com.memorybox.service.SaveQueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaveQueBookController {
    private final SaveQueBookService saveQueBookService;
    private final QuestionService questionService;
    private final SaveQueService saveQueService;

    @PostMapping(value = "/saveQue")
    public @ResponseBody ResponseEntity save(@RequestBody @Valid SaveQueDto saveQueDto,
                                                   BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
        }
        String email = principal.getName();
        Long saveQueId;

        saveQueId =saveQueBookService.addSaveQueBook(saveQueDto, email);

        return new ResponseEntity<Long>(saveQueId, HttpStatus.OK);
    }

    @GetMapping(value = "/saveQueBook/{saveQueBookId}")
    public String showdtl(Model model, @PathVariable("saveQueBookId") Long saveQueBookId){
        List<QuestionDto> questionDtoList = questionService.getSaveQuestionDtoList(saveQueBookId);

        model.addAttribute("questionDtoList", questionDtoList);
        return "SaveQueBook/SaveQueBookDtl";
    }


    @DeleteMapping(value = "/saveQueBook/{saveQueBookId}/Question/{deleteQueId}")
    public @ResponseBody ResponseEntity deleteSaveQue(@PathVariable("saveQueBookId") Long saveQuebookId, @PathVariable("deleteQueId") Long queId,
                                                      Principal principal){
        Long saveQueId = saveQueService.findSaveQueId(saveQuebookId, queId);
        if(!saveQueBookService.validateSaveQue(saveQueId, principal.getName())){
            return new ResponseEntity<String>("문제 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        saveQueService.deleteSaveQue(saveQueId);
        return new ResponseEntity<Long>(saveQueId,HttpStatus.OK);
    }


}
