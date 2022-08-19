package com.memorybox.controller;

import com.memorybox.dto.SaveQueDto;
import com.memorybox.dto.SaveQueBookDto;
import com.memorybox.entity.SaveQue;
import com.memorybox.service.SaveQueBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaveQueBookController {
    private final SaveQueBookService saveQueBookService;

    @GetMapping(value = "/saveQueBook")
    public String showdtl(){ return "SaveQueBook/SaveQueBookDtl"; }


        @PatchMapping(value = "/saveQue/{saveQueId}")
        public @ResponseBody ResponseEntity updateSaveQue(@PathVariable("saveQueId") Long saveQueId,
        int count, Principal principal){
            System.out.println(saveQueId);
            if (count <= 0){
                return new ResponseEntity<String>("최소 1문제 이상 고르세요.", HttpStatus.BAD_REQUEST);
            } else if (!saveQueBookService.validateSaveQue(saveQueId, principal.getName())) {
                return new ResponseEntity<String>("수정권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
            saveQueBookService.updateSaveQueCount(saveQueId, count);
            return new ResponseEntity<Long>(saveQueId, HttpStatus.OK);
        }

        @DeleteMapping(value = "/saveQue/{saveQueId}")
        public @ResponseBody ResponseEntity deleteSaveQue(@PathVariable("saveQueId")Long saveQueId,
                Principal principal){
            if (!saveQueBookService.validateSaveQue(saveQueId, principal.getName())){
                return new ResponseEntity<String>("수정권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
            saveQueBookService.deleteSaveQue(saveQueId);
            return new ResponseEntity<Long>(saveQueId, HttpStatus.OK);
        }

    }
